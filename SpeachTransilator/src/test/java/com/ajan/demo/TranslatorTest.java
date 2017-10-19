package com.ajan.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TranslatorTest {
    List<String> lines;

    @Before
    public void init() {
        lines = new ArrayList<>();
        lines.add("dolphin_1: eeeehk! ehhhk ekhhh ehhhk");
        lines.add("dolphin_2: eeeehk?");
    }

    @Test
    public void testMapSpeakerToDialogs() {
        Map<String, List<String>> stringListMap = Translator.mapSpeakerToDialogs(lines);
        Assert.assertEquals("Expecting two in size", 2, stringListMap.size());
    }

    @Test
    public void testTranslatePhrase() {
        String humPhrase = Translator.translatePhrase("eeeehk!");
        Assert.assertEquals("Expecting hello", "hello", humPhrase);

        humPhrase = Translator.translatePhrase("eeeehk");
        Assert.assertEquals("Expecting null", null, humPhrase);
    }
}
