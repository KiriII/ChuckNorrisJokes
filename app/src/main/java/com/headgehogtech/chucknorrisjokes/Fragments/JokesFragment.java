package com.headgehogtech.chucknorrisjokes.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.headgehogtech.chucknorrisjokes.Adapters.JokeListAdapter;
import com.headgehogtech.chucknorrisjokes.Models.JokeModel;
import com.headgehogtech.chucknorrisjokes.Presenters.JokePresenter;
import com.headgehogtech.chucknorrisjokes.R;
import com.headgehogtech.chucknorrisjokes.Views.JokesView;

import java.util.ArrayList;
import java.util.Objects;

public class JokesFragment extends Fragment implements JokesView {

    private RecyclerView jokesRecycleVeiw;
    private EditText jokesCountEditText;
    private Button jokesReloadButton;
    private JokeListAdapter jokeListAdapter;
    private JokePresenter jokePresenter;

    private void initViews(View view){
        jokesRecycleVeiw = view.findViewById(R.id.jokes_list);
        jokesCountEditText = view.findViewById(R.id.edit_jokes_count);
        jokesReloadButton = view.findViewById(R.id.button_reload);
    }

    private void initRecyclerView(){
        jokePresenter = new JokePresenter(this);
        jokeListAdapter = new JokeListAdapter();
        jokesRecycleVeiw.setAdapter(jokeListAdapter);
        jokesRecycleVeiw.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        jokesRecycleVeiw.setHasFixedSize(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jokes, container, false);

        initViews(root);
        initRecyclerView();

        jokesReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = jokesCountEditText.getText().toString();
                if (text.length() > 0) {
                    jokePresenter.loadJokes(Integer.parseInt(text));
                }
            }
        });
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("jokeModels", jokeListAdapter.getJokesList());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            ArrayList<JokeModel> jokeModels = savedInstanceState.getParcelableArrayList("jokeModels");
            setJokesList(jokeModels);
        }
    }

    public void setJokesList(final ArrayList<JokeModel> jokeModels){
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                jokeListAdapter.setupJokes(jokeModels);
            }
        });
    }
}