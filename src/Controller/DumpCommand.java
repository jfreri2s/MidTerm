package Controller;

import Model.Container;

import java.util.List;

public class DumpCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        if(args.get(0).equals("-") && args.get(1).equals("status")){
            //TODO: call of the according container method
            c.dump(args);
        }

    }

    public void undo() {

    }
}
