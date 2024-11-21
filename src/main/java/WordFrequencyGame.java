import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";
    public static final String SPACE_CHAR = " ";

    public String getWordFrequency(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = getInitialWordFrequencies(sentence);

            wordFrequencyList = getWordFrequencies(wordFrequencyList);

            return getJoinResult(wordFrequencyList);

        } catch (Exception e) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

        return wordToWordFrequenciesMap.entrySet().stream()
                .map(wordEntry -> new WordFrequency(wordEntry.getKey(), wordEntry.getValue().size()))
                .sorted(Comparator.comparingInt(WordFrequency::getFrequency).reversed())
                .toList();
    }

    private static String getJoinResult(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .map(wordFrequency -> (wordFrequency.getWord() + SPACE_CHAR + wordFrequency.getFrequency()))
                .collect(Collectors.joining(NEW_LINE));
    }

    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        return Arrays.stream(words)
                .map(word -> new WordFrequency(word))
                .toList();
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
