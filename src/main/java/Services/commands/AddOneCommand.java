package Services.commands;

import Classes.Repository;

public class AddOneCommand<T> implements Command<Void, T>{
    private final Repository<T> repository;
    private T commandContext;

    public AddOneCommand(Repository<T> repository) {
        this.repository = repository;
    }
    private AddOneCommand(AddOneCommand<T> aoc) {
        this.repository = aoc.repository;
        this.commandContext = aoc.commandContext;
    }
    @Override
    public void setCommandContext(T o) {
        commandContext = o;
    }

    @Override
    public Command<Void, T> getClone() {
        return new AddOneCommand<>(this);
    }

    @Override
    public Void execute() {
        repository.add(commandContext);
        return null;
    }


}