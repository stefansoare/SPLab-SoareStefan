package Services.commands;

import Classes.*;

import java.util.List;

public class GetAllCommand<T> implements Command<List<T>, Void> {
    private final Repository<T> repository;
    public GetAllCommand(Repository<T> repository) {
        this.repository = repository;
    }
    private GetAllCommand(GetAllCommand<T> gac) {
        this.repository = gac.repository;
    }

    @Override
    public List<T> execute() {
        return repository.getAll();
    }

    @Override
    public Command<List<T>, Void> getClone() {
        return new GetAllCommand<>(this);
    }
}
