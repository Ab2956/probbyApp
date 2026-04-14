package csrc.probbyapp.database;

import java.util.List;

import csrc.probbyapp.models.PropertyModel;

public interface OnGetPropertiesListener {

  List<PropertyModel> onSuccess(List<PropertyModel> properties);
  void onFailure(Exception e);
}
