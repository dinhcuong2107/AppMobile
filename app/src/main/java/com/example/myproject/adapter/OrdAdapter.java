package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.CTOrdActivity;
import com.example.myproject.R;
import com.example.myproject.obj.Devices;
import com.example.myproject.obj.OrdDevices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrdAdapter extends RecyclerView.Adapter<OrdAdapter.OrdViewHolder>{
    List<OrdDevices> mlist;
    Context context;
    DatabaseReference databaseReference;

    public OrdAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<OrdDevices> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ord, parent, false);
        return new OrdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdViewHolder holder, int position) {
        OrdDevices spOrd = mlist.get(position);
        if (spOrd == null){
            return;
        }
//        holder.tvsl.setText(spOrd.type);
        databaseReference= FirebaseDatabase.getInstance().getReference("Devices").child(spOrd.key_devices);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Devices devices = snapshot.getValue(Devices.class);

                Picasso.with(holder.imageView.getContext()).load(devices.img1).into(holder.imageView);
                holder.tvsl.setText(spOrd.sl);
                holder.tvname.setText(devices.name);
                if (spOrd.status.equals("0")){
                    holder.tvstt.setText("Đang giao hàng");
                    holder.tvstt.setBackgroundColor(Color.parseColor("#CF6F6F"));
                }
                if (spOrd.status.equals("1")){
                    holder.tvstt.setText("Đã nhận hàng");
                    holder.tvstt.setBackgroundColor(Color.parseColor("#4CAF50"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, CTOrdActivity.class);
                intent.putExtra("key_ord",spOrd.add);
                intent.putExtra("key_user",spOrd.key_user);
                context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mlist != null){
            return mlist.size();
        }
        return 0;
    }

    public class OrdViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvname,tvsl,tvstt;

        public OrdViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_ord_img);
            tvname = itemView.findViewById(R.id.item_ord_text_name);
            tvsl = itemView.findViewById(R.id.item_ord_text_sl);
            tvstt = itemView.findViewById(R.id.item_ord_text_stt);
        }
    }

}
