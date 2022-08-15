package com.example.myproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myproject.HomeFragActivity;
import com.example.myproject.OrdFragActivity;
import com.example.myproject.SettingActivity;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragActivity();
            case 1:
                return new OrdFragActivity();
            case 2:
                return new SettingActivity();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
