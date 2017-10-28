package uk.co.dekoorb.c3469162.minesweeper.model;

/**
 * Created by edbrook on 28/10/2017.
 */

public class Mine {
    private long mId;
    private GeoLocation mGeoLocation;
    private boolean mCleared;

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
}
