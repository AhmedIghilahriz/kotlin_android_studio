package com.example.appli;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appli.R;
import com.example.appli.UltrasonicData;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<UltrasonicData> dataList;

    public DataAdapter(List<UltrasonicData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UltrasonicData data = dataList.get(position);
        holder.distanceTextView.setText(String.valueOf(data.getDistance()));
        holder.timestampTextView.setText(data.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView distanceTextView;
        TextView timestampTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            distanceTextView = itemView.findViewById(R.id.distanceTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
        }
    }
}
