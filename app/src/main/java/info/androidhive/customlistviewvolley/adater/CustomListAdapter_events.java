package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Event;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomListAdapter_events extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Event> eventItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter_events(Activity activity, List<Event> eventItems) {
        this.activity = activity;
        this.eventItems = eventItems;;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int location) {
        return eventItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public CustomListAdapter_events(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView duree = (TextView) convertView.findViewById(R.id.duree);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Event m = eventItems.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getType());

        // duree
        duree.setText(String.valueOf(m.getNombre()) + " soirée(s) organisée(s).");

        // genre
        //genre.setText(String.valueOf(m.getGenre()));

        JSONArray events_m = m.getEvents();
        String events_m_string = "";
            for(int j=0; j < events_m.length(); j++){
                JSONObject events_m_o = null;
                try {
                    events_m_o = events_m.getJSONObject(j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String varstring = null;
                try {
                    varstring = events_m_o.getString("titre");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                events_m_string = events_m_string + " - " + varstring;
            }

        genre.setText(events_m_string);


        // release year
        //year.setText(String.valueOf(m.getYear()));




        return convertView;
    }

    private Context context;


}