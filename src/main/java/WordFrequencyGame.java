import java.util.*;

public class WordFrequencyGame {
//    rename: getresult, input, arr
//    stream
//    useless code, import, if else
//    reformat, empty space
//    temp field: input = list
//    unused import
//    extract getResult method
//    magic string


    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency input = new WordFrequency(word, 1);
                    wordFrequencyList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencyMap = getListMap(wordFrequencyList);

                List<WordFrequency> resultWordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencyMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    resultWordFrequencyList.add(wordFrequency);
                }
                wordFrequencyList = resultWordFrequencyList;

                wordFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String s = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>(); 
                arr.add(input);
                map.put(input.getWord(), arr);
            } else {
                map.get(input.getWord()).add(input);
            }
        }
        return map;
    }


}
