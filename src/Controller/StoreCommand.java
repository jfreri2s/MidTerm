package Controller;

import Model.Container;
import Model.PersistenceException;

import java.util.List;

public class StoreCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) throws PersistenceException {
        c.store();
    }
}
