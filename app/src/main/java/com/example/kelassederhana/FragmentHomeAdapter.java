package com.example.kelassederhana;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomeAdapter extends FragmentPagerAdapter {

    private final List<Fragment> list= new ArrayList<>();
    private  final List<String> locate=new ArrayList<>();

    public FragmentHomeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return locate.size();
    }
}
