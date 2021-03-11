package Controller;

import Model.Container;

import java.util.List;

public class StatusCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        switch (args.get(1)){
            case "done":c.status(Integer.parseInt(args.get(0)),"done");
            break;
            case "progress":c.status(Integer.parseInt(args.get(0)),"progress");
            break;
            case "todo": c.status(Integer.parseInt(args.get(0)),"todo");
            break;
        }

    }
}
