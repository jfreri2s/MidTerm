package Model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Container {

    private PersistenceStrategy<Userstory> pSS;
    private static LinkedList<Userstory> list;
    private static Container instance = null;
    private static final Object lock = new Object();

    // Implementationg of the methods for the  existing Commands
    private Container(){
         list = new LinkedList<>();
    }

    //synchronized verhindert mehrere Clients die zugreifen
    public static /*synchronized*/ Container getInstance(){
        synchronized(lock) {
            if (instance == null) {
                instance = new Model.Container();
            }
        }
        return instance;
    }

    public void addUserStory (Userstory r ) throws ContainerException {
        if ( contains(r) == true ) {
            throw new ContainerException("ID bereits vorhanden!");
        }
        list.add(r);

    }
    private boolean contains(Userstory u) {
        for ( Userstory a : list) {
            if (a.getId().equals(u.getId())) {
                return true;
            }
        }
        return false;
    }
    public int size(){
        return list.size();
    }

    public void store() throws PersistenceException {
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        pSS.save(list);
    }

    public void loadMerge() throws PersistenceException, ContainerException {
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        LinkedList<Userstory> tmp = (LinkedList<Userstory>) pSS.load();

            for (Userstory u : tmp) {
                Container.getInstance().addUserStory(u);
            }
    }
    public void loadForce() throws PersistenceException {
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        list = (LinkedList<Userstory>) pSS.load();
    }
    public List<Userstory> getCurrentList(){
        return list;
    }

    public void setStrategy(Model.PersistenceStrategyStream pSS) {
        this.pSS = pSS;
    }

    public void dump(List<Userstory> liste){
        System.out.println("1)Prio 2)ID 3)Text 4) Kriterien 5)Mehrwert 6)Strafe 7)Aufwand 8) Risiko");
        liste.stream()
                .sorted(Comparator.comparing(Userstory::getPrio))
                .filter(element -> element.getAufwand()>4)
                .forEach(element -> System.out.println(" 1)" + element.getPrio()+" 2)"+element.getId()+" 3)"+element.getText()+" 4)"+element.getKriterien()+
                        " 5)"+element.getMehrwert() + " 6)" + element.getStrafe()+ " 7)" +element.getAufwand()+ " 8)" +element.getRisk() ));
    }
}
