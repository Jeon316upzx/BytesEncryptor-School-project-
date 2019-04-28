package com.example.ifeanyi.bytesencryptor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by IFEANYI on 1/12/2017.
 */
public class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new EncryptFragment();
            case 1:
                return new DecryptFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
