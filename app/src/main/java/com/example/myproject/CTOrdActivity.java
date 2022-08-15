package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class CTOrdActivity extends AppCompatActivity {
ImageView imageView1,imageView2,imageView3;
TextView textViewKeyuser,textViewname,textViewcompany,textViewCPU,textViewRam,textViewRom,textViewCT,textViewADD,textViewSL,textViewTT,textViewTime;
Button button_update;
RadioButton radioButton0,radioButton1;
TextView textViewSTT;
DatabaseReference databaseReference,databaseReferenceDevices;
String key_user,key_ord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctord);

        imageView1 = (ImageView) findViewById(R.id.ct_ord_img1);
        imageView2 = (ImageView) findViewById(R.id.ct_ord_img2);
        imageView3 = (ImageView) findViewById(R.id.ct_ord_img3);

        radioButton1 = (RadioButton) findViewById(R.id.status1);
        radioButton0 = (RadioButton) findViewById(R.id.status0);

        button_update =(Button) findViewById(R.id.btn_update);
        textViewKeyuser = (TextView) findViewById(R.id.text_ct_ord_nameuser);
        textViewname = (TextView) findViewById(R.id.text_ct_ord_name);
        textViewcompany = (TextView) findViewById(R.id.text_ct_ord_company);
        textViewCPU = (TextView) findViewById(R.id.ct_ord_cpu);
        textViewRam = (TextView) findViewById(R.id.ct_ord_ram);
        textViewRom = (TextView) findViewById(R.id.ct_ord_rom);
        textViewCT = (TextView) findViewById(R.id.ct_ord_ct);
        textViewTime = (TextView) findViewById(R.id.ct_ord_time);
        textViewADD = (TextView) findViewById(R.id.ct_ord_add);
        textViewSL = (TextView) findViewById(R.id.ct_ord_sl);
        textViewTT = (TextView) findViewById(R.id.ct_ord_tt);
        textViewSTT = (TextView) findViewById(R.id.ct_ord_stt);

        key_ord = getIntent().getStringExtra("key_ord");
        key_user =  getIntent().getStringExtra("key_user");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(key_user);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                if (users.position.equals("admin"))
                {
                    textViewSTT.setVisibility(View.GONE);
                }
                else {
                    textViewKeyuser.setVisibility(View.GONE);
                    button_update.setVisibility(View.GONE);
                    radioButton0.setVisibility(View.GONE);
                    radioButton1.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Order").child(key_ord);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OrdDevices ordDevices = snapshot.getValue(OrdDevices.class);
                if (ordDevices.status.equals("0")){
                    textViewSTT.setText("Đang giao hàng");
                    radioButton0.setChecked(true);
                    radioButton1.setChecked(false);
                }else {
                    radioButton1.setChecked(true);
                    radioButton0.setChecked(false);
                    textViewSTT.setText("Đã nhân hàng");
                }
                textViewKeyuser.setText(ordDevices.key_user);
                textViewSL.setText(ordDevices.sl+"   (Chiếc)");
                textViewTime.setText(ordDevices.time);
                textViewADD.setText(ordDevices.add);

                databaseReferenceDevices = FirebaseDatabase.getInstance().getReference("Devices").child(ordDevices.key_devices);
                databaseReferenceDevices.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Devices devices = snapshot.getValue(Devices.class);

                        Picasso.with(imageView1.getContext()).load(devices.img1).into(imageView1);
                        Picasso.with(imageView2.getContext()).load(devices.img2).into(imageView2);
                        Picasso.with(imageView3.getContext()).load(devices.img3).into(imageView3);

                        textViewname.setText(devices.name);
                        textViewcompany.setText(devices.company);
                        textViewRam.setText(devices.ram);
                        textViewRom.setText(devices.rom);
                        textViewCPU.setText(devices.cpu);

                        int tempSL = Integer.parseInt(ordDevices.sl);
                        int price = Integer.parseInt(devices.price);
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

                        textViewCT.setText(devices.detail);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        textViewKeyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = textViewKeyuser.getText().toString();
                databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(temp);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        int gra =Gravity.CENTER;
                        final Dialog dialog = new Dialog(CTOrdActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_show_user);

                        Window window = dialog.getWindow();
                        if (window == null){return;}
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        WindowManager.LayoutParams win = window.getAttributes();
                        win.gravity = gra;
                        window.setAttributes(win);

                        if (Gravity.CENTER== gra){
                            dialog.setCancelable(true);
                        }else {
                            dialog.setCancelable(false);
                        }

                        ImageView imageViewDialog = dialog.findViewById(R.id.show_avt);
                        TextView textname = dialog.findViewById(R.id.show_name);
                        TextView textdob = dialog.findViewById(R.id.show_dob);
                        TextView textphone = dialog.findViewById(R.id.show_phone);

                        Picasso.with(imageViewDialog.getContext()).load(users.avatar).into(imageViewDialog);
                        textname.setText(users.fullname);
                        textdob.setText(users.dateofbirth);
                        textphone.setText(users.phonenumber);

                        textphone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String uri = "tel:" + users.phonenumber;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        });

                        dialog.show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton0.isChecked())
                {
                    databaseReference= FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Order").child(key_ord).child("status").setValue("0");
                    Intent intent = new Intent(CTOrdActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    databaseReference= FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Order").child(key_ord).child("status").setValue("1");
                    Intent intent = new Intent(CTOrdActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}