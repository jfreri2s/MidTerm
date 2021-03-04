package Controller;

import Model.Container;

import java.util.List;

public class DumpCommand implements Command{
    private Container c;
    @Override
    public void execute(List<String> args) {
        for (String s : args) {
            System.out.println(s);
        }
    }

    public void undo() {

    }
}
