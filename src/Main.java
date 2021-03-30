import Model.*;
import View.Eingabe;

public class Main {

    public static void main(String[] args){
        Container c = Container.getInstance();
        Eingabe eingabe = new Eingabe();
        c.setStrategy(new PersistenceStrategyStream<Userstory>());
        try {
            eingabe.parseInput();
        } catch (AnalyzeException e) {
            System.out.println(e.getMessage());
        } catch (ContainerException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }

    }

}
