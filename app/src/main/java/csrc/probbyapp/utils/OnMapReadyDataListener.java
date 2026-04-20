package csrc.probbyapp.utils;

import java.util.List;
import csrc.probbyapp.models.MapPropertyModel;

public interface OnMapReadyDataListener {
    void onDataReady(List<MapPropertyModel> properties);

}
