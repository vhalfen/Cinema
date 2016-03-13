package info.androidhive.customlistviewvolley;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;


import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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


        VideoView videoView =(VideoView)findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=chvki68McG0"));

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Get data via the key
                txt1.setText(extras.getString("Titre"));
                txt2.setText(extras.getString("Synopsis"));
                thumbNail.setImageUrl(extras.getString("Affiche"), imageLoader);
                txt4.setText(extras.getString("Date"));
                txt5.setText(extras.getString("Realisateur"));
                txt6.setText(extras.getString("Categorie"));
                txt7.setText(extras.getString("Genre"));
                media1.setImageUrl(extras.getString("Medias0"), imageLoader);
                media2.setImageUrl(extras.getString("Medias1"), imageLoader);
                media3.setImageUrl(extras.getString("Medias2"), imageLoader);
                media4.setImageUrl(extras.getString("Medias3"), imageLoader);
                media5.setImageUrl(extras.getString("Medias4"), imageLoader);
                media6.setImageUrl(extras.getString("Medias5"), imageLoader);
        }
    }


}
