package com.example.apiddb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listMovieName extends AppCompatActivity implements movieAdapter.MovieAdapterlistener{
    RecyclerView recycled;
    ArrayList<movieModel> listDataMovie;
    private movieAdapter adapterListCode;


    public void GetMovie(){
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=5e30e788ba0016174d8c885253084699";

        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // Tangani respons berhasil
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        try {
                            JSONArray jsonArrayEPLTeam = jsonObject.getJSONArray("results");
                            for (int i = 0; i < jsonArrayEPLTeam.length(); i++) {
                                movieModel myMovie = new movieModel();
                                JSONObject jsonTeam = jsonArrayEPLTeam.getJSONObject(i);
                                myMovie.setFilmName(jsonTeam.getString("title"));
                                myMovie.setLanguage(jsonTeam.getString("original_language"));
                                myMovie.setDate(jsonTeam.getString("release_date"));
                                myMovie.setRate(jsonTeam.getString("vote_average"));
                                myMovie.setLogoFilm(jsonTeam.getString("poster_path"));
                                myMovie.setDeskripsi(jsonTeam.getString("overview"));
                                listDataMovie.add(myMovie);
                            }
                            recycled = findViewById(R.id.recycled);

                            adapterListCode = new movieAdapter(getApplicationContext(), listDataMovie,listMovieName.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recycled.setHasFixedSize(true);
                            recycled.setLayoutManager(mLayoutManager);
                            recycled.setAdapter(adapterListCode);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // Tangani kesalahan
                        Log.d("failed ", "onError: "+error.toString());
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie_name);
        listDataMovie = new ArrayList<>();
        GetMovie();

    }

    @Override
    public void onContactSelected(movieModel contact) {
        Intent intent = new Intent(listMovieName.this,detailPage.class);
        intent.putExtra("mymovie", contact);
        startActivity(intent);
    }

    @Override
    public void onLongClickListener(movieModel film) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}