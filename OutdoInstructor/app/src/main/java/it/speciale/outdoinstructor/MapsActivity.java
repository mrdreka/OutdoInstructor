package it.speciale.outdoinstructor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import static android.R.id.message;

public class MapsActivity extends FragmentActivity  implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private int permissionCheck;
    Marker Person1, Person2 ;

    Polyline lif1,lift2,line,toLars1,toLars2,toLars3,toLars4,toLars5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //mapFragment.onResume(); // needed to get the map to display immediately


        Log.d(getClass().getName(), "value = " + permissionCheck);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
/*
    public void checkBounds() {
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                fetchData(bounds);
            }
        });

    } */

    @Override
    public void onCameraIdle() {

        Log.v("test", ""+ mMap.getCameraPosition());


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnCameraIdleListener(this);

      //  mMap .getUiSettings().setScrollGesturesEnabled(false);

        mMap.setMinZoomPreference((float) 14);

        permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck >= 0) {
                mMap.setMyLocationEnabled(true);

        }
        else {
            // permission denied, boo! Disable the
            // functionality that depends on this permission.
            Toast.makeText(this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
        }
        // For dropping a marker at a point on the Map
        LatLng ZellAmZee = new LatLng(47.208865, 12.689183);
        mMap.addMarker(new MarkerOptions().position(ZellAmZee).title("Me").snippet("Skiing area"));

        mMap.setOnMarkerClickListener(this);
        // For dropping a marker at a point on the Map
        LatLng Person1L = new LatLng(47.191345, 12.676994);
        Person1 = mMap.addMarker(new MarkerOptions().position(Person1L).title("Morten").snippet("Skiing area")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_black)));

        LatLng Person2L = new LatLng(47.219985, 12.699170);
        Person2 = mMap.addMarker(new MarkerOptions().position(Person2L).title("Lars").snippet("Skiing area")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_2)));



        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(ZellAmZee).zoom(14).bearing(189).tilt(38).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Log.v("test", ""+ mMap.getCameraPosition());

/*
        CameraPosition oldPos = mMap.getCameraPosition();

        CameraPosition pos = CameraPosition.builder(oldPos).bearing(90).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(pos));
*/


/*
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
            }
        });*/
//        mMap.setLatLngBoundsForCameraTarget(northeast,southwest);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(Person1))
        {
            lif1 = this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.208865, 12.689183),new LatLng(47.201324, 12.677970)).width(5).color(Color.GREEN));
            line = this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.201324, 12.677970),new LatLng(47.201681, 12.680577)).width(6).color(Color.BLUE));
            lift2 = this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.201681, 12.680577),new LatLng(47.191345, 12.676994)).width(5).color(Color.GREEN));


            if(toLars1 != null) {
                toLars1.remove();
                toLars2.remove();
                toLars3.remove();
                toLars4.remove();
            }
        }
        else if (marker.equals(Person2)){
            if(lif1 != null) {
                lif1.remove();
                line.remove();
                lift2.remove();
            }

            toLars1 = this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.208865, 12.689183),new LatLng(47.212990, 12.688757)).width(6).color(Color.BLUE));
            toLars2 = this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.212990, 12.688757),new LatLng(47.215220, 12.692373)).width(6).color(Color.BLUE));
            toLars3 =  this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.215220, 12.692373),new LatLng(47.219869, 12.696976)).width(6).color(Color.BLUE));
            toLars4 =  this.mMap.addPolyline(new PolylineOptions().add(new LatLng(47.219869, 12.696976),new LatLng(47.219985, 12.699170)).width(6).color(Color.BLUE));

        }
        return false;
    }

}
