package com.ajan.demo;

import java.util.*;

public class DialogDecoder {

    public static Set<Character> getReconChars() {
        Properties props = FileManager.loadProperties();
        Set<Character> reconChars = new HashSet<>();
        Set<String> propNames = props.stringPropertyNames();
        propNames.forEach(s -> {
            char[] charsInWord = s.toCharArray();
            for (char c : charsInWord) {
                reconChars.add(c);
            }
        });
        return reconChars;
    }

    public static List<String> cleanDialog(String dialog) {

        Properties props = FileManager.loadProperties();
        Set<String> propNames = props.stringPropertyNames();

        String[] phrases = dialog.split(" ");
        List<String> cleanedPhrases = new ArrayList<>();

        for (String phrase : phrases) {
            StringBuilder cleanedPhrase = null;
            for (String dolpWord : propNames) {
                char[] dolpLetters = dolpWord.toCharArray();
                cleanedPhrase = new StringBuilder();
                int lastSuccessIndex = -1;
                for (char dc : dolpLetters) {
                    int index = phrase.indexOf(dc, lastSuccessIndex + 1);
                    if (index > lastSuccessIndex) {
                        lastSuccessIndex = index;
                        cleanedPhrase.append(dc);
                    } else {
                        //If one letter is missing then the word is not there
                        break;
                    }
                }
                if (cleanedPhrase.toString().equals(dolpWord)) {
                    break;
                }
            }
            if (cleanedPhrase != null && cleanedPhrase.length() > 0) {
                cleanedPhrases.add(cleanedPhrase.toString());
            }
        }
        return cleanedPhrases;
    }

    public static List<String> recognizePhrase(List<String> cleanedPhrases) {
        List<String> phraseList = new ArrayList<>();
        Properties props = FileManager.loadProperties();
        Set<String> propNames = props.stringPropertyNames();

        for (String phrase : cleanedPhrases) {
            if (propNames.contains(phrase)) {
                phraseList.add(phrase);
            }
        }
        return phraseList;
    }

}
