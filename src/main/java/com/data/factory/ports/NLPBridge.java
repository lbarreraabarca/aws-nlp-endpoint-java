package com.data.factory.ports;

import com.data.factory.exceptions.NLPBridgeException;
import com.data.factory.models.TaggedObject;

import java.util.ArrayList;

public interface NLPBridge {

    ArrayList<TaggedObject> getPartOfSpeech(String text, String language) throws NLPBridgeException;
    Boolean isValidPartOfSpeechLanguage(String language) throws NLPBridgeException;
}
