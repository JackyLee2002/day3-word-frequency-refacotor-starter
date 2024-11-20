import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";
    public static final String SPACE_CHAR = " ";
    //    rename: getresult, input, arr
//    stream
//    useless code, import, if else
//    reformat, empty space
//    temp field: input = list
//    unused import
//    extract getResult method
//    magic string

    public String getWordFrequency(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            List<WordFrequency> wordFrequencyList = getInitialWordFrequencies(sentence);

            wordFrequencyList = getWordFrequencies(wordFrequencyList);

            return getJoinResult(wordFrequencyList);

        } catch (Exception e) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencyList) {
        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

        return wordToWordFrequenciesMap.entrySet().stream()
                .map(wordEntry -> new WordFrequency(wordEntry.getKey(), wordEntry.getValue().size()))
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .toList();
    }

    private static String getJoinResult(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .map(wordFrequency -> (wordFrequency.getWord() + SPACE_CHAR + wordFrequency.getWordCount()))
                .collect(Collectors.joining(NEW_LINE));
    }

    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
