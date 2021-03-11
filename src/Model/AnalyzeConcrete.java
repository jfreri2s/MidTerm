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
        ds =  new LinkedList<String>();; // list of userstory details
        qs= new LinkedList<>(); // list of quality
    }

    @Override
    public void analyzeAll() {

    }
    public void addtoLists(int quality, String hints, String details){
        qs.add(quality);
        hs.add(hints);
        ds.add(details);
    }
    @Override
    public void analyze(int id, String hints, String details) {
        String[] uST = null;
        Userstory cur = null;
        for(Userstory us : uSL){
            if(us.getId() == id){
                cur = us;
            }
        }
        if(cur == null){
            //throw new Exception("Die Userstory mit der ID " + cur.getID() + " ist nicht vorhanden!");
            //TODO: Write more specific exceptiontype
        }
        uST = cur.getText().split(" ");
        if(cur.getText().length() == 0){
            //throw new Exception("Userstory hat keinen Inhalt")
        }
        if(!uST[0].toLowerCase().equals("als")){
            //als möglicher hint "Beginnt nicht mit Als als erstes Wort";

        }

        if(!aL.contains(uST[1].toLowerCase())){
            //wrong actor
            addtoLists(-5,"Registrieren sie einen neuen Akteur!","Akteur ('\"" + uST[1] + "\"') ist nicht bekannt (- 20% )");
        }

        if(! uST[2].toLowerCase().equals("möchte")){
            addtoLists(-5,"","");
        }
        int tempIdx = 0;
        for(int idx = 3; idx < uST.length; idx++ ){
            if(uST[idx].equals("um") ){
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
            else{
                //hint - kein Mehrwert vorhanden
                addtoLists(-5,"Fügen sie einen schriftlichen Mehrwert hinzu!","Kein schriftlicher Mehrwert zu erkennen (- 30%)");
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
        System.out.println("Die User Story mit der ID "+ cur.getId()+" hat folgende Qualität: " + qs.get(0));
        //TODO what to do about the persistent storage of actors?

    }
}
