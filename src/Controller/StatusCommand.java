package Controller;

import Model.Container;

import java.util.List;

public class StatusCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        //TODO: call of the according status method in container
        /*the parameters could be tested for done, progress or to do */
    }
}
