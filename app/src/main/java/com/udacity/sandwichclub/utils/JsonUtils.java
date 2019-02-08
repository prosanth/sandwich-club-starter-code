package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJSON = new JSONObject(json);
            JSONObject name = sandwichJSON.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray akaArray =  name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            if(akaArray.length() == 0) alsoKnownAs.add("None");
            for (int i=0; i< akaArray.length(); i++)
                alsoKnownAs.add(akaArray.getString(i));
            String placeOfOrigin = sandwichJSON.getString("placeOfOrigin");
            if(placeOfOrigin.equals("")) placeOfOrigin = "Unknown";
            String description = sandwichJSON.getString("description");
            String image = sandwichJSON.getString("image");
            JSONArray ingredientArray = sandwichJSON.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int j=0; j< ingredientArray.length(); j++)
                ingredients.add(ingredientArray.getString(j));
            Sandwich chosenSandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return chosenSandwich;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
