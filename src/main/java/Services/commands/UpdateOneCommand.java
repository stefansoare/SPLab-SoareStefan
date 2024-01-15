package Services.commands;

import Classes.MyPair;
import Classes.Repository;

public class UpdateOneCommand<T> implements Command<Void, MyPair<String, T>> {
    private final Repository<T> repository;
    private  MyPair<String, T> commandContext;

    public UpdateOneCommand(Repository<T> repository) {
        this.repository = repository;
    }
    private UpdateOneCommand(UpdateOneCommand<T> uoc) {
        this.repository = uoc.repository;
        this.commandContext = uoc.commandContext;
    }
    @Override
    public void setCommandContext( MyPair<String, T> o) {
        commandContext = o;
    }

    @Override
    public Command<Void, MyPair<String, T>> getClone() {
        return new UpdateOneCommand<>(this);
    }

    @Override
    public Void execute() {
        repository.update(commandContext.first, commandContext.second);
        return null;
    }
}