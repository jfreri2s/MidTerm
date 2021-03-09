package Controller;

import Model.Container;

import java.util.List;

public class LoadCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        //TODO: call of the according load method in container, depending on the parameters
        //not sure whether there should be this dissection of this command?
        if(args.get(0).equals("-") && args.get(0).equals("force")){

        }
    }
}
