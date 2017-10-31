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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.co.dekoorb.c3469162.minesweeper.fragments.MineFragment;
import uk.co.dekoorb.c3469162.minesweeper.model.GeoLocation;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;

public class MainActivity extends AppCompatActivity
        implements MineFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment mineListFragment = new MineFragment();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.master_frame, mineListFragment)
                .commit();
    }

    @Override
    public void onListFragmentInteraction(Mine mine) {
        Bundle mineBundle = mine.asBundle();
        Intent intent = new Intent(this, MineMapActivity.class);
        intent.putExtras(mineBundle);
        startActivity(intent);
    }
}
