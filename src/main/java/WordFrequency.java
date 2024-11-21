
public class WordFrequency {
    private String word;
    private int frequency;

    public WordFrequency(String word, int count) {
        this.word = word;
        this.frequency = count;
    }

    public WordFrequency(String word) {
        this.word = word;
        this.frequency = 1;
    }

    public String getWord() {
        return this.word;
    }

    public int getFrequency() {
        return this.frequency;
    }

}
