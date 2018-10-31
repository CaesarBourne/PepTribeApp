package com.bourne.caesar.peptribeconcept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
import com.bourne.caesar.peptribeconcept.Adapters.MoviesAdapter;
import com.bourne.caesar.peptribeconcept.ModelClass.Anime;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

//    private String URL_JSON = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
//    private JsonArrayRequest ArrayRequest ;
//    private RequestQueue requestQueue ;
//    private List<Anime> lstAnime = new ArrayList<>();
//    private RecyclerView myrv ;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movies);
//        myrv = findViewById(R.id.recyclerviewid);
//        jsoncall();
//
//
//
//
//    }
//
//    public void jsoncall() {
//
//
//        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                JSONObject jsonObject = null;
//
//
//                for (int i = 0 ; i<response.length();i++) {
//
//                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();
//
//                    try {
//
//                        jsonObject = response.getJSONObject(i);
//                        Anime Anime = new Anime();
//
//                        Anime.setName(jsonObject.getString("name"));
//                        Anime.setRating(jsonObject.getString("Rating"));
//                        Anime.setDescription(jsonObject.getString("description"));
//                        Anime.setImage_url(jsonObject.getString("img"));
//                        Anime.setStudio(jsonObject.getString("studio"));
//                        Anime.setCategorie(jsonObject.getString("categorie"));
//                        //Toast.makeText(MainActivity.this,Anime.toString(),Toast.LENGTH_SHORT).show();
//                        lstAnime.add(Anime);
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//                Toast.makeText(MoviesActivity.this,"Size of Liste "+String.valueOf(lstAnime.size()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(MoviesActivity.this,lstAnime.get(1).toString(),Toast.LENGTH_SHORT).show();
//
//                setMoviesAdapter(lstAnime);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//        requestQueue = Volley.newRequestQueue(MoviesActivity.this);
//        requestQueue.add(ArrayRequest);
//    }
//
//
//
//    public void setMoviesAdapter (List<Anime> lst) {
//
//        MoviesAdapter myAdapter = new MoviesAdapter(this,lst) ;
//        myrv.setLayoutManager(new LinearLayoutManager(this));
//        myrv.setAdapter(myAdapter);
//
//
//
//
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.menu,menu);
////        return super.onCreateOptionsMenu(menu);
//
////    }

}
