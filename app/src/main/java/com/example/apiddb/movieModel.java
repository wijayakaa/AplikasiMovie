package com.example.apiddb;

import android.os.Parcel;
import android.os.Parcelable;

public class movieModel implements Parcelable {

    private String FilmName;
    private String Rate;
    private String Language;
    private String Deskripsi;
    private String Date;
    private String logoFilm;

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    protected movieModel(Parcel in) {
        FilmName = in.readString();
        Deskripsi = in.readString();
        Rate = in.readString();
        Language = in.readString();
        Date = in.readString();
        logoFilm = in.readString();
    }
    movieModel(){

    }

    public static final Creator<movieModel> CREATOR = new Creator<movieModel>() {
        @Override
        public movieModel createFromParcel(Parcel in) {
            return new movieModel(in);
        }

        @Override
        public movieModel[] newArray(int size) {
            return new movieModel[size];
        }
    };

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFilmName() {
        return FilmName;
    }

    public void setFilmName(String filmName) {
        FilmName = filmName;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getLogoFilm() {
        return logoFilm;
    }

    public void setLogoFilm(String logoFilm) {
        this.logoFilm = logoFilm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(FilmName);
        parcel.writeString(Deskripsi);
        parcel.writeString(Rate);
        parcel.writeString(Language);
        parcel.writeString(Date);
        parcel.writeString(logoFilm);
    }
}