package csrc.probbyapp.utils;

import java.util.List;

import csrc.probbyapp.models.PropertyModel;

public interface OnGetPropertiesListener {

  void onSuccess(List<PropertyModel> properties);
  void onFailure(Exception e);
}
