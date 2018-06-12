package com.fragdemo.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fragdemo.app.adapter.SimpleFragmentPagerAdapter;
import com.fragdemo.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupview();
        addfragment();

    }

    private void setupview() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private void addfragment() {
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        // Set the adapter onto the view pager
        binding.vPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.vPager);
    }
}
