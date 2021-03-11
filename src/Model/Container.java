package Model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

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

        AnalyzeStrategy astrat = new AnalyzeConcrete(uSL,aL);

        if(checkDigit(args.get(0))){
            astrat.analyze(Integer.parseInt(args.get(0)),null,null);
        }
        else{
            // param details hints all
            if(args.get(0).equals("-") && args.get(1).equals("all")){
                // param ==  all, alle userstories müssen ausgegeben werden.

            }
            else {
                if (args.get(1).equals("-") && args.get(2).equals("details")) {
                    if (args.get(1).equals("-") && args.get(4).equals("hints")) {
                        //es werden zusätzlich zu den details weitere Hinweise angezeigt
                    } else {
                        //nur details werden angezeigt
                    }
                }

            }
        }

    }
    public void enter() throws ContainerException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Geben sie eine ID an");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Geben sie einen Text an");
        String text = sc.nextLine();
        System.out.println("Geben sie Kriterien an");
        String krit = sc.nextLine();
        System.out.println("Geben sie einen Mehrwert");
        int mehrwert = sc.nextInt();
        System.out.println("Geben sie einen Strafe");
        int strafe = sc.nextInt();
        System.out.println("Geben sie einen Aufwand");
        int aufwand = sc.nextInt();
        System.out.println("Geben sie einen Risiko");
        int risko = sc.nextInt();
        Userstory bs = new Userstory(id, text, krit, mehrwert, strafe, aufwand, risko);
        addUserStory(bs);
    }
    public void addUserStory (Userstory r ) throws ContainerException {
        if (contains(r)) {
            throw new ContainerException("ID bereits vorhanden!");
        }
        uSL.add(r);

    }
    public void help(){
        System.out.println("Die möglichen Befehle sind : actors addElement analyze dump undo status enter store load exit setStrategy help");
    }
    public void status(int id, String s){
        for ( Userstory a : uSL) {
            if (a.getId().equals(id)) {
                a.setStatus(s);
            }
        }
        System.out.println("Die User Story mit der ID " + id + " wurde auf den Status " + s + " gesetzt");
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
    public void dump(List<String> args,List<Userstory> liste){
        System.out.println("1)Prio 2)ID 3)Text 4) Kriterien 5)Mehrwert 6)Strafe 7)Aufwand 8) Risiko");
        Predicate<Userstory> hasAufwand = userstory ->userstory.getAufwand()>4;
        Predicate<Userstory> hasStatus = userstory -> userstory.getStatus().equals(args.get(2));
        Predicate<Userstory> p;
        if(args.contains("status")){
            p = hasStatus;
        }
        else{
            p = hasAufwand;
        }
        uSL.stream()
                .sorted(Comparator.comparing(Userstory::getPrio))
                .filter(p)
                .forEach(element -> System.out.println(" 1)" + element.getPrio()+" 2)"+element.getId()+" 3)"+element.getText()+" 4)"+element.getKriterien()+
                        " 5)"+element.getMehrwert() + " 6)" + element.getStrafe()+ " 7)" +element.getAufwand()+ " 8)" +element.getRisk() + element.getStatus()));
    }
    public boolean checkDigit(String args){
        boolean isNumeric =  true;
        for (int i = 0; i < args.length(); i++) {
            if (!Character.isDigit(args.charAt(i))) {
                isNumeric = false;
            }
        }
        return isNumeric;
    }

    public void actors() {
        for(Actor a : aL){
            System.out.println(a.getName());
        }
    }
    public void exit(){
        System.exit(0);
    }

    public void addElement(String name) {
        String res = "";
        if(name.equals(" ") || name.length() == 0){
            //throw new InputMismatchException();
        }
        for(int idx = 0; idx < aL.size(); idx++){
            if(!(aL.get(idx).equals(name))){
                aL.add(new Actor(name));
                res = "Der Akteur Student wurde im System registriert!";
                break;
            }
            else{
                res = "Der Akteur wurde bereits im System registriert!";
            }
        }
        System.out.println(res);
    }
}
