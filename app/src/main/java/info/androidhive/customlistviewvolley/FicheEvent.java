package info.androidhive.customlistviewvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;


import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;

import info.androidhive.customlistviewvolley.app.AppController;

public class FicheEvent extends AppCompatActivity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_event);
        TextView txt1 = (TextView) findViewById(R.id.textView1);
        TextView txt2 = (TextView) findViewById(R.id.textViewDescription);

        imageLoader = AppController.getInstance().getImageLoader();


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Get data via the key
            txt1.setText(extras.getString("Titre"));
            txt2.setText(extras.getString("Descriptions_events"));


        }
    }
}
