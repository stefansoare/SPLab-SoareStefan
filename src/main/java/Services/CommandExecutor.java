package Services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import Services.commands.Command;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommandExecutor {
    private final ExecutorService executor;
    private final Map<String, Object> results;

    public CommandExecutor() {
        int poolSize = 2;
        results = new HashMap<>();
        this.executor = Executors.newFixedThreadPool(poolSize);

    }

    public <T1,T2> T1 execute(Command<T1,T2> cmd){
        return cmd.execute();
    }

    public <T1,T2> String executeAsync(Command<T1,T2> cmd){
        String opId = UUID.randomUUID().toString();
        var deepClonedCmd = cmd.getClone();
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                results.put(opId, deepClonedCmd.execute());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executor.submit(runnableTask);
        return opId;
    }

    public Object getAsyncResult(String opId){
        return results.get(opId);
    }

}