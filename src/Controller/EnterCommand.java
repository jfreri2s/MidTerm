package Controller;

import Model.Container;
import Model.ContainerException;

import java.util.List;

public class EnterCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        try {
            c.enter();
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }
}
