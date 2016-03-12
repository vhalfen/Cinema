package info.androidhive.customlistviewvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;


import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;

public class FicheFilm extends AppCompatActivity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_film);
        TextView txt1 = (TextView) findViewById(R.id.textView1);
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        TextView txt4 = (TextView) findViewById(R.id.textViewdate);
        TextView txt5 = (TextView) findViewById(R.id.textViewrealisateur);
        TextView txt6 = (TextView) findViewById(R.id.textViewCategorie);
        TextView txt7 = (TextView) findViewById(R.id.textViewGenre);

        imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Get data via the key
            String value1 = extras.getString("Titre");
            String value2 = extras.getString("Synopsis");
            String value3 = extras.getString("Affiche");
            String value4 = extras.getString("Date");
            String value5 = extras.getString("Realisateur");
            String value6 = extras.getString("Categorie");
            String value7 = extras.getString("Genre");

            if (value1 != null) {
                txt1.setText(value1);
                txt2.setText(value2);
                thumbNail.setImageUrl(value3, imageLoader);
                txt4.setText(value4);
                txt5.setText(value5);
                txt6.setText(value6);
                txt7.setText(value7);
            }
        }
    }
}
