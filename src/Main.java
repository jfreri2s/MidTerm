import Model.Container;
import Model.PersistenceStrategyStream;
import Model.Userstory;
import View.Parser;

public class Main {

    public static void main(String[] args) {
        Container c = Container.getInstance();
        Parser eingabe = new Parser();
        c.setStrategy(new PersistenceStrategyStream<Userstory>());
        eingabe.startEingabe();
        // TODO: Was passiert nach der Eingabe des Risikos Beispielwerte oder Test-Cases wären gut.

        System.out.println("Hello World!");
    }

}
