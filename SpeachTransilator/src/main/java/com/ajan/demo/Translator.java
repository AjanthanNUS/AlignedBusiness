package com.ajan.demo;

import java.util.*;

public class Translator {

    public static Map<String, List<String>> mapSpeakerToDialogs(List<String> dialogs) {
        HashMap<String, List<String>> speakerDialog = new HashMap<>();
        dialogs.stream().forEach(s -> {
            String[] strings = s.split(":");
            if (strings.length == 2) {
                //Clean dialogs before adding to map
                List<String> cleanedPhrases = DialogDecoder.cleanDialog(strings[1].trim());

                speakerDialog.put(strings[0].trim(), DialogDecoder.recognizePhrase(cleanedPhrases));
            }
        });
        return speakerDialog;
    }

    public static String translatePhrase(String dolPhrase) {
        Properties props = FileManager.loadProperties();
        return props.getProperty(dolPhrase, null);
    }

    public static void translateDolphinToHuman(String dolphinFileName, String humanDestFilePath) {
        List<String> lines = FileManager.readFileByLines(dolphinFileName);
        Map<String, List<String>> speakerDialogs = Translator.mapSpeakerToDialogs(lines);
        StringBuilder humanDialogs = new StringBuilder();
        speakerDialogs.forEach((speaker, phrases) -> {
            humanDialogs.append(speaker).append(": ");

            phrases.forEach(phrase -> {
                String humPhrase = Translator.translatePhrase(phrase);
                humanDialogs.append(humPhrase).append(" ");
            });

            humanDialogs.append("\n");
        });
        String translatedToHuman = humanDialogs.toString().trim();

        System.out.println(translatedToHuman);

        FileManager.writeToFile(humanDestFilePath, translatedToHuman);
    }
}
