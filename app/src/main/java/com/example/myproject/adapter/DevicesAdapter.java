package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.CTDeviceActivity;
import com.example.myproject.R;
import com.example.myproject.obj.Devices;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>{

    Context context;
    List<Devices> list;

    public DevicesAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Devices> t_list){
        this.list=t_list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_devices,parent,false);
        return new DevicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesViewHolder holder, int position) {
        Devices devices = list.get(position);
        if (devices == null)
        {
            return;
        }

        Picasso.with(holder.img.getContext()).load(devices.img1).into(holder.img);
        holder.name.setText(devices.name);

        StringBuilder builder = new StringBuilder(""+devices.price);
        if (devices.price.length()>6) {
            int halfway1 = devices.price.length()-3;
            int halfway2 = devices.price.length()-6;
            builder.insert(halfway1, ".");
            builder.insert(halfway2, ".");
        }else if (devices.price.length()>9)
        {
            int halfway1 = devices.price.length()-3;
            int halfway2 = devices.price.length()-6;
            int halfway3 = devices.price.length()-9;
            builder.insert(halfway1, ".");
            builder.insert(halfway2, ".");
            builder.insert(halfway3, ".");
        }else {
            int halfway = devices.price.length()-3;
            builder.insert(halfway, ".");
        }

        holder.price.setText(builder.toString()+" VNĐ" );

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, CTDeviceActivity.class);
                intent.putExtra("key_device",devices.sl);
                intent.putExtra("key_user",devices.color3);
                context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null)
        {
            return list.size();
        }
        return 0;
    }

    public class DevicesViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,price;

        public DevicesViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.devices_img);
            name = itemView.findViewById(R.id.devices_name);
            price = itemView.findViewById(R.id.devices_price);
        }
    }
}
