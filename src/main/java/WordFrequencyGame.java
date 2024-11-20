import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";
    //    rename: getresult, input, arr
//    stream
//    useless code, import, if else
//    reformat, empty space
//    temp field: input = list
//    unused import
//    extract getResult method
//    magic string



    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<WordFrequency> wordFrequencyList = GetInitialWordFrequencies(sentence);

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

                List<WordFrequency> resultWordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequenciesMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    resultWordFrequencyList.add(wordFrequency);
                }

                wordFrequencyList = resultWordFrequencyList;

                wordFrequencyList.sort((currentWord, nextWord) -> nextWord.getWordCount() - currentWord.getWordCount());

                return  wordFrequencyList.stream()
                        .map(wordFrequency -> (wordFrequency.getWord() + " " + wordFrequency.getWordCount()))
                        .collect(Collectors.joining(NEW_LINE));

            } catch (Exception e) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private static List<WordFrequency> GetInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        List<WordFrequency> wordFrequencyList = Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
        return wordFrequencyList;
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
