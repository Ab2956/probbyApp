package csrc.probbyapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import csrc.probbyapp.R;
import csrc.probbyapp.controllers.MapController;
import csrc.probbyapp.models.MapPropertyModel;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;
    MapController mapController = new MapController();

    @Override
    public View onCreateView(LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mapController.getAllPropertiesLatLong(getContext(), userId, mapProperties -> {
            gMap.clear();

            for (MapPropertyModel property : mapProperties) {
               MarkerOptions marker = new MarkerOptions()
                       .position(property.getLatLng())
                       .title(property.getAddress());
               gMap.addMarker(marker);

            }
            if(!mapProperties.isEmpty()){
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapProperties.get(0).getLatLng(),12.0f));
            }
        });

    }
}
