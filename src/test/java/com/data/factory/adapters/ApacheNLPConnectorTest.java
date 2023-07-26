package com.data.factory.adapters;


import com.data.factory.exceptions.NLPBridgeException;
import com.data.factory.models.TaggedObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ApacheNLPConnectorTest {

    @Test
    void getPartOfSpeechTest() throws NLPBridgeException, JsonProcessingException {
        ApacheNLPConnector apacheNLP = new ApacheNLPConnector();
        String text = "Sir Isaac Newton was an English mathematician, physicist, astronomer and author was described";
        String language = "en";
        ArrayList<TaggedObject> tokens = apacheNLP.getPartOfSpeech(text, language);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        System.out.println(objectWriter.writeValueAsString(tokens));

        Assertions.assertTrue(!tokens.isEmpty());
    }
}
