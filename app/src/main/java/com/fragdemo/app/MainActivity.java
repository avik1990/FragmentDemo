package com.fragdemo.app;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fragdemo.app.adapter.SimpleFragmentPagerAdapter;
import com.fragdemo.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BlankFragment2.SendMessage {

    ActivityMainBinding binding;
    static ViewPager vPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupview();
        addfragment();

        runOnUiThread(new Runnable(){

            @Override
            public void run() {

            }
        });

    }

    private void setupview() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vPager = binding.vPager;
    }

    private void addfragment() {
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        // Set the adapter onto the view pager
        binding.vPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.vPager);
    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.v_pager + ":" + 2;
        BlankFragment3 f = (BlankFragment3) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (android.support.v4.app.Fragment fragment : getSupportFragmentManager().getFragments()) {
            //System.out.println("@#@");
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
