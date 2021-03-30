package Controller;

import Model.AnalyzeException;
import Model.ContainerException;
import Model.PersistenceException;

import java.util.List;

public interface Command {
    void execute( List<String> args ) throws AnalyzeException, PersistenceException, ContainerException;

}
