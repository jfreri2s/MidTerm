package Controller;

import Model.Container;

import java.util.List;

public class StoreCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        //TODO: call of the according store method in container
    }
}
