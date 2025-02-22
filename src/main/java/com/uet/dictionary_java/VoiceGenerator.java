package com.uet.dictionary_java;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceGenerator {
    private static VoiceGenerator instance;

    private VoiceGenerator() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    }

    public static VoiceGenerator getInstance() {
        if (instance == null) {
            instance = new VoiceGenerator();
        }
        return instance;
    }

    public void speak(String word) {
        if (word.isEmpty()) return;

        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        if (voice != null) {
            voice.allocate();
            voice.speak(word);
            voice.deallocate();
        } else {
            System.out.println("Voice not found.");
        }
    }
}
