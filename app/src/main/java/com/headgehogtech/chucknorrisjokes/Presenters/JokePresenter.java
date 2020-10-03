package com.headgehogtech.chucknorrisjokes.Presenters;

import android.util.Log;

import com.headgehogtech.chucknorrisjokes.Models.JokeModel;
import com.headgehogtech.chucknorrisjokes.Views.JokesView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JokePresenter {

    private ArrayList<JokeModel> jokeModels;
    private JokesView jokesFragment;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public JokePresenter(JokesView jokesFragment){
        this.jokesFragment = jokesFragment;
        jokeModels = new ArrayList<>();
    }

    public void loadTestJokes(int count){
        jokeModels = new ArrayList<>();
        for (int i = 0; i < count; i++){
            jokeModels.add(new JokeModel("Joke about Chuck Norris " + i));
        }
        jokesFragment.setJokesList(jokeModels);
    }

    public void loadJokes(int count){
        jokeModels = new ArrayList<>();

        String url = "https://api.icndb.com/jokes/random/";
        Request request = new Request.Builder()
                .url(url +count)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                parseResponse(response);
                jokesFragment.setJokesList(jokeModels);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Callback error", e.toString());
            }
        } );
    }

    private void parseResponse(Response response) throws IOException {
        try {
            JSONArray jokes = new JSONObject(Objects.requireNonNull(response.body()).string())
                    .getJSONArray("value");

            for (int i = 0; i < jokes.length(); i++) {
                jokeModels.add(new JokeModel(jokes.getJSONObject(i).getString("joke")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
