package csrc.probbyapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.MapController;

public class MapFragment extends Fragment  {
//implements OnMapReadyCallback
    private GoogleMap gMap;
    MapController mapController = new MapController();

    @Override
    public View onCreateView(LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

//        SupportMapFragment mapFragment = (SupportMapFragment)
//                getChildFragmentManager().findFragmentById(R.id.map);
//
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(this);
//        }

        return view;
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        //this.gMap = googleMap;
//        // TODO add map functionality
//
//    }
}
