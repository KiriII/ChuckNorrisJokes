package com.headgehogtech.chucknorrisjokes.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class JokeModel implements Parcelable {

    private String text;

    public JokeModel(String text){
        this.text = text;
    }

    public JokeModel(Parcel in) {
        text = in.readString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
    }

    public static final Parcelable.Creator<JokeModel> CREATOR = new Parcelable.Creator<JokeModel>(){
        @Override
        public JokeModel createFromParcel(Parcel source) {
            return new JokeModel(source);
        }

        @Override
        public JokeModel[] newArray(int size) {
            return new JokeModel[size];
        }
    };
}
