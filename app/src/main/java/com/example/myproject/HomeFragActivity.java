package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.adapter.DevicesAdapter;
import com.example.myproject.obj.Devices;
import com.example.myproject.obj.TypeDevice;
import com.example.myproject.obj.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragActivity extends Fragment {
ImageView img_avt,img_lap,img_phone,img_pk;
TextView textView_name_user,textView_phone_user,textView0,textView1,textView2;
RecyclerView recyclerView_0,recyclerView_1,recyclerView_2;
String key_user="";
int i0,i1,i2;
DatabaseReference databaseReference,databaseReferenceUser,databaseReferenceDevices;
    DevicesAdapter devicesAdapter0,devicesAdapter1,devicesAdapter2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_frag,container,false);

        img_avt = (ImageView) view.findViewById(R.id.img_avt_user);
        textView_name_user = (TextView) view.findViewById(R.id.text_name_user);
        textView_phone_user = (TextView) view.findViewById(R.id.text_phone_user);

        textView0 = (TextView) view.findViewById(R.id.spm);
        textView1 = (TextView) view.findViewById(R.id.spnb);
        textView2 = (TextView) view.findViewById(R.id.spbcn);

        recyclerView_0 = (RecyclerView) view.findViewById(R.id.rec_0);
        recyclerView_1 = (RecyclerView) view.findViewById(R.id.rec_1);
        recyclerView_2 = (RecyclerView) view.findViewById(R.id.rec_2);

        img_lap = (ImageView) view.findViewById(R.id.int_lap);
        img_phone = (ImageView) view.findViewById(R.id.int_phone);
        img_pk = (ImageView) view.findViewById(R.id.int_pk);

        Bundle bundle = getActivity().getIntent().getExtras();
        key_user = bundle.getString("key_user");

        databaseReferenceUser= FirebaseDatabase.getInstance().getReference("Users").child(key_user);
        databaseReferenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);

                if (users.avatar.length() != 0)
                {
                    Picasso.with(getActivity()).load(users.avatar).into(img_avt);
                }
                else {
                    Picasso.with(getActivity()).load(R.drawable.avatar_default_icon).into(img_avt);
                }
                textView_name_user.setText(""+users.fullname);
                textView_phone_user.setText(""+users.phonenumber);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // recyclerView_0
        devicesAdapter0 = new DevicesAdapter(getContext());
        GridLayoutManager gridLayoutManager0 = new GridLayoutManager(getContext(),2);
        recyclerView_0.setLayoutManager(gridLayoutManager0);
        devicesAdapter0.setData(getList_rec_0());
        recyclerView_0.setAdapter(devicesAdapter0);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1++;
                if (i1%2==0)
                {
                    recyclerView_1.setVisibility(View.VISIBLE);
                }else {
                    recyclerView_1.setVisibility(View.GONE);
                }

            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i2++;
                if (i2%2==0)
                {
                    recyclerView_2.setVisibility(View.VISIBLE);
                }else {
                    recyclerView_2.setVisibility(View.GONE);
                }

            }
        });
        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i0++;
                if (i0%2==0)
                {
                    recyclerView_0.setVisibility(View.VISIBLE);
                }else {
                    recyclerView_0.setVisibility(View.GONE);
                }

            }
        });

        // recyclerView_1
        devicesAdapter1 = new DevicesAdapter(getContext());
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),2);
        recyclerView_1.setLayoutManager(gridLayoutManager1);
        devicesAdapter1.setData(getList_rec_1());
        recyclerView_1.setAdapter(devicesAdapter1);

        // recyclerView_2
        devicesAdapter2 = new DevicesAdapter(getContext());
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),2);
        recyclerView_2.setLayoutManager(gridLayoutManager2);
        devicesAdapter2.setData(getList_rec_2());
        recyclerView_2.setAdapter(devicesAdapter2);

        img_lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeviceActivity.class);
                intent.putExtra("key_user",key_user);
                intent.putExtra("type_device","1");
                startActivity(intent);
            }
        });
        img_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeviceActivity.class);
                intent.putExtra("key_user",key_user);
                intent.putExtra("type_device","2");
                startActivity(intent);
            }
        });
        img_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DeviceActivity.class);
//                intent.putExtra("key_user",key_user);
//                intent.putExtra("type_device","3");
//                startActivity(intent);
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog_dev);

                Window window = dialog.getWindow();
                if (window == null){return;}
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams win = window.getAttributes();
                win.gravity = Gravity.CENTER;
                window.setAttributes(win);
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        return view;
    }

    private List<Devices> getList_rec_0() {
        List<Devices> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Special");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    TypeDevice typeDevice = dataSnapshot.getValue(TypeDevice.class);
                    if (typeDevice.sp1)
                    {
                        databaseReferenceDevices = FirebaseDatabase.getInstance().getReference("Devices").child(typeDevice.id);
                        databaseReferenceDevices.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshotDevice) {
                                Devices devices = snapshotDevice.getValue(Devices.class);
                                if (Integer.parseInt(devices.sl)>=1)
                                {
                                    devices.sl=typeDevice.id;
                                    devices.color3 = key_user;
                                    list.add(devices);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list;
    }
    private List<Devices> getList_rec_1() {
        List<Devices> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Special");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    TypeDevice typeDevice = dataSnapshot.getValue(TypeDevice.class);
                    if (typeDevice.sp2)
                    {
                        databaseReferenceDevices = FirebaseDatabase.getInstance().getReference("Devices").child(typeDevice.id);
                        databaseReferenceDevices.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshotDevice) {
                                Devices devices = snapshotDevice.getValue(Devices.class);
                                if (Integer.parseInt(devices.sl)>=1)
                                {
                                    devices.sl=typeDevice.id;
                                    devices.color3 = key_user;
                                    list.add(devices);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }
    private List<Devices> getList_rec_2() {
        List<Devices> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Special");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    TypeDevice typeDevice = dataSnapshot.getValue(TypeDevice.class);
                    if (typeDevice.sp3)
                    {
                        databaseReferenceDevices = FirebaseDatabase.getInstance().getReference("Devices").child(typeDevice.id);
                        databaseReferenceDevices.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshotDevice) {
                                Devices devices = snapshotDevice.getValue(Devices.class);
                                if (Integer.parseInt(devices.sl)>=1)
                                {
                                    devices.sl=typeDevice.id;
                                    devices.color3 = key_user;
                                    list.add(devices);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }
}