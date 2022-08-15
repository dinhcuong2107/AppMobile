package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myproject.obj.Devices;
import com.example.myproject.obj.TypeDevice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddDevicesActivity extends AppCompatActivity {
String key_user;
LinearLayout layoutcl2,layoutcl3;
RadioButton radioButtonLap,radioButtonPhone,radioButtonPK,radioButtonspm,radioButtonspnb,radioButtonspsell;
ImageView img1,img2,img3,imageView;
EditText namesp,c_sp,cpusp,ramsp,romsp,cl1,cl2,cl3,detail,price,slsp;
Button btn_add,btn_cl2,btn_cl3;
int temp=1,i1=0,i2=0,i3=0;
DatabaseReference databaseReference;
    String urla1="",urla2="",urla3="";
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_devices);

        radioButtonLap = (RadioButton) findViewById(R.id.choose_lap);
        radioButtonPhone = (RadioButton) findViewById(R.id.choose_phone);
        radioButtonPK = (RadioButton) findViewById(R.id.choose_pk);

        radioButtonspm = (RadioButton) findViewById(R.id.add_spm);
        radioButtonspnb = (RadioButton) findViewById(R.id.add_spnb);
        radioButtonspsell = (RadioButton) findViewById(R.id.add_spsell);

        img1 = (ImageView) findViewById(R.id.add_img1);
        img2 = (ImageView) findViewById(R.id.add_img2);
        img3 = (ImageView) findViewById(R.id.add_img3);

        namesp = (EditText) findViewById(R.id.namesp);
        c_sp = (EditText) findViewById(R.id.companysp);
        cpusp = (EditText) findViewById(R.id.cpusp);
        ramsp = (EditText) findViewById(R.id.ramsp);
        romsp = (EditText) findViewById(R.id.romsp);
        cl1 = (EditText) findViewById(R.id.clsp1);
        cl2 = (EditText) findViewById(R.id.clsp2);
        cl3 = (EditText) findViewById(R.id.clsp3);
        detail = (EditText) findViewById(R.id.ctsp);
        price = (EditText) findViewById(R.id.price_sp);
        slsp = (EditText) findViewById(R.id.slsp);

        btn_add = (Button) findViewById(R.id.add_sp);
        btn_cl2 = (Button) findViewById(R.id.btn_clsp1);
        btn_cl3 = (Button) findViewById(R.id.btn_clsp2);

        layoutcl2 = (LinearLayout) findViewById(R.id.layoutcl2);
        layoutcl3 = (LinearLayout) findViewById(R.id.layoutcl3);

        layoutcl2.setVisibility(View.GONE);
        layoutcl3.setVisibility(View.GONE);

        // nhận data từ intent
        key_user =  getIntent().getStringExtra("key_user");

        btn_cl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp == 1)
                {
                    temp=2;
                    layoutcl2.setVisibility(View.VISIBLE);
                    cl2.setText("");

                }else {temp=1;
                    layoutcl2.setVisibility(View.GONE);
                    cl2.setText("");
                }
            }
        });

        btn_cl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp == 2)
                {
                    temp=3;
                    layoutcl3.setVisibility(View.VISIBLE);
                    cl3.setText("");

                }else {temp=2;
                    cl3.setText("");
                    layoutcl3.setVisibility(View.GONE);}
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

        radioButtonPK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddDevicesActivity.this);
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
                radioButtonLap.setChecked(true);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                imageView=img1;
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                imageView=img2;
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                imageView=img3;
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (urla1.equals("") || urla2.equals("") || urla3.equals(""))
                {
                    Toast.makeText(AddDevicesActivity.this, "Chọn đủ 3 ảnh",Toast.LENGTH_LONG).show();
                }
                else {
                    if (namesp.getText().toString().length()==0 || c_sp.getText().toString().length()==0||cpusp.getText().toString().length()==0||ramsp.getText().toString().length()==0||romsp.getText().toString().length()==0||slsp.getText().toString().length()==0||detail.getText().toString().length()==0||price.getText().toString().length()==0)
                    {
                        Toast.makeText(AddDevicesActivity.this, "Chọn đủ thông tin",Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (temp==1)
                        {
                            if (cl1.getText().toString().length()==0)
                            {
                                Toast.makeText(AddDevicesActivity.this, "Chọn đủ thông tin màu sắc",Toast.LENGTH_LONG).show();
                            }else {
                                addDevice();
                            }
                        }
                        if (temp==2)
                        {
                            if (cl1.getText().toString().length()==0||cl2.getText().toString().length()==0)
                            {
                                Toast.makeText(AddDevicesActivity.this, "Chọn đủ thông tin màu sắc",Toast.LENGTH_LONG).show();
                            }else {
                                addDevice();
                            }
                        }
                        if (temp==4)
                        {
                            if (cl1.getText().toString().length()==0||cl2.getText().toString().length()==0||cl3.getText().toString().length()==0)
                            {
                                Toast.makeText(AddDevicesActivity.this, "Chọn đủ thông tin màu sắc",Toast.LENGTH_LONG).show();
                            }else {
                                addDevice();
                            }
                        }

                    }
                }}
            });
        }

    private void addDevice() {
        Devices devices = new Devices();
        devices.img1=urla1;
        devices.img2=urla2;
        devices.img3=urla3;
        if (radioButtonLap.isChecked()==true)
        {
            devices.type = "1";
        }
        if (radioButtonPhone.isChecked()==true)
        {
            devices.type = "2";
        }
        if (radioButtonLap.isChecked()==false && radioButtonPhone.isChecked()==false)
        {
            Toast.makeText(AddDevicesActivity.this, "Chọn loại sản phẩm",Toast.LENGTH_LONG).show();
            return;
        }
        devices.name = namesp.getText().toString();
        devices.company = c_sp.getText().toString();
        devices.ram = ramsp.getText().toString();
        devices.rom = romsp.getText().toString();
        devices.cpu = cpusp.getText().toString();
        devices.color1 = cl1.getText().toString();
        devices.color2 = cl2.getText().toString();
        devices.color3 = cl3.getText().toString();
        devices.price= price.getText().toString();
        devices.sl=slsp.getText().toString();
        devices.detail=detail.getText().toString();
        databaseReference= FirebaseDatabase.getInstance().getReference();

    /*                          databaseReference.child("Users").push().setValue(user);
                                Toast.makeText(CreatedActivity.this, "hoàn thành", Toast.LENGTH_SHORT).show();
                                new_intent();
    */
        databaseReference.child("Devices").push().setValue(devices, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error==null){
                    TypeDevice typeDevice = new TypeDevice();
                    typeDevice.id = ref.getKey();
                    typeDevice.sp1=typeDevice.sp2=typeDevice.sp3 = false;
                    if (radioButtonspm.isChecked()) {typeDevice.sp1=true;}
                    if (radioButtonspnb.isChecked()){typeDevice.sp2=true;}
                    if (radioButtonspsell.isChecked()){typeDevice.sp3=true;}
                    if (radioButtonspm.isChecked()==true || radioButtonspnb.isChecked()==true || radioButtonspsell.isChecked() == true)
                    {
                        DatabaseReference type= FirebaseDatabase.getInstance().getReference();
                        type.child("Special").push().setValue(typeDevice, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                            }
                        });
                    }


                    Toast.makeText(AddDevicesActivity.this, "hoàn thành", Toast.LENGTH_SHORT).show();
                    delete_edittext();
                    new_intent();
                }else {
                    Toast.makeText(AddDevicesActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            void new_intent(){
                Intent intent = new Intent(AddDevicesActivity.this,MainActivity.class);
                startActivity(intent);
            }
            void delete_edittext(){
                namesp.getText().clear();
                cpusp.getText().clear();
                price.getText().clear();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

                FirebaseStorage storage;
                StorageReference storageReference;
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
                if(filePath != null)
                {
                    final ProgressDialog progressDialog = new ProgressDialog(AddDevicesActivity.this);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Uri downloadUrl = uri;
                                            if (imageView==img1){urla1=downloadUrl.toString();}
                                            if (imageView==img2){urla2=downloadUrl.toString();}
                                            if (imageView==img3){urla3=downloadUrl.toString();}
                                        }
                                    });
                                    Toast.makeText(AddDevicesActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(AddDevicesActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                            .getTotalByteCount());
                                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
                                }
                            });
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}