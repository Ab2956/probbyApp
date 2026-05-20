package csrc.probbyapp.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import csrc.probbyapp.R;
import csrc.probbyapp.models.PropertyModel;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    // Adapter for the properties to be displayed on the recycler view

    private List<PropertyModel> properties;
    private OnRemovePropertyListener removeListener;
    UIHelper uiHelper = new UIHelper();

    public interface OnRemovePropertyListener {
        void onRemoveProperty(PropertyModel property, int position);
    }

    public PropertyAdapter(List<PropertyModel> properties, OnPropertyClickListener listener, OnRemovePropertyListener removeListener) {
        this.properties = properties;
        this.listener = listener;
        this.removeListener = removeListener;
    }

    public void updateList(List<PropertyModel> properties) {
        this.properties = properties;
        notifyDataSetChanged();
        Log.d("Property list updated: " , properties.size() + " items");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_properties, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PropertyModel property = properties.get(position);
        holder.tvType.setText(property.getPropertyType());
        holder.tvAddress.setText(property.getAddress());
        String mortgage = "£" + property.getMortgage() + " Mortgage";
        String rent = "£" + property.getRent() + " Rent";
        holder.tvPrice.setText(rent);
        holder.tvMortgage.setText(mortgage);


        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPropertyClick(property);
            }
        });
        if(holder.removeBtn != null) {

            holder.removeBtn.setOnClickListener(v -> {
                if (removeListener != null) {
                    Log.d("Remove btn: ","Remove button clicked");
                    removeListener.onRemoveProperty(property, position);
                    Toast.makeText(holder.itemView.getContext(), "Property removed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Log.d("Property type: " , property.getPropertyType());
        uiHelper.applyTouchEffect(holder.removeBtn);
        uiHelper.applyTouchEffect(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return properties != null ? properties.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvAddress, tvPrice, tvMortgage;
        Button removeBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            removeBtn = itemView.findViewById(R.id.remove_btn);
            tvType = itemView.findViewById(R.id.tvPropertyType);
            tvAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvPrice = itemView.findViewById(R.id.tvPropertyPrice);
            tvMortgage = itemView.findViewById(R.id.tvPropertyMortgage);
        }
    }

    public interface OnPropertyClickListener {
        void onPropertyClick(PropertyModel property);
    }

    private OnPropertyClickListener listener;

}