package uk.co.dekoorb.c3469162.minesweeper.model;

/**
 * Created by c3469162 on 27/10/2017.
 */

public class GeoLocation {
    private double mLongitude;
    private double mLatitude;

    public GeoLocation(double latitude, double longitude) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mLatitude);
        sb.append(", ");
        sb.append(mLongitude);
        return sb.toString();
    }
}
