package Model;

import Controller.Command;

import java.util.List;
import java.util.Stack;

public class Invoker {
    private Stack<Command> cs = new Stack<Command>();
    private Command cm;
    public Invoker(Command command){
        cm = command;
    }
    public void executeOperation(Command c, List<String>  args) throws AnalyzeException, ContainerException, PersistenceException {
        cs.add(c);
        c.execute(args);
    }
}
