package com.headgehogtech.chucknorrisjokes.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.headgehogtech.chucknorrisjokes.Models.JokeModel;
import com.headgehogtech.chucknorrisjokes.R;

import java.util.ArrayList;

public class JokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private ArrayList<JokeModel> jokesList = new ArrayList<>();

    public void setupJokes(ArrayList<JokeModel> jokeModels){
        jokesList.clear();
        jokesList.addAll(jokeModels);
        notifyDataSetChanged();
    }

    public ArrayList<JokeModel> getJokesList(){
        return jokesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_jokes_list, parent, false);
        return new JokesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JokesViewHolder){
            ((JokesViewHolder) holder).bind(jokesList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }
}

class JokesViewHolder extends RecyclerView.ViewHolder{

    private TextView textView = itemView.findViewById(R.id.text_joke);

    JokesViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bind(final JokeModel jokeModel){
        textView.setText(jokeModel.getText());
    }
}
