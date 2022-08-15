package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.obj.Devices;
import com.example.myproject.obj.OrdDevices;
import com.example.myproject.obj.TypeDevice;
import com.example.myproject.obj.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class CTDeviceActivity extends AppCompatActivity {
String key_user,key_device,key_type,type,time_now;
DatabaseReference databaseReference,databaseReferenceDevices,databaseReferenceTypeDevice;
    LinearLayout layoutadmin,layoutuser;
    RadioButton radioButtonspm,radioButtonspnb,radioButtonspsell,radioButtoncl1,radioButtoncl2,radioButtoncl3;
    ImageView img1,img2,img3,imageViewU1,imageViewU2,imageViewU3;
    TextView textView_name,textView_company,textViewcpu,textViewram,textViewrom,textViewdetail,textViewprice,textViewTT,textViewSL;
    EditText namesp,c_sp,cpusp,ramsp,romsp,cl1,cl2,cl3,detail,price,slsp,add_ord;
    Button btn_save,btn_buy,btn_tang,btn_giam;
    int i1,i2,i3,slmax;
    String urla1="",urla2="",urla3="";
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctdevice);

        layoutadmin = (LinearLayout) findViewById(R.id.linear_admin);
        layoutuser = (LinearLayout) findViewById(R.id.linear_user);

        radioButtonspm = (RadioButton) findViewById(R.id.ct_add_spm);
        radioButtonspnb = (RadioButton) findViewById(R.id.ct_add_spnb);
        radioButtonspsell = (RadioButton) findViewById(R.id.ct_add_spsell);

        img1 = (ImageView) findViewById(R.id.ct_img1);
        img2 = (ImageView) findViewById(R.id.ct_img2);
        img3 = (ImageView) findViewById(R.id.ct_img3);

        namesp = (EditText) findViewById(R.id.edt_ct_namesp);
        c_sp = (EditText) findViewById(R.id.edt_ct_companysp);
        cpusp = (EditText) findViewById(R.id.edt_ct_cpusp);
        ramsp = (EditText) findViewById(R.id.edt_ct_ramsp);
        romsp = (EditText) findViewById(R.id.edt_ct_romsp);
        cl1 = (EditText) findViewById(R.id.edt_ct_clsp1);
        cl2 = (EditText) findViewById(R.id.edt_ct_clsp2);
        cl3 = (EditText) findViewById(R.id.edt_ct_clsp3);
        detail = (EditText) findViewById(R.id.edt_ct_ctsp);
        price = (EditText) findViewById(R.id.edt_ct_price_sp);
        slsp = (EditText) findViewById(R.id.edt_ct_slsp);
        add_ord = (EditText) findViewById(R.id.add_ord);

        btn_save = (Button) findViewById(R.id.save_sp);

        imageViewU1 = (ImageView) findViewById(R.id.ct_u_img1);
        imageViewU2 = (ImageView) findViewById(R.id.ct_u_img2);
        imageViewU3 = (ImageView) findViewById(R.id.ct_u_img3);

        textView_name = (TextView) findViewById(R.id.text_ct_u_name);
        textView_company = (TextView) findViewById(R.id.text_ct_u_company);
        textViewcpu = (TextView) findViewById(R.id.text_ct_u_cpu);
        textViewram = (TextView) findViewById(R.id.text_ct_u_ram);
        textViewrom = (TextView) findViewById(R.id.text_ct_u_rom);
        textViewdetail = (TextView) findViewById(R.id.text_ct_u_ctsp);
        textViewprice = (TextView) findViewById(R.id.text_ct_u_price);
        textViewTT = (TextView) findViewById(R.id.text_tt);
        textViewSL = (TextView) findViewById(R.id.text_ct_u_sl);

        btn_buy = (Button) findViewById(R.id.btn_buy);
        btn_tang = (Button) findViewById(R.id.btn_tangsl);
        btn_giam = (Button) findViewById(R.id.btn_giamsl);

        radioButtoncl1 = (RadioButton) findViewById(R.id.cl1);
        radioButtoncl2 = (RadioButton) findViewById(R.id.cl2);
        radioButtoncl3 = (RadioButton) findViewById(R.id.cl3);

        key_device = getIntent().getStringExtra("key_device");
        key_user =  getIntent().getStringExtra("key_user");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(key_user);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                if (users.position.equals("admin"))
                {
                    layoutadmin.setVisibility(View.VISIBLE);
                    layoutuser.setVisibility(View.GONE);
                    load_data();
                }
                else {
                    layoutadmin.setVisibility(View.GONE);
                    layoutuser.setVisibility(View.VISIBLE);
                    load_data();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        radioButtonspm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1++;
                if (i1%2==1)
                {radioButtonspm.setChecked(true);}
                else {radioButtonspm.setChecked(false);}
            }
        });
        radioButtonspnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i2++;
                if (i2%2==1)
                {radioButtonspnb.setChecked(true);}
                else {radioButtonspnb.setChecked(false);}
            }
        });
        radioButtonspsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i3++;
                if (i3%2==1)
                {radioButtonspsell.setChecked(true);}
                else {radioButtonspsell.setChecked(false);}
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (namesp.getText().toString().length()==0 || c_sp.getText().toString().length()==0|| cl1.getText().toString().length()==0||cpusp.getText().toString().length()==0||ramsp.getText().toString().length()==0||romsp.getText().toString().length()==0||slsp.getText().toString().length()==0||detail.getText().toString().length()==0||price.getText().toString().length()==0)
                {
                    Toast.makeText(CTDeviceActivity.this, "Chọn đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else {
                    updateDevices();
                }
            }
        });
        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempSL = Integer.parseInt(textViewSL.getText().toString());
                tempSL++;
                if (tempSL>slmax)
                {textViewSL.setText(""+slmax);}
                else {textViewSL.setText(""+tempSL);}
                load_TT();
            }
        });
        btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempSL = Integer.parseInt(textViewSL.getText().toString());
                tempSL--;
                if (tempSL<=0)
                {textViewSL.setText("1");}
                else {textViewSL.setText(""+tempSL);}
                load_TT();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtoncl1.isChecked()==false && radioButtoncl2.isChecked()==false && radioButtoncl3.isChecked()==false)
                {Toast.makeText(CTDeviceActivity.this,"Vui lòng chọn phiên bản màu sắc",Toast.LENGTH_LONG).show();}else {
                    if (add_ord.getText().length()<10){
                        Toast.makeText(CTDeviceActivity.this,"Địa chỉ không hợp lệ",Toast.LENGTH_LONG).show();
                    }else{
                        OrdDevices ordDevices =new OrdDevices();
                        ordDevices.key_user = key_user;
                        ordDevices.key_devices = key_device;
                        req_time_now();
                        if (radioButtoncl1.isChecked())
                        {
                            ordDevices.color= radioButtoncl1.getText().toString();
                        }
                        if (radioButtoncl2.isChecked())
                        {
                            ordDevices.color= radioButtoncl2.getText().toString();
                        }
                        if (radioButtoncl3.isChecked())
                        {
                            ordDevices.color= radioButtoncl3.getText().toString();
                        }

                        ordDevices.time = time_now;
                        ordDevices.sl = textViewSL.getText().toString();
                        ordDevices.add = add_ord.getText().toString();
                        ordDevices.status = "0";
                        databaseReference= FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("Order").push().setValue(ordDevices, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error==null){
                                    int sl_new = slmax - Integer.parseInt(textViewSL.getText().toString());
                                    String string_new = ""+sl_new;
                                    databaseReference= FirebaseDatabase.getInstance().getReference();
                                    databaseReference.child("Devices").child(key_device).child("sl").setValue(string_new);
                                    Toast.makeText(CTDeviceActivity.this, "Hoàn thành", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CTDeviceActivity.this, MainActivity.class);
                                    intent.putExtra("key_user",key_user);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(CTDeviceActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void req_time_now() {
        android.icu.util.Calendar mcurrentTime = android.icu.util.Calendar.getInstance();
        int hour_now = mcurrentTime.get(android.icu.util.Calendar.HOUR_OF_DAY);
        int minute_now = mcurrentTime.get(android.icu.util.Calendar.MINUTE);
        time_now = ""+hour_now+":"+minute_now + "   "+day+"/"+month+"/"+year;
    }

    private void load_TT() {
        int tempSL = Integer.parseInt(textViewSL.getText().toString());
        int price = Integer.parseInt(textViewprice.getText().toString());
        int sum = tempSL*price;
        String priceSum = ""+sum;

        StringBuilder builder = new StringBuilder(""+priceSum);
        if (priceSum.length()>6) {
            int halfway1 = priceSum.length()-3;
            int halfway2 = priceSum.length()-6;
            builder.insert(halfway1, ".");
            builder.insert(halfway2, ".");
        }else if (priceSum.length()>9)
        {
            int halfway1 = priceSum.length()-3;
            int halfway2 = priceSum.length()-6;
            int halfway3 = priceSum.length()-9;
            builder.insert(halfway1, ".");
            builder.insert(halfway2, ".");
            builder.insert(halfway3, ".");
        }else {
            int halfway = priceSum.length()-3;
            builder.insert(halfway, ".");
        }
        textViewTT.setText(builder.toString()+" VNĐ" );
    }

    private void load_data() {
        databaseReferenceDevices = FirebaseDatabase.getInstance().getReference("Devices").child(key_device);
        databaseReferenceDevices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Devices devices = snapshot.getValue(Devices.class);
                urla1 = devices.img1;
                urla2 = devices.img2;
                urla3 = devices.img3;
                slmax = Integer.parseInt(devices.sl);
                // load admin
                type = devices.type;

                Picasso.with(img1.getContext()).load(devices.img1).into(img1);
                Picasso.with(img2.getContext()).load(devices.img2).into(img2);
                Picasso.with(img3.getContext()).load(devices.img3).into(img3);
                namesp.setText(devices.name);
                c_sp.setText(devices.company);
                ramsp.setText(devices.ram);
                romsp.setText(devices.rom);
                cpusp.setText(devices.cpu);
                if (devices.color1.length()>0)
                {cl1.setText(devices.color1);}
                if (devices.color2.length()>0)
                {cl2.setText(devices.color2);}
                if (devices.color3.length()>0)
                {cl3.setText(devices.color3);}

                price.setText(devices.price);
                slsp.setText(devices.sl);
                detail.setText(devices.detail);

                //load user

                Picasso.with(imageViewU1.getContext()).load(devices.img1).into(imageViewU1);
                Picasso.with(imageViewU2.getContext()).load(devices.img2).into(imageViewU2);
                Picasso.with(imageViewU3.getContext()).load(devices.img3).into(imageViewU3);

                textView_name.setText(devices.name);
                textView_company.setText(devices.company);
                textViewram.setText(devices.ram);
                textViewrom.setText(devices.rom);
                textViewcpu.setText(devices.cpu);
                textViewprice.setText(devices.price);
                textViewdetail.setText(devices.detail);
                if (devices.sl.equals("0")) {
                    textViewSL.setText("Hết");
                    btn_giam.setVisibility(View.GONE);
                    btn_tang.setVisibility(View.GONE);
                    btn_buy.setVisibility(View.GONE);
                }else {load_TT();}


                if (devices.color1.length()>0)
                {radioButtoncl1.setText(devices.color1);}
                else {radioButtoncl1.setVisibility(View.GONE);}
                if (devices.color2.length()>0)
                {radioButtoncl2.setText(devices.color2);}
                else {radioButtoncl2.setVisibility(View.GONE);}
                if (devices.color3.length()>0)
                {radioButtoncl3.setText(devices.color3);}
                else {radioButtoncl3.setVisibility(View.GONE);}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Special");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    TypeDevice typeDevice = dataSnapshot.getValue(TypeDevice.class);

                    if (typeDevice.id.equals(key_device))
                    {
                        key_type = dataSnapshot.getKey();
                        if (typeDevice.sp1) {radioButtonspm.setChecked(true);}

                        if (typeDevice.sp2) {radioButtonspnb.setChecked(true);}

                        if (typeDevice.sp3) {radioButtonspsell.setChecked(true);}
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateDevices() {
        Devices devices = new Devices();
        devices.img1 = urla1;
        devices.img2 = urla2;
        devices.img3 = urla3;
        devices.name = namesp.getText().toString();
        devices.company = c_sp.getText().toString();
        devices.ram = ramsp.getText().toString();
        devices.rom = romsp.getText().toString();
        devices.cpu = cpusp.getText().toString();
        devices.type = type;
        devices.color1 = cl1.getText().toString();
        devices.color2 = cl2.getText().toString();
        devices.color3 = cl3.getText().toString();
        devices.price= price.getText().toString();
        devices.sl=slsp.getText().toString();
        devices.detail=detail.getText().toString();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Devices").child(key_device).setValue(devices, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error==null){
                    TypeDevice typeDevice = new TypeDevice();
                    typeDevice.id=key_device;
                    typeDevice.sp1=typeDevice.sp2=typeDevice.sp3 = false;
                    if (radioButtonspm.isChecked()) {typeDevice.sp1=true;}
                    if (radioButtonspnb.isChecked()){typeDevice.sp2=true;}
                    if (radioButtonspsell.isChecked()){typeDevice.sp3=true;}
                    if (radioButtonspm.isChecked()==true || radioButtonspnb.isChecked()==true || radioButtonspsell.isChecked() == true)
                    {
                        DatabaseReference type= FirebaseDatabase.getInstance().getReference();
                        type.child("Special").child(key_type).setValue(typeDevice, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                            }
                        });
                    }


                    Toast.makeText(CTDeviceActivity.this, "hoàn thành", Toast.LENGTH_SHORT).show();
                    delete_edittext();
                    new_intent();
                }else {
                    Toast.makeText(CTDeviceActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            void new_intent(){
                Intent intent = new Intent(CTDeviceActivity.this,MainActivity.class);
                intent.putExtra("key_user",key_user);
                startActivity(intent);
            }
            void delete_edittext(){
                namesp.getText().clear();
                cpusp.getText().clear();
                price.getText().clear();
            }
        });
    }
}