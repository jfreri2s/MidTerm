import Model.Container;
import Model.PersistenceStrategyStream;
import Model.Userstory;
import View.Eingabe;

public class Main {

    public static void main(String[] args) {
        Container c = Container.getInstance();
        Eingabe eingabe = new Eingabe();
        c.setStrategy(new PersistenceStrategyStream<Userstory>());
        eingabe.parseInput();

        System.out.println("Hello World!");
    }

}
