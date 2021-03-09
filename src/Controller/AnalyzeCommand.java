package Controller;

import Model.Container;

import java.util.List;
import java.util.Locale;

public class AnalyzeCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
       c.analyze(args);
    }

    public void undo() {

    }
}
