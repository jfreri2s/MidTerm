package Model;

import java.util.LinkedList;

public class AnalyzeConcrete implements AnalyzeStrategy{
    private LinkedList<Userstory> uSL;
    private LinkedList<Actor> aL;
    LinkedList<String> hs; // list of hints for the user
    LinkedList<String> ds;; // list of userstory details
    LinkedList<Integer> qs; // list of quality
    public AnalyzeConcrete(LinkedList<Userstory> uSL,LinkedList<Actor> aL) {
        this.uSL = uSL;
        this.aL = aL;
        hs = new LinkedList<String>(); // list of hints for the user
        ds = new LinkedList<String>();; // list of userstory details
        qs = new LinkedList<>(); // list of quality
    }

    @Override
    public void analyzeAll(String hints, String details) throws AnalyzeException {
        int sum = 0;
        double avgQs = 0;
        for(Userstory us : uSL){
            processAnalysis(us.getId(),hints,details);
        }
        for(Integer i : qs){
            sum += i;
        }
        if(uSL.size() == 1){
            String g = grade(sum);
            String ans = "Ihre Userstory hat durchschnittlich folgende Qualität: \n"+ (100+sum) + "% (" + g + ")\n";
            ausgabe(hints, details, ans);
        } else {
            avgQs = (double) sum/qs.size();
            String g = grade((int) avgQs);
            String ans = "Ihre " + uSL.size() + " Userstories haben durchschnittlich folgende Qualität: \n"+ avgQs + "% (" + g + ")\n";
            ausgabe(hints, details, ans);
        }
    }
    private String grade(int sum){
        String ans = "";

        if(sum>= 90){
            ans = "Sehr gut";
        }
        if(sum>= 75){
            ans = "Gut";
        }
        if(sum>= 60){
            ans = "Befriedigend";
        }
        if(sum>= 50){
            ans = "Ausreichend";
        }
        if(sum<= 50){
            ans = "Ungenügend";
        }
        return ans;
    }
    private void ausgabe(String hints, String details, String ans) {
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
    }

    public void addtoLists(int quality, String hints, String details){
        qs.add(quality);
        hs.add(hints);
        ds.add(details);
    }
    @Override
    public void analyze(int id, String hints, String details) throws AnalyzeException {
        processAnalysis(id,hints,details);
        int sum = 0;
        for(Integer i : qs){
            sum += i;
        }
        String g = grade(sum);
        String ans = "Die User Story mit der ID "+ id+" hat folgende Qualität: \n" + (100+sum)+ "% (" + g + ")\n";
        ausgabe(hints, details, ans);
        //TODO what to do about the persistent storage of actors?
    }
    public void processAnalysis(int id, String hints, String details) throws AnalyzeException {
        String[] uST = null;
        Userstory cur = null;
        for(Userstory us : uSL){
            if(us.getId() == id){
                cur = us;
            }
        }
        if(cur == null){
            throw new AnalyzeException("Die Userstory mit der ID " + id + " ist nicht vorhanden!");

        }
        uST = cur.getText().split(" ");
        if(cur.getText().length() == 0){
            throw new AnalyzeException("Userstory hat keinen Inhalt");
        }
        //TODO if no actors are registered there must be a notification!
        if(aL.isEmpty()){
            System.out.println("Es sind keine Akteure registriert\n");
            addtoLists(-20,"Registrieren sie einen neuen Akteur!","Akteur ('" + uST[1] + "') ist nicht bekannt (- 20%)");
            for(Actor a : aL){
                if(!a.getName().toLowerCase().equals(uST[1].toLowerCase())){
                    //wrong actor
                    addtoLists(-20,"Registrieren sie einen neuen Akteur!","Akteur ('" + uST[1] + "') ist nicht bekannt (- 20%)");
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
