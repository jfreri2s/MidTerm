package Controller;

import Model.Container;

import java.util.List;

public class AddElementCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        // param == actor
        if(args.get(0).equals("-") && args.get(1).equals("actor") ){
            c.addElement(args.get(2));
        }
    }
}
