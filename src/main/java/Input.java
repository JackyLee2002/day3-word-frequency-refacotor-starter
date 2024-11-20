
public class Input {
    private String value; // word
    private int count;

    //    smell: naming
    public Input(String w, int i) {
        this.value = w;
        this.count = i;
    }

    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }


}
