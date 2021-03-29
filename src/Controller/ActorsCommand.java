package Controller;

import Model.Container;
import Model.ContainerException;

import java.util.List;

public class ActorsCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args){
        c.actors();
    }


    public void undo() {

    }
}
