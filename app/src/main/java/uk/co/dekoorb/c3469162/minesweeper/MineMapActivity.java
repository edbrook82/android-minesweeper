package uk.co.dekoorb.c3469162.minesweeper;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.co.dekoorb.c3469162.minesweeper.model.GeoLocation;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;
import uk.co.dekoorb.c3469162.minesweeper.tools.DummyContent;
import uk.co.dekoorb.c3469162.minesweeper.tools.MineGenerator;

public class MineMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Mine mMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_map);

        Bundle extras = getIntent().getExtras();
        mMine = Mine.fromBundle(extras);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Mine mine : DummyContent.ITEMS) {
            GeoLocation loc = mine.getGeoLocation();
            LatLng mineLoc = new LatLng(loc.getLatitude(), loc.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            mine.isCleared() ?
                                    BitmapDescriptorFactory.HUE_GREEN :
                                    BitmapDescriptorFactory.HUE_RED
                    ))
                    .alpha(0.5f)
                    .position(mineLoc)
                    .title("Mine-" + mine.getId() + " [" + (mine.isCleared() ? "CLEAR" : "ACTIVE") + "]"));
            if (mine.getId() == mMine.getId()) {
                marker.setAlpha(1.0f);
                marker.showInfoWindow();
            }
        }

        LatLng mineLoc = new LatLng(
                (MineGenerator.MIN_LAT + MineGenerator.MAX_LAT) / 2.0,
                (MineGenerator.MIN_LON + MineGenerator.MAX_LON) / 2.0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mineLoc, 17.0f));
    }
}
