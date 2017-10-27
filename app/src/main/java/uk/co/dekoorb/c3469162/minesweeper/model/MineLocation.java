package uk.co.dekoorb.c3469162.minesweeper.model;

/**
 * Created by c3469162 on 27/10/2017.
 */

public class MineLocation {
    private double mLongitude;
    private double mLatitude;

    public MineLocation(double latitude, double longitude) {
        mLongitude = longitude;
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }
}
