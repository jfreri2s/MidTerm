package View;

import Controller.*;
import Model.Invoker;


import java.util.HashMap;
import java.util.Scanner;

public class Parser {

    public void parseInput() {
        String strInput  = null;
        HashMap<String, Command > commandHashMap = new HashMap<String, Command>();
        commandHashMap.put("Actors", new ActorsCommand());
        commandHashMap.put("Analyze", new AnalyzeCommand());
        commandHashMap.put("Dump", new DumpCommand());
        commandHashMap.put("Undo", new UndoCommand());
        commandHashMap.put("Status", new StatusCommand());
        commandHashMap.put("AddElement", new AddElementCommand());
        commandHashMap.put("Enter", new EnterCommand());
        commandHashMap.put("Store", new StoreCommand());
        commandHashMap.put("Load", new LoadCommand());
        commandHashMap.put("Exit", new ExitCommand());
        commandHashMap.put("Help", new HelpCommand());
        commandHashMap.put("Help", new SetStrategyCommand());

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("> ");
            strInput = sc.nextLine();
            Command command = commandHashMap.get(strInput);
            Invoker inv = new Invoker(command);
            inv.executeOperation(command);
        }
    }
}
