package ro.info.uvt.commands;

import ro.info.uvt.Classes.MyPair;
import ro.info.uvt.persistence.CrudRepository;

public class UpdateOneCommand<T> implements Command<T, MyPair<Long, T>> {
    private final CrudRepository<T, Long> repository;
    private  MyPair<Long, T> commandContext;

    public UpdateOneCommand(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }
    private UpdateOneCommand(UpdateOneCommand<T> uoc) {
        this.repository = uoc.repository;
        this.commandContext = uoc.commandContext;
    }
    @Override
    public void setCommandContext( MyPair<Long, T> o) {
        commandContext = o;
    }

    @Override
    public Command<T, MyPair<Long, T>> getClone() {
        return new UpdateOneCommand<>(this);
    }

    @Override
    public T execute() {
        return repository.update(commandContext.first, commandContext.second);
//        return null;
    }
}