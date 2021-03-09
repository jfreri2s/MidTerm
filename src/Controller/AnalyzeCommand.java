package Controller;

import Model.Container;

import java.util.List;
import java.util.Locale;

public class AnalyzeCommand implements Command{
    private Container c = Container.getInstance();
    @Override
    public void execute(List<String> args) {
        // param details hints all
        if(args.get(0).equals("-") && args.get(1).equals("all")){
            // param ==  all, alle userstories müssen ausgegeben werden.
        }
        else {
            if(args.get(1).equals("-") && args.get(2).equals("details")){
                if(args.get(1).equals("-") && args.get(4).equals("hints")){
                    //es werden zusätzlich zu den details weitere Hinweise angezeigt
                }
                else{
                    //nur details werden angezeigt
                }
            }
        }
    }

    public void undo() {

    }
}
