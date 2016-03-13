package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

public class Prochainement extends AppCompatActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://centrale.corellis.eu/prochainement.json";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent details = new Intent(Prochainement.this, FicheFilm.class);
                Movie m = movieList.get(position);
                details.putExtra("Titre", m.getTitle());
                details.putExtra("Synopsis", m.getSynopsis());
                details.putExtra("Affiche", m.getThumbnailUrl());
                details.putExtra("Date", m.getYear());
                details.putExtra("Realisateur", m.getRealisateur());
                details.putExtra("Categorie", m.getCategorie());
                details.putExtra("Genre", m.getGenre());

                JSONArray medias = m.getMedias();
                ArrayList<String> media_liste = null;
                JSONObject media_l = null;
                String media_l_tostring = "";

                for (int l_m = 0; l_m < medias.length(); l_m++) {
                    try {
                        media_l = medias.getJSONObject(l_m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (media_l != null) {
                            media_l_tostring = media_l.getString("path");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    details.putExtra("Medias" + l_m, media_l_tostring);

                }

                startActivity(details);
            }
        });


        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    JSONArray films = response.getJSONArray("films");

                    for (int j = 0; j < films.length(); j++) {
                        JSONObject film_i = films.getJSONObject(j);
                        Movie movie = new Movie();
                        movie.setTitle(film_i.getString("titre"));
                        movie.setThumbnailUrl(film_i.getString("affiche"));
                        movie.setDuree(film_i.getString("duree"));
                        movie.setYear(film_i.getString("annee"));
                        movie.setGenre(film_i.getString("genre"));
                        movie.setRealisateur(film_i.getString("realisateur"));
                        movie.setDistributeur(film_i.getString("distributeur"));
                        movie.setSynopsis(film_i.getString("synopsis"));
                        movie.setCategorie(film_i.getString("categorie"));
                        movie.setMedias(film_i.getJSONArray("medias"));

                        movieList.add(movie);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filmseances:
                Intent mainActivity = new Intent(this,MainActivity.class);
                startActivity(mainActivity);
                return true;
            case R.id.prochainement:
                Intent prochainementActivity = new Intent(this,Prochainement.class);
                startActivity(prochainementActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}