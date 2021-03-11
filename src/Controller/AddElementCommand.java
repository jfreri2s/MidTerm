package Controller;

import Model.Container;

import java.util.List;

public class AddElementCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        // param == actor
        c.addElement(args.get(1));

    }
}
