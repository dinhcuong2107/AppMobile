package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.adapter.DevicesAdapter;
import com.example.myproject.obj.Devices;
import com.example.myproject.obj.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeviceActivity extends AppCompatActivity {
String key_user,type_device;
LinearLayout layoutlap, layoutphone,layoutpk;
Button add_lap, add_phone, add_pk;
DatabaseReference databaseReference;
DevicesAdapter devicesAdapter;
RecyclerView recyclerView_lap,recyclerView_phone,recyclerView_pk;
TextView text_price_lap,text_RAM_lap,text_price_phone,text_RAM_phone;
RadioButton rlap_price_all,rlap_price_1,rlap_price_2,rlap_price_3,rlap_RAM_all,rlap_Ram4,rlap_Ram8,rlap_Ram16;
RadioButton rp_price_all,rp_price_1,rp_price_2,rp_price_3,rp_RAM_all,rp_Ram3,rp_Ram4,rp_Ram8;
RadioGroup group_price_lap,group_RAM_lap,group_price_phone,group_RAM_phone;
int i1 = 0,i2 = 0,i3 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        anhxa();

        // nhận data từ intent
        key_user =  getIntent().getStringExtra("key_user");
        type_device = getIntent().getStringExtra("type_device");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(key_user);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                if (users.position.equals("admin"))
                {
                    add_lap.setVisibility(View.VISIBLE);
                    add_phone.setVisibility(View.VISIBLE);
                    add_pk.setVisibility(View.VISIBLE);
                }
                else {
                    add_lap.setVisibility(View.GONE);
                    add_phone.setVisibility(View.GONE);
                    add_pk.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        checkTypeView(type_device);

        add_lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceActivity.this,AddDevicesActivity.class);
                intent.putExtra("key_user",key_user);
                startActivity(intent);
            }
        });

        text_price_lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1++;
                if(i1%2==1)
                {
                    group_price_lap.setVisibility(View.VISIBLE);
                }else{
                    group_price_lap.setVisibility(View.GONE);
                }
            }
        });
        text_RAM_lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i3++;
                if(i3%2==1)
                {
                    group_RAM_lap.setVisibility(View.VISIBLE);
                }else{
                    group_RAM_lap.setVisibility(View.GONE);
                }
            }
        });
        text_price_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1++;
                if(i1%2==1)
                {
                    group_price_phone.setVisibility(View.VISIBLE);
                }else{
                    group_price_phone.setVisibility(View.GONE);
                }
            }
        });
        text_RAM_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i3++;
                if(i3%2==1)
                {
                    group_RAM_phone.setVisibility(View.VISIBLE);
                }else{
                    group_RAM_phone.setVisibility(View.GONE);
                }
            }
        });
        rlap_price_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_price_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_price_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_price_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_RAM_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_Ram4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_Ram8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });
        rlap_Ram16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_lap();
            }
        });

        rp_price_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_price_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_price_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_price_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_RAM_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_Ram3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_Ram4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });
        rp_Ram8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_phone();
            }
        });

    }

    private void load_phone() {
        DevicesAdapter devicesAdapter = new DevicesAdapter(DeviceActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(DeviceActivity.this,2);
        recyclerView_phone.setLayoutManager(gridLayoutManager);
        String sprice="",sram="";
        if (rp_price_all.isChecked())
        {sprice = "all";}
        if (rp_price_1.isChecked())
        {sprice = "1";}
        if (rp_price_2.isChecked())
        {sprice = "2";}
        if (rp_price_3.isChecked())
        {sprice = "3";}
        if (rp_RAM_all.isChecked())
        {sram="all";        }
        if (rp_Ram4.isChecked())
        {sram="4";        }
        if (rp_Ram8.isChecked())
        {sram="8";        }
        if (rp_Ram3.isChecked())
        {sram="3";        }
        devicesAdapter.setData(getList("2",sprice,sram));
        recyclerView_phone.setAdapter(devicesAdapter);
    }

    private void load_lap() {
        DevicesAdapter devicesAdapter = new DevicesAdapter(DeviceActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(DeviceActivity.this,2);
        recyclerView_lap.setLayoutManager(gridLayoutManager);
        String sprice="",sram="";
        if (rlap_price_all.isChecked())
        {sprice = "all";}
        if (rlap_price_1.isChecked())
        {sprice = "1";}
        if (rlap_price_2.isChecked())
        {sprice = "2";}
        if (rlap_price_3.isChecked())
        {sprice = "3";}
        if (rlap_RAM_all.isChecked())
        {sram="all";        }
        if (rlap_Ram4.isChecked())
        {sram="4";        }
        if (rlap_Ram8.isChecked())
        {sram="8";        }
        if (rlap_Ram16.isChecked())
        {sram="16";        }
        devicesAdapter.setData(getList("1",sprice,sram));
        recyclerView_lap.setAdapter(devicesAdapter);
    }

    private List<Devices> getList(String stype,String sprice, String sram) {
        List<Devices> list = new ArrayList<>();
        if (stype.equals("1"))
        {
            if (sprice.equals("all") && sram.equals("all")){
                databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            Devices devices = dataSnapshot.getValue(Devices.class);
                            if (Integer.parseInt(devices.sl)>=1 && devices.type.equals(stype))
                            {
                                devices.sl=dataSnapshot.getKey();
                                devices.color3 = key_user;
                                list.add(devices);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else if (sprice.equals("all") && sram.length()<3){
                databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            Devices devices = dataSnapshot.getValue(Devices.class);
                            if (Integer.parseInt(devices.sl)>=1 && devices.type.equals(stype) && devices.ram.equals(sram))
                            {
                                devices.sl=dataSnapshot.getKey();
                                devices.color3 = key_user;
                                list.add(devices);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else if (sprice.length()<3 && sram.equals("all")){
                databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            Devices devices = dataSnapshot.getValue(Devices.class);
                            if (Integer.parseInt(devices.sl)>=1 && devices.type.equals(stype))
                            {
                                if (sprice.equals("1")){
                                    if (Integer.parseInt(devices.price)<=10000000){
                                        devices.sl=dataSnapshot.getKey();
                                        devices.color3 = key_user;
                                        list.add(devices);
                                    }
                                }
                                else if (sprice.equals("2")){
                                    if (Integer.parseInt(devices.price)>=10000000 && Integer.parseInt(devices.price)<=20000000){
                                        devices.sl=dataSnapshot.getKey();
                                        devices.color3 = key_user;
                                        list.add(devices);
                                    }
                                }
                                else if (sprice.equals("3")){
                                    if (Integer.parseInt(devices.price)>=20000000){
                                        devices.sl=dataSnapshot.getKey();
                                        devices.color3 = key_user;
                                        list.add(devices);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        else if (stype.equals("2")){
            if (sprice.equals("all") && sram.equals("all")){
                databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            Devices devices = dataSnapshot.getValue(Devices.class);
                            if (Integer.parseInt(devices.sl)>=1 && devices.type.equals(stype))
                            {
                                devices.sl=dataSnapshot.getKey();
                                devices.color3 = key_user;
                                list.add(devices);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else if (sprice.equals("all") && sram.length()<3){
                databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            Devices devices = dataSnapshot.getValue(Devices.class);
                            if (Integer.parseInt(devices.sl)>=1 && devices.type.equals(stype) && devices.ram.equals(sram))
                            {
                                devices.sl=dataSnapshot.getKey();
                                devices.color3 = key_user;
                                list.add(devices);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else if (sprice.length()<3 && sram.equals("all")){
                databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            Devices devices = dataSnapshot.getValue(Devices.class);
                            if (Integer.parseInt(devices.sl)>=1 && devices.type.equals("2"))
                            {
                                if (sprice.equals("1")){
                                    if (Integer.parseInt(devices.price)<=5000000){
                                        devices.sl=dataSnapshot.getKey();
                                        devices.color3 = key_user;
                                        list.add(devices);
                                    }
                                }
                                else if (sprice.equals("2")){
                                    if (Integer.parseInt(devices.price)>=5000000 && Integer.parseInt(devices.price)<=10000000){
                                        devices.sl=dataSnapshot.getKey();
                                        devices.color3 = key_user;
                                        list.add(devices);
                                    }
                                }
                                else if (sprice.equals("3")){
                                    if (Integer.parseInt(devices.price)>=10000000){
                                        devices.sl=dataSnapshot.getKey();
                                        devices.color3 = key_user;
                                        list.add(devices);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

        return list;
    }

    private void anhxa() {
        layoutlap = (LinearLayout) findViewById(R.id.linear_lap);
        layoutphone = (LinearLayout) findViewById(R.id.linear_phone);
        layoutpk = (LinearLayout) findViewById(R.id.linear_pk);

        recyclerView_lap = (RecyclerView) findViewById(R.id.recyclerView_lap);
        recyclerView_phone = (RecyclerView) findViewById(R.id.recyclerView_phone);
        recyclerView_pk = (RecyclerView) findViewById(R.id.recyclerView_pk);

        add_lap = (Button) findViewById(R.id.btn_add_lap);
        add_phone = (Button) findViewById(R.id.btn_add_phone);
        add_pk = (Button) findViewById(R.id.btn_add_pk);

        text_price_lap = (TextView) findViewById(R.id.text_price_lap);
        text_RAM_lap = (TextView) findViewById(R.id.text_RAM_lap);
        text_price_phone = (TextView) findViewById(R.id.text_price_phone);
        text_RAM_phone = (TextView) findViewById(R.id.text_RAM_phone);

        rlap_price_all = (RadioButton) findViewById(R.id.radio_price_all_lap);
        rlap_price_1 = (RadioButton) findViewById(R.id.radio_price_1_lap);
        rlap_price_2 = (RadioButton) findViewById(R.id.radio_price_2_lap);
        rlap_price_3 = (RadioButton) findViewById(R.id.radio_price_3_lap);
        rlap_RAM_all = (RadioButton) findViewById(R.id.radio_ram_all_lap);
        rlap_Ram4 = (RadioButton) findViewById(R.id.radio_ram_4_lap);
        rlap_Ram8 = (RadioButton) findViewById(R.id.radio_ram_8_lap);
        rlap_Ram16 = (RadioButton) findViewById(R.id.radio_ram_16_lap);

        rp_price_all= (RadioButton) findViewById(R.id.radio_price_all_phone);
        rp_price_1 = (RadioButton) findViewById(R.id.radio_price_1_phone);
        rp_price_2 = (RadioButton) findViewById(R.id.radio_price_2_phone);
        rp_price_3 = (RadioButton) findViewById(R.id.radio_price_3_phone);
        rp_RAM_all = (RadioButton) findViewById(R.id.radio_ram_all_phone);
        rp_Ram3 = (RadioButton) findViewById(R.id.radio_ram_3_phone);
        rp_Ram4 = (RadioButton) findViewById(R.id.radio_ram_4_phone);
        rp_Ram8 = (RadioButton) findViewById(R.id.radio_ram_8_phone);

        group_price_lap = (RadioGroup) findViewById(R.id.group_price_lap);
        group_RAM_lap = (RadioGroup) findViewById(R.id.group_ram_lap);
        group_price_phone = (RadioGroup) findViewById(R.id.group_price_phone);
        group_RAM_phone = (RadioGroup) findViewById(R.id.group_ram_phone);
    }

    private void checkTypeView(String type_device) {
        if (type_device.equals("1"))
        {
            layoutphone.setVisibility(View.GONE);
            layoutpk.setVisibility(View.GONE);
            rlap_price_all.setChecked(true);
            rlap_RAM_all.setChecked(true);

            group_price_lap.setVisibility(View.GONE);
            group_RAM_lap.setVisibility(View.GONE);
            load_lap();
        }
        if (type_device.equals("2"))
        {
            layoutlap.setVisibility(View.GONE);
            layoutpk.setVisibility(View.GONE);
            rp_price_all.setChecked(true);
            rp_RAM_all.setChecked(true);

            group_price_phone.setVisibility(View.GONE);
            group_RAM_phone.setVisibility(View.GONE);
            load_phone();
        }
        if (type_device.equals("3"))
        {
            layoutphone.setVisibility(View.GONE);
            layoutlap.setVisibility(View.GONE);
        }

    }
}