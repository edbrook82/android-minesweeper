package uk.co.dekoorb.c3469162.minesweeper;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.co.dekoorb.c3469162.minesweeper.fragments.MineFragment;
import uk.co.dekoorb.c3469162.minesweeper.model.GeoLocation;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;
import uk.co.dekoorb.c3469162.minesweeper.tools.DummyContent;
import uk.co.dekoorb.c3469162.minesweeper.tools.MineGenerator;

public class MainActivity extends AppCompatActivity
        implements MineFragment.OnListFragmentInteractionListener,
        OnMapReadyCallback {

    private Mine mMine;
    private GoogleMap mMap;
    private boolean mDualMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        Fragment mineListFragment = fm.findFragmentById(R.id.master_frame);

        if (mineListFragment == null) {
            mineListFragment = new MineFragment();

            fm.beginTransaction()
                    .add(R.id.master_frame, mineListFragment)
                    .commit();
        }

        if (findViewById(R.id.detail_frame) != null) {
            mDualMode = true;
            SupportMapFragment mineMapFragment = new com.google.android.gms.maps.SupportMapFragment();
            fm.beginTransaction()
                    .replace(R.id.detail_frame, mineMapFragment)
                    .commit();
            mineMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onListFragmentInteraction(Mine mine) {
        mMine = mine;
        if (!mDualMode) {
//            Bundle mineBundle = mMine.asBundle();
//            Intent intent = new Intent(this, MineMapActivity.class);
//            intent.putExtras(mineBundle);
//            startActivity(intent);
            SupportMapFragment mineMapFragment = new com.google.android.gms.maps.SupportMapFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                .add(R.id.master_frame, mineMapFragment)
                .addToBackStack("map-view")
                .commit();
            mineMapFragment.getMapAsync(this);
        } else {
            LatLng mineLoc = new LatLng(
                    mMine.getGeoLocation().getLatitude(),
                    mMine.getGeoLocation().getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mineLoc, 17.0f));
        }
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

        LatLng mineLoc;
        if (mMine != null) {
            mineLoc = new LatLng(
                    mMine.getGeoLocation().getLatitude(),
                    mMine.getGeoLocation().getLongitude());
        } else {
            mineLoc = new LatLng(
                    (MineGenerator.MIN_LAT + MineGenerator.MAX_LAT) / 2.0,
                    (MineGenerator.MIN_LON + MineGenerator.MAX_LON) / 2.0);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mineLoc, 17.0f));
    }
}
