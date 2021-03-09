package Controller;

import Model.Container;

import java.util.List;

public class SetStrategyCommand implements Command{
    private Container c;
    @Override
    public void execute(List<String> args) {
        //TODO: call of the according setStrategy method in container, depending on the parameters
        //Which strategy is supposed to be set?
    }
}
