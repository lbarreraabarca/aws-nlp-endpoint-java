package com.data.factory.adapters;

import com.data.factory.exceptions.NLPBridgeException;
import com.data.factory.models.TaggedObject;
import com.data.factory.ports.NLPBridge;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ApacheNLPConnector implements NLPBridge {
    private static final Logger LOG = LoggerFactory.getLogger(ApacheNLPConnector.class);
    private String ERROR_MESSAGE = "%s cannot be null or empty.";
    private HashMap<String, String> partOfSpeechLanguages;

    public ApacheNLPConnector(){
        this.partOfSpeechLanguages = new HashMap<>();
        this.partOfSpeechLanguages.put("en", "/models/en-pos-maxent.bin");
    }

    @Override
    public Boolean isValidPartOfSpeechLanguage(String language) throws NLPBridgeException{
        if (language == null || language.isEmpty()) throw new NLPBridgeException(String.format(ERROR_MESSAGE, "language"));
        return this.partOfSpeechLanguages.containsKey(language);
    }

    @Override
    public ArrayList<TaggedObject> getPartOfSpeech(String text, String language) throws NLPBridgeException {
        if (text == null || text.isEmpty()) throw new NLPBridgeException(String.format(ERROR_MESSAGE, "text"));
        if (language == null || language.isEmpty()) throw new NLPBridgeException(String.format(ERROR_MESSAGE, "language"));
        try {
            SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
            String[] tokens = tokenizer.tokenize(text);
            InputStream inputStream = getClass().getResourceAsStream(this.partOfSpeechLanguages.get(language));
            POSModel posModel = new POSModel(inputStream);
            POSTaggerME posTaggerME = new POSTaggerME(posModel);
            ArrayList<String> elements = new ArrayList<>((Arrays.asList(tokens)));
            ArrayList<String> tagged = new ArrayList<>(Arrays.asList(posTaggerME.tag(tokens)));
            ArrayList<TaggedObject> result = new ArrayList<>();
            for (int i=0; i < elements.size(); i++) {
                TaggedObject taggedObject = new TaggedObject(elements.get(i).toString(), tagged.get(i).toString());
                result.add(taggedObject);
            }
            return result;
        } catch (Exception e){
            throw new NLPBridgeException(String.format("%s %s", e.getClass(), e.getMessage()));
        }
    }
}
