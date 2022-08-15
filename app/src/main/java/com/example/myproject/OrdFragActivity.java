package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myproject.adapter.OrdAdapter;
import com.example.myproject.obj.OrdDevices;
import com.example.myproject.obj.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrdFragActivity extends Fragment {
    RecyclerView recyclerView;
    TextView textView;
    OrdAdapter ordAdapter;
    DatabaseReference databaseReference;
    String key_user = "", time = "";
    String admin = "";

    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ord_frag, container, false);

        recyclerView = view.findViewById(R.id.recycler_list_ord);
        textView = (TextView) view.findViewById(R.id.text_myord);

        Bundle bundle = getActivity().getIntent().getExtras();
        key_user = bundle.getString("key_user");

        load_data();

        return view;
    }

    private void load_data() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(key_user);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                if (user.position.equals("admin")) {
                    admin = "0";
                    textView.setText("Quản lý đơn hàng");
                } else {
                    admin = "1";
                    textView.setText("" + user.fullname);
                }
                load_list(admin);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void load_list(String admin) {
        //Toast.makeText(getActivity(),""+all+"apk "+ admin,Toast.LENGTH_LONG).show();
        ordAdapter = new OrdAdapter(getContext());
        if (ordAdapter == null) {
            ordAdapter = new OrdAdapter(getContext());
        } else {
            ordAdapter.notifyDataSetChanged();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ordAdapter.setData(getListOrd(admin));
        recyclerView.setAdapter(ordAdapter);

    }

    private List<OrdDevices> getListOrd(String admin) {
        List<OrdDevices> spOrdList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (admin.equals("0")) {
                        final OrdDevices spOrd = dataSnapshot.getValue(OrdDevices.class);
                        spOrd.add = dataSnapshot.getKey();
                        spOrd.key_user = key_user;
                        spOrdList.add(spOrd);

                    } else {
                        final OrdDevices spOrd = dataSnapshot.getValue(OrdDevices.class);
                        if (spOrd.status.length() == 1) {
                            if (spOrd.key_user.equals(key_user)) {
                                spOrd.add = dataSnapshot.getKey();
                                spOrd.key_user = key_user;
                                spOrdList.add(spOrd);
                            }
                        }

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return spOrdList;
    }
}

