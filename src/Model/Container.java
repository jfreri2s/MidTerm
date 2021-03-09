package Model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Container {

    private PersistenceStrategy<Userstory> pSS;
    private static LinkedList<Userstory> uSL;
    private static LinkedList<Actor> aL;
    private static Container instance = null;
    private static final Object lock = new Object();

    // Implementationg of the methods for the  existing Commands
    private Container(){
         uSL = new LinkedList<>();
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
    public void analyze(List<String> args){
        // param details hints all
        AnalyzeStrategy astrat = new AnalyzeConcrete(uSL,aL);
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

    public void addUserStory (Userstory r ) throws ContainerException {
        if ( contains(r) == true ) {
            throw new ContainerException("ID bereits vorhanden!");
        }
        uSL.add(r);

    }
    private boolean contains(Userstory u) {
        for ( Userstory a : uSL) {
            if (a.getId().equals(u.getId())) {
                return true;
            }
        }
        return false;
    }
    public int size(){
        return uSL.size();
    }

    public void store() throws PersistenceException {
        if(pSS == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"NoStrategyIsSet");
        }
        pSS.save(uSL);
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
        uSL = (LinkedList<Userstory>) pSS.load();
    }
    public List<Userstory> getCurrentList(){
        return uSL;
    }

    public void setStrategy(Model.PersistenceStrategyStream pSS) {
        this.pSS = pSS;
    }

    //TODO: either implement a specialized dump method for status or one where the status will be queried
    public void dump(List<Userstory> liste){
        System.out.println("1)Prio 2)ID 3)Text 4) Kriterien 5)Mehrwert 6)Strafe 7)Aufwand 8) Risiko");
        liste.stream()
                .sorted(Comparator.comparing(Userstory::getPrio))
                .filter(element -> element.getAufwand()>4)
                .forEach(element -> System.out.println(" 1)" + element.getPrio()+" 2)"+element.getId()+" 3)"+element.getText()+" 4)"+element.getKriterien()+
                        " 5)"+element.getMehrwert() + " 6)" + element.getStrafe()+ " 7)" +element.getAufwand()+ " 8)" +element.getRisk() ));
    }
}
