package Model;

public interface AnalyzeStrategy {
     void analyzeAll(String hints, String details);

    void analyze(int id, String hints, String details);
}
