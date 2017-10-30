package uk.co.dekoorb.c3469162.minesweeper;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.co.dekoorb.c3469162.minesweeper.model.GeoLocation;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;
import uk.co.dekoorb.c3469162.minesweeper.tools.DummyContent;

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
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            mine.isCleared() ?
                                    BitmapDescriptorFactory.HUE_GREEN :
                                    BitmapDescriptorFactory.HUE_RED
                    ))
                    .position(mineLoc)
                    .title("Mine-" + mine.getId() + " [" + (mine.isCleared() ? "CLEAR" : "ACTIVE") + "]"));
        }

        LatLng mineLoc = new LatLng(
                mMine.getGeoLocation().getLatitude(),
                mMine.getGeoLocation().getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mineLoc, 17.0f));
    }
}
