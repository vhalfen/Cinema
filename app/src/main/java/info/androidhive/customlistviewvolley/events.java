package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.adater.CustomListAdapter_events;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Event;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class events extends AppCompatActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://centrale.corellis.eu/events.json";
    private ProgressDialog pDialog;
    private List<Event> eventList = new ArrayList<Event>();
    private ListView listView;
    private CustomListAdapter_events adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter_events(this, eventList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent details = new Intent(events.this, FicheEvent.class);
                Event m = eventList.get(position);
                details.putExtra("Titre", m.getType());
                details.putExtra("Date", m.getNombre());

                JSONArray events_m = m.getEvents();
                String events_m_string = "";
                events_m_string = "";
                for(int j=0; j < events_m.length(); j++){
                    JSONObject events_m_o = null;
                    try {
                        events_m_o = events_m.getJSONObject(j);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String varstring = null;
                    String varstring_titre = null;
                    try {
                        varstring_titre = events_m_o.getString("titre");
                        varstring = events_m_o.getString("description");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    events_m_string = events_m_string + "\r\n\r\n" + varstring_titre + "\r\n" + varstring;
                }
                details.putExtra("Descriptions_events", events_m_string);

                startActivity(details);
            }
        });

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Event event = new Event();
                                event.setType(obj.getString("type"));
                                JSONArray event_par_type = obj.getJSONArray("events");
                                event.setNombre(event_par_type.length());
                                event.setEvents(event_par_type);
                                // adding movie to movies array
                                eventList.add(event);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
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
            case R.id.events:
                Intent eventActivity = new Intent(this, events.class);
                startActivity(eventActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}