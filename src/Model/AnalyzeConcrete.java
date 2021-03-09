package Model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;

public class AnalyzeConcrete implements AnalyzeStrategy{
    private LinkedList<Userstory> uSL;
    private LinkedList<Actor> aL;
    public AnalyzeConcrete(LinkedList<Userstory> uSL,LinkedList<Actor> aL) {
        this.uSL = uSL;
        this.aL = aL;
    }

    @Override
    public void analyzeAll() {

    }

    @Override
    public void analyze(int id, String hints, String details) {
        String[] uST = null;
        LinkedList<String> hs = new LinkedList<String>(); // list of hints for the user
        LinkedList<String> ds =  new LinkedList<String>();; // list of userstory details
        int quality = 100;
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
        if(! uST[0].toLowerCase().equals("als")){
            //als möglicher hint "Beginnt nicht mit Als als erstes Wort";

        }
        /*
        if(!aL.contains(uST[1].toLowerCase())){

            //wrong actor
            quality-=20;
            hs.add("Registrieren sie einen neuen Akteur!");
            ds.add("Akteur ('\"" + uST[1] + "\"') ist nicht bekannt (- 20% )");
        }
        */
        if(! uST[2].toLowerCase().equals("möchte")){
            quality -=5;
            hs.add("Es wurde kein");
            ds.add("");
        }
        int tempIdx = 0;
        for(int idx = 3; idx < uST.length; idx++ ){
            if(uST[idx].equals("um") ){
                if(idx < 6){
                    // too short or not sufficient information supplied
                    quality -=5;
                    hs.add("");
                    ds.add("");
                }
                if(idx > 10){
                    // too long
                    quality -=5;
                    hs.add("");
                    ds.add("");
                }
                tempIdx = idx;
                continue;
            }
            else{
                //hint - kein Mehrwert vorhanden
                quality -=0;
                hs.add("");
                ds.add("");
            }
            if(uST[idx].endsWith(".")){
                if(idx < 13){
                    // hint - zu geringer Mehrwert
                    quality -=0;
                    hs.add("");
                    ds.add("");
                }
                if(idx > 23){
                    // hint zu großer Mehwert
                    quality -=0;
                    hs.add("");
                    ds.add("");
                }
            }

        }
        System.out.println("Die User Story mit der ID "+ cur.getId()+" hat folgende Qualität: " + quality);
        //TODO what to do about the persistent storage of actors?

    }
}
