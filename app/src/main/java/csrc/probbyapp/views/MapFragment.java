package csrc.probbyapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import csrc.probbyapp.R;
import csrc.probbyapp.controllers.MapController;
import csrc.probbyapp.models.MapPropertyModel;
import csrc.probbyapp.utils.OnGetListener;

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

        gMap.setOnMarkerClickListener(marker -> {
            String propertyId = (String) marker.getTag();

            if (propertyId != null) {
                PropertyDetailsFragment detailsFragment = new PropertyDetailsFragment();

                Bundle args = new Bundle();
                args.putString("propertyId", propertyId);
                detailsFragment.setArguments(args);

                detailsFragment.show(getParentFragmentManager(), "PropertyDetails");
            }
            return false;
        });

        mapController.getAllPropertiesLatLong(getContext(), userId, new OnGetListener<List<MapPropertyModel>>() {
            @Override
            public void onSuccess(List<MapPropertyModel> mapProperties) {
                if (!isAdded() || gMap == null) return;

                gMap.clear();

                if (!mapProperties.isEmpty()) {
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapProperties.get(0).getLatLng(), 12.0f));
                }
                int bacthSize = 50;

                for(int i = 0; i < mapProperties.size(); i += bacthSize){
                    int start = 1;
                    int end = Math.min(i + bacthSize, mapProperties.size());

                    List<MapPropertyModel> batch = mapProperties.subList(start, end);
                    new android.os.Handler(android.os.Looper.getMainLooper())
                            .postDelayed(() -> {

                                if (!isAdded() || gMap == null) return;

                                for (MapPropertyModel property : batch) {
                                    Marker marker = gMap.addMarker(
                                            new MarkerOptions()
                                                    .position(property.getLatLng())
                                                    .title(property.getAddress())
                                    );

                                    if (marker != null) {
                                        marker.setTag(property.getPropertyId());
                                    }
                                }

                            }, start * 10);
                }
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("MapFragment", "Error getting properties: " + e.getMessage());
            }
        });

    }
}
