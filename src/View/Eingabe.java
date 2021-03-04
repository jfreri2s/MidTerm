package View;

import Controller.*;
import Model.Invoker;


import java.util.*;

public class Eingabe {
    public void parseInput() {
        String strInput  = null;
        String[] strSplit  = null;
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
        commandHashMap.put("SetStrategy", new SetStrategyCommand());

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("> ");
            strInput = sc.nextLine();
            strSplit = strInput.split(" ");
            List<String> myList = new ArrayList<>(Arrays.asList(strSplit));
            myList.remove(0);
            // TODO: Command Fail
            Command command = commandHashMap.get(strSplit[0]);
            Invoker inv = new Invoker(command);
            inv.executeOperation(command,myList);
        }
    }
}
