package Model;

public interface AnalyzeStrategy {
    void analyzeAll(String hints, String details) throws AnalyzeException;

    void analyze(int id, String hints, String details) throws AnalyzeException;
}
