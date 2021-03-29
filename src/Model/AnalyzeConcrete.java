package Model;

import java.util.LinkedList;

public class AnalyzeConcrete implements AnalyzeStrategy{
    private LinkedList<Userstory> uSL;
    private LinkedList<Actor> aL;
    LinkedList<String> hs = new LinkedList<String>(); // list of hints for the user
    LinkedList<String> ds =  new LinkedList<String>();; // list of userstory details
    LinkedList<Integer> qs= new LinkedList<>(); // list of quality
    public AnalyzeConcrete(LinkedList<Userstory> uSL,LinkedList<Actor> aL) {
        this.uSL = uSL;
        this.aL = aL;
        hs = new LinkedList<String>(); // list of hints for the user
        ds = new LinkedList<String>();; // list of userstory details
        qs = new LinkedList<>(); // list of quality
    }

    @Override
    public void analyzeAll(String hints, String details) {
        int sum = 0;
        double avgQs = 0;
        for(Userstory us : uSL){
            processAnalysis(us.getId(),hints,details);
        }
        for(Integer i : qs){
            sum += i;
        }
        avgQs = (double) sum/qs.size();
        System.out.println("Ihre " + uSL.size() + "Userstories haben durchschnittlich folgende Qualität: "+ avgQs); //TODO rating of the quality of the userstories wiht grade in words
    }
    public void addtoLists(int quality, String hints, String details){
        qs.add(quality);
        hs.add(hints);
        ds.add(details);
    }
    @Override
    public void analyze(int id, String hints, String details) {
        processAnalysis(id,hints,details);
        int sum = 0;
        for(Integer i : qs){
            sum += i;
        }

        String ans = "Die User Story mit der ID "+ id+" hat folgende Qualität: \n" + (100+sum)+ "%\n";
        if(details != null && details.equals("details") ){
            ans+= "\nDetails: \n";
            for(String s: ds)
                ans+=s + "\n";
        }
        if( hints != null && hints.equals("hints") ){
            ans+="\nHints: \n";
            for(String s: hs)
                ans+=s + "\n";
        }
        System.out.println(ans);
        //TODO rating of the quality of the userstories wiht grade in words
        //TODO what to do about the persistent storage of actors?

    }
    public void processAnalysis(int id, String hints, String details){
        String[] uST = null;
        Userstory cur = null;
        for(Userstory us : uSL){
            if(us.getId() == id){
                cur = us;
            }
        }
        //qs.add(100);

        if(cur == null){
            //throw new Exception("Die Userstory mit der ID " + cur.getID() + " ist nicht vorhanden!");
            //TODO: Write more specific exceptiontype
        }
        uST = cur.getText().split(" ");
        if(cur.getText().length() == 0){
            //throw new Exception("Userstory hat keinen Inhalt")
        }
        //TODO if no actors are registered there must be a notification!
        if(aL.isEmpty()){
            addtoLists(-20,"Registrieren sie einen neuen Akteur!","Akteur ('\"" + uST[1] + "\"') ist nicht bekannt (- 20% )");
            for(Actor a : aL){
                if(!a.getName().toLowerCase().equals(uST[1].toLowerCase())){
                    //wrong actor
                    //TODO addtoLists is not appropriate because a base quality must be set and then decreased
                    //currently several qualities are added to a quality list
                    //either sum the list up and add it to 100 or only change the preset 100 with the decreased value.
                    addtoLists(-20,"Registrieren sie einen neuen Akteur!","Akteur ('\"" + uST[1] + "\"') ist nicht bekannt (- 20% )");
                    break;
                }
            }
        }
        int tempIdx = 0;
        boolean umIsStated = false;
        for(int idx = 3; idx < uST.length; idx++ ){
            if(uST[idx].equals("um") ){
                if(idx == 6){
                    umIsStated = true;
                }
                if(idx < 6){
                    // too short or not sufficient information supplied
                    addtoLists(-5,"Die funktionale Beschreibung sollte länger als drei Wörter sein!","Ihre funktionale Beschreibung ist zu kurz (- 5%)");
                }
                if(idx > 10){
                    // too long
                    addtoLists(-5,"Die funktionale Beschreibung sollte nicht länger als acht Wörter sein!","Ihre funktionale Beschreibung ist zu lang (- 5%)");
                }
                tempIdx = idx;
                continue;
            }
            if(uST[idx].endsWith(".")){
                if(idx < 13){
                    // hint - zu geringer Mehrwert
                    addtoLists(-5,"Der Mehrwert sollte länger als drei Wörter sein!","Ihr Mehrwert ist nicht ausreichend beschrieben (- 5%)");
                }
                if(idx > 23){
                    // hint zu großer Mehwert
                    addtoLists(-5,"Der Mehrwert sollte nicht länger als zehn Wörter sein!","Ihr Mehrwert ist zu ausführlich beschrieben  (- 5%)");
                }
            }
        }
        if(!umIsStated){
            addtoLists(-30,"Fügen sie einen schriftlichen Mehrwert hinzu!","Kein schriftlicher Mehrwert zu erkennen (- 30%)");
        }
    }
}
