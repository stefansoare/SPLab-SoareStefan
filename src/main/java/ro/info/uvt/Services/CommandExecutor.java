package ro.info.uvt.Services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ro.info.uvt.commands.Command;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommandExecutor {
    private final ExecutorService executor;
    private final Map<String, Object> objectResults;
    private final Map<String, Object> iterableResults;

    public CommandExecutor() {
        int poolSize = 2;
        objectResults = new HashMap<>();
        iterableResults = new HashMap<>();
        this.executor = Executors.newFixedThreadPool(poolSize);

    }

    public <T1,T2> T1 execute(Command<T1,T2> cmd){
        return cmd.execute();
    }

    public <T1,T2> String executeAsync(Command<T1,T2> cmd){
        String opId = UUID.randomUUID().toString();
        var deepClonedCmd = cmd.getClone();
        Runnable runnableTask = () -> {
            try{
                T1 obj = deepClonedCmd.execute();
                if(obj instanceof Iterable<?>){
                    iterableResults.put(opId, obj);
                }
                else objectResults.put(opId, obj);
            }catch (Exception ex){
                int x = 1;
            }
        };
        executor.submit(runnableTask);
        return opId;
    }

    public Object getAsyncResult(String opId){
        if(objectResults.containsKey((opId))){
            return objectResults.get(opId);
        }else {
            return iterableResults.get(opId);
        }
    }

}