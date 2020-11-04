package com.DevayaniDinda.gd10_image_x_yyyy;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int number_tabs;

    public PageAdapter(FragmentManager fm, int number_tabs) {
        super(fm);
        this.number_tabs = number_tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ShaderFragment();
            case 1:
                return new BitmapFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return number_tabs;
    }
}