package com.example.jasonactivity;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelContentSir {
    public static final List<Model> MODELS = new ArrayList<>();
    public static final Map<String, Model> MODELS_MAP = new HashMap<>();
private static boolean BUILT = false;

    public void jsonParse(Activity activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.JSON_url);
        // Request a string response from the provided URL.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // NEXT, we need to use GSON to turn that JSON into a model
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("pokemonGames");
                            MODELS.clear();
                            MODELS_MAP.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject pokemans = jsonArray.getJSONObject(i);
                                String name = pokemans.getString("name");
                                String favoritePokemon = pokemans.getString("favoritePokemon");
                                String description = pokemans.getString("description");
                                Model model = new Model(name, favoritePokemon, description);
                                MODELS.add(model);
                                MODELS_MAP.put(name, model);
                            }
                            if(!BUILT) {
                                activity.recreate();
                            }
                            BUILT = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // you should drop a breakpoint RIGHT HERE if you need to see the error coming back
                error.printStackTrace();
            }
        });

        queue.add(objectRequest);
    }
}
