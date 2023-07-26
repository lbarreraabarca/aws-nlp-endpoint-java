package com.data.factory.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class PartOfSpeechResponse {

    @JsonProperty private ArrayList<TaggedObject> result;
}
