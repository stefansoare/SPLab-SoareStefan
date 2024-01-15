package Services.commands;

import Classes.Repository;

public class DeleteOneCommand<T> implements Command<Void, String> {
    private final Repository<T> repository;
    private String commandContext;

    public DeleteOneCommand(Repository<T> repository) {
        this.repository = repository;
    }
    private DeleteOneCommand(DeleteOneCommand<T> doc){
        repository = doc.repository;
        commandContext = doc.commandContext;
    }

    @Override
    public void setCommandContext(String o) {
        commandContext = o;
    }

    @Override
    public Command<Void, String> getClone() {
        return new DeleteOneCommand<>(this);
    }

    @Override
    public Void execute() {
        repository.delete(commandContext);
        return null;
    }
}