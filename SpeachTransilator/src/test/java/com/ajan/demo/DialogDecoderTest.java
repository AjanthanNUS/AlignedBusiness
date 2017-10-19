package com.ajan.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DialogDecoderTest {

    @Test
    public void testGetReconChars() {
        Set<Character> reconChars = DialogDecoder.getReconChars();
        Assert.assertEquals("Expecting 5 characters ", 5, reconChars.size());
    }

    @Test
    public void testCleanDialog() {
        String dirty = "ehhhhk#@#$%thisisawonderfulworld";
        List<String> clean = DialogDecoder.cleanDialog(dirty);
        Assert.assertEquals("Expecting ehhhk", "ehhhk", clean.get(0));
    }

    @Test
    public void testRecognizePhrase() {
        String s = "eeeehk! ehhhk ekhhh ehhhk ehhhkfdfd ";
        List<String> phrases = DialogDecoder.recognizePhrase(Arrays.asList(s.split(" ")));
        Assert.assertEquals("Expecting 4 of size list", 4, phrases.size());
    }
}

