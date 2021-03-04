package View;

import Controller.*;
import Model.Invoker;


import java.util.*;

public class Eingabe {
    public void parseInput() {
        String strInput  = null;
        String[] strSplit  = null;
        HashMap<String, Command > commandHashMap = new HashMap<String, Command>();
        commandHashMap.put("actors", new ActorsCommand());
        commandHashMap.put("analyze", new AnalyzeCommand());
        commandHashMap.put("dump", new DumpCommand());
        commandHashMap.put("undo", new UndoCommand());
        commandHashMap.put("status", new StatusCommand());
        commandHashMap.put("addElement", new AddElementCommand());
        commandHashMap.put("enter", new EnterCommand());
        commandHashMap.put("store", new StoreCommand());
        commandHashMap.put("load", new LoadCommand());
        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("setStrategy", new SetStrategyCommand());

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("> ");
            strInput = sc.nextLine().toLowerCase();
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
