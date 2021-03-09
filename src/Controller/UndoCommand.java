package Controller;

import Model.Container;

import java.util.List;

public class UndoCommand implements Command{
    private Container c;
    @Override
    public void execute(List<String> args) {
        //TODO: call of the according load method in container, depending on the parameters
    }
}
