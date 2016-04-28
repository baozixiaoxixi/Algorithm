package C04;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 包子 on 2016/4/28.
 */
public class WordMapDemo {

    //打印所有要求的答案
    //给出包含一些单词作为关键字和只在一个字母上不同的一列单词作为关键字的值，输出那些具有minWords个或更多通过1字母替换得到的单词的单词
    public static void printHighChangeables(Map<String, List<String>> adjWords, int minWords) {
        for (Map.Entry<String, List<String>> entry : adjWords.entrySet()) {
            List<String> words = entry.getValue();

            if (words.size() >= minWords) {
                System.out.print(entry.getKey() + "(");
                System.out.print(words.size() + "):");
                for (String w : words) {
                    System.out.print(" " + w);
                }
                System.out.println();
            }
        }
    }

    //检测两个单词是否在一个字母上不同
    private static boolean oneCharOff(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int diffs = 0;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++diffs > 1) {
                    return false;
                }
            }
        }
        return diffs == 1;
    }

    //计算一个map对象的函数，该对象以一些单词作为关键字而以只在一个字母处不同的一列单词作为关键字的值
    //该函数对一个89000的单词运行96s
    public static Map<String, List<String>> computeAdjacentWords(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();

        String[] words = new String[theWords.size()];

        theWords.toArray(words);//Appends the specified element to the end of this list
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (oneCharOff(words[i], words[j])) {
                    update(adjWords, words[i], words[j]);
                    update(adjWords, words[j], words[i]);
                }
            }
        }
        return adjWords;
    }

    //计算一个映射的函数，该对象以一些单词作为关键字而以只在一个字母处不同的一列单词作为关键字的值
    //将单词按照长度分组，该函数对一个89000的单词运行51s
    public static Map<String, List<String>> computeAdjacentWords1(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();

        for (String w : theWords) {     //Group the words by their length
            update(wordsByLength, w.length(), w);
        }

        for (List<String> groupsWords : wordsByLength.values()) {       //Work on each group separately
            String[] words = new String[groupsWords.size()];

            groupsWords.toArray(words);
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    if (oneCharOff(words[i], words[j])) {
                        update(adjWords, words[i], words[j]);
                        update(adjWords, words[j], words[i]);
                    }
                }
            }
        }

        return adjWords;
    }

    //计算包含单词作为关键字而以只在一个字母处不同的一列单词作为映射的函数
    //将单词按照长度分组，该函数对一个89000的单词运行4s
    public static Map<String, List<String>> computeAdjacentWords2(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();

        for (String w : theWords) {     //Group the words by their length
            update(wordsByLength, w.length(), w);
        }

        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {       //Work on each position in each group
            List<String> groupsWords = entry.getValue();
            int groupNum = entry.getKey();

            for (int i = 0; i < groupNum; i++) {
                Map<String, List<String>> repToWord = new TreeMap<>();

                for (String str : groupsWords) {
                    String rep = str.substring(0, i) + str.substring(i + 1);
                    update(repToWord, rep, str);
                }

                for (List<String> wordClique : repToWord.values()) {
                    if (wordClique.size() > 2) {
                        for (String s1 : wordClique) {
                            for (String s2 : wordClique) {
                                if (s1 != s2) {
                                    update(adjWords, s1, s2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return adjWords;
    }

    private static <KeyType> void update(Map<KeyType, List<String>> m, KeyType key, String value) {
        List<String> lst = m.get(key);
        if (lst == null) {
            lst = new ArrayList<>();
            m.put(key, lst);
        }

        lst.add(value);
    }


}
