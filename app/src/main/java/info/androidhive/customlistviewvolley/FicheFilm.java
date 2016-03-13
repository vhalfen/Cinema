package info.androidhive.customlistviewvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;


import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;

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
        NetworkImageView media1 = (NetworkImageView) findViewById(R.id.media0);
        NetworkImageView media2 = (NetworkImageView) findViewById(R.id.media1);
        NetworkImageView media3 = (NetworkImageView) findViewById(R.id.media2);
        NetworkImageView media4 = (NetworkImageView) findViewById(R.id.media3);
        NetworkImageView media5 = (NetworkImageView) findViewById(R.id.media4);
        NetworkImageView media6 = (NetworkImageView) findViewById(R.id.media5);


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

            String value8 = extras.getString("Medias0");
            String value9 = extras.getString("Medias1");
            String value10 = extras.getString("Medias2");
            String value11 = extras.getString("Medias3");
            String value12 = extras.getString("Medias4");
            String value13 = extras.getString("Medias5");


            if (value1 != null) {
                txt1.setText(value1);
                txt2.setText(value2);
                thumbNail.setImageUrl(value3, imageLoader);
                txt4.setText(value4);
                txt5.setText(value5);
                txt6.setText(value6);
                txt7.setText(value7);
                media1.setImageUrl(value8, imageLoader);
                media2.setImageUrl(value9, imageLoader);
                media3.setImageUrl(value10, imageLoader);
                media4.setImageUrl(value11, imageLoader);
                media5.setImageUrl(value12, imageLoader);
                media6.setImageUrl(value13, imageLoader);
            }
        }
    }
}
