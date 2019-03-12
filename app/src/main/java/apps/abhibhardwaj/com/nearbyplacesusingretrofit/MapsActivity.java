package apps.abhibhardwaj.com.nearbyplacesusingretrofit;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity {

  String placeName;
  LatLng latLng;
  SupportMapFragment mapFragment;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);

    getPlaceDetails();

    mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
    mapFragment.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.addMarker(setMarker(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

      }
    });


  }

  private MarkerOptions setMarker(LatLng latLng) {

    MarkerOptions options = new MarkerOptions();
    options.title(placeName);
    options.position(latLng);

    return options;
  }

  private void getPlaceDetails() {
    placeName = getIntent().getStringExtra("placeName");
    latLng = new LatLng(Double.valueOf(getIntent().getStringExtra("latitude")), Double.valueOf(getIntent().getStringExtra("longitude")));
  }
}
