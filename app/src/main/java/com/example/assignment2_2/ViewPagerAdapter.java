package com.example.assignment2_2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String[] titles = {"messages", "Calls"};


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new MessagesFragment();
            case 1: return new CallsFragment();
        }
        return new MessagesFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
