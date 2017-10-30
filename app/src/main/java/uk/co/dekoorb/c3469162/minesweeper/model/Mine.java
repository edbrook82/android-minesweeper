package uk.co.dekoorb.c3469162.minesweeper.model;

import android.os.Bundle;

/**
 * Created by edbrook on 28/10/2017.
 */

public class Mine {
    public static final String KEY_ID = "mine::id";
    public static final String KEY_GEO_LON = "mine::geo_longitude";
    public static final String KEY_GEO_LAT = "mine::geo_latitude";
    public static final String KEY_CLEARED = "mine::cleared";

    private long mId;
    private GeoLocation mGeoLocation;
    private boolean mCleared;

    public static Mine fromBundle(Bundle bundle) {
        long id = bundle.getLong(KEY_ID);
        double geoLon = bundle.getDouble(KEY_GEO_LON);
        double geoLat = bundle.getDouble(KEY_GEO_LAT);
        boolean cleared = bundle.getBoolean(KEY_CLEARED);
        GeoLocation geoLocation = new GeoLocation(geoLat, geoLon);
        return new Mine(id, geoLocation, cleared);
    }

    public Mine(long id, GeoLocation geoLocation, boolean cleared) {
        this.mId = id;
        this.mGeoLocation = geoLocation;
        this.mCleared = cleared;
    }

    public long getId() {
        return mId;
    }

    public GeoLocation getGeoLocation() {
        return mGeoLocation;
    }

    public boolean isCleared() {
        return mCleared;
    }

    public void setCleared(boolean cleared) {
        this.mCleared = cleared;
    }

    public Bundle asBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_ID, mId);
        bundle.putDouble(KEY_GEO_LON, mGeoLocation.getLongitude());
        bundle.putDouble(KEY_GEO_LAT, mGeoLocation.getLatitude());
        bundle.putBoolean(KEY_CLEARED, mCleared);
        return bundle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mine-");
        sb.append(mId);
        sb.append(" [");
        sb.append(mCleared?"CLEAR":"ACTIVE");
        sb.append("] @ (");
        sb.append(mGeoLocation.toString());
        sb.append(")");
        return sb.toString();
    }
}
