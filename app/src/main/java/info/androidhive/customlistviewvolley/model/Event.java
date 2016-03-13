package info.androidhive.customlistviewvolley.model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 13/03/2016.
 */
public class Event {
    private String type;
    private int nombre;
    private JSONArray events;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setEvents(JSONArray events) {
        this.events = events;
    }

    public JSONArray getEvents() {
        return events;
    }
}
