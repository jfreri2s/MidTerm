package Controller;

import Model.Container;
import Model.ContainerException;
import Model.PersistenceException;

import java.util.List;

public class LoadCommand implements Command {
    private Container c = Container.getInstance();

    @Override
    public void execute(List<String> args) {
        //not sure whether there should be this dissection of this command?
        try {
            if (args.get(0).equals("-") && args.get(1).equals("force")) {
                c.loadForce();
            }
            if (args.get(0).equals("-") && args.get(1).equals("merge")) {
                c.loadMerge();
            }
        } catch (ContainerException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}

