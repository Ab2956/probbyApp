package csrc.probbyapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import csrc.probbyapp.R;
import csrc.probbyapp.models.PropertyModel;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private List<PropertyModel> properties;

    public PropertyAdapter(List<PropertyModel> properties, OnPropertyClickListener listener) {
        this.properties = properties;
        this.listener = listener;
    }

    public void updateList(List<PropertyModel> properties) {
        this.properties = properties;
        notifyDataSetChanged();
        System.out.println("Property list updated: " + properties.size() + " items");
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
        String rent = "£" + property.getRent() + "Rent Amount";
        holder.tvPrice.setText(rent);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPropertyClick(property);
            }
        });

        System.out.println("Property type: " + property.getPropertyType());
    }

    @Override
    public int getItemCount() {
        return properties != null ? properties.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvAddress, tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tvPropertyType);
            tvAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvPrice = itemView.findViewById(R.id.tvPropertyPrice);
        }
    }

    public interface OnPropertyClickListener {
        void onPropertyClick(PropertyModel property);
    }

    private OnPropertyClickListener listener;

}