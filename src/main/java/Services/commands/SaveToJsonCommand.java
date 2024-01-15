package Services.commands;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import Classes.Book;
import Classes.Visitee;
import Services.JsonSerializer;

import java.util.List;


/**
 * TODO: use T as SaveVisitor type.
 * TODO: Make this command generic for any serializable type.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SaveToJsonCommand implements Command<String, Object> {
    private final JsonSerializer serializer;
    @Setter
    private Object commandContext;

    @Autowired
    public SaveToJsonCommand(JsonSerializer serializer) {
        this.serializer = serializer;
    }
    private SaveToJsonCommand(SaveToJsonCommand stjc) {
        this.serializer = stjc.serializer;
        this.commandContext = stjc.commandContext;
    }

    @Override
    public String execute() {
        if (commandContext instanceof List)
            return serializer.serialize((List<Visitee>) commandContext);
        return serializer.serialize((Book) commandContext);
    }

    @Override
    public Command<String, Object> getClone() {
        return new SaveToJsonCommand(this);
    }
}