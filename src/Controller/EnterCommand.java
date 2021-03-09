package Controller;

import Model.Container;

import java.util.List;

public class EnterCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        //Are the parameters useless because this method doesn't utilize them?
        //TODO: call according enter method in container
    }
}
