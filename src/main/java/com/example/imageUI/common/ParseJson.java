package com.example.imageUI.common;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ParseJson {

    HashMap<String, Float> map;
    public ParseJson(String text) {

        JsonObject jsonObject = new JsonParser().parse(text).getAsJsonObject();

        JsonObject jsonObject2 = jsonObject.getAsJsonObject("result");

        JsonArray tags = jsonObject2.getAsJsonArray("tags");

        map = new HashMap<>();

        for (int i = 0; i < tags.size(); i++) {
            JsonElement jsonElement = tags.get(i);
            System.out.println(jsonElement.toString());
            JsonObject jsonObject3 = tags.get(i).getAsJsonObject();
            JsonObject jsonObject4 = jsonObject3.get("tag").getAsJsonObject();

            System.out.println(jsonObject3.get("confidence").toString() + " " + jsonObject4.get("en").toString());

            map.put(jsonObject4.get("en").toString(), Float.valueOf(jsonObject3.get("confidence").toString()));
        }

        map.entrySet().stream().sorted(Map.Entry.<String, Float>comparingByValue().reversed()).forEach(stringFloatEntry -> System.out.println(stringFloatEntry.getKey() + "|" + stringFloatEntry.getValue()));
    }
}
