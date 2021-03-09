package Controller;

import Model.Container;

import java.util.List;

public class ActorsCommand implements Command{
    private Container c;
    @Override
    public void execute(List<String> args) {
        //TODO: call of the according actors method in container
    }


    public void undo() {

    }
}
