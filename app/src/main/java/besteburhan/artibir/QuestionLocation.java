package besteburhan.artibir;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by besteburhan on 15.9.2017.
 */

public class QuestionLocation {
    LatLng latLng;
    int meter;

    public QuestionLocation(LatLng latLng, int meter) {
        this.latLng = latLng;
        this.meter = meter;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getMeter() {
        return meter;
    }

    public void setMeter(int meter) {
        this.meter = meter;
    }
}

