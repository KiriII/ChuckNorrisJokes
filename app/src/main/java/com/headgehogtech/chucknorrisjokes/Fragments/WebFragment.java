package com.headgehogtech.chucknorrisjokes.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.headgehogtech.chucknorrisjokes.Models.CustomWebViewClient;
import com.headgehogtech.chucknorrisjokes.R;
import com.headgehogtech.chucknorrisjokes.Views.WebFragmentView;

public class WebFragment extends Fragment implements WebFragmentView{

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    private void webViewInit(View view){
        webView = view.findViewById(R.id.web_page);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.icndb.com/api/");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_web, container, false);
        webViewInit(root);
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", webView.getUrl());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            webView.loadUrl(savedInstanceState.getString("url"));
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        }
    }
}