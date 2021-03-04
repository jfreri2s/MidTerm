package Controller;

import java.util.List;

public interface Command {
    void execute( List<String> args );

    //public void undo();
}
