package com.khachsan.hotelmanament2.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityMainBinding;
import com.khachsan.hotelmanament2.ui.fragment.IntroducesFragment;
import com.khachsan.hotelmanament2.ui.fragment.ListCustomerUsingFragment;
import com.khachsan.hotelmanament2.ui.fragment.ListServicesUsingFragment;
import com.khachsan.hotelmanament2.ui.fragment.MainFragment;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private Fragment currentFragment = null;
    int k = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        closeDrawer();
        initFragment();
        setNavigationView();

    }

    public void closeDrawer() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.close();
        }
    }

    public void initFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, new MainFragment(), "MainFragment");
        fragmentTransaction.addToBackStack("MainFragment");
        fragmentTransaction.commit();
    }


    private void setNavigationView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        binding.navMain.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.openDrawer(GravityCompat.START);
            } else {
                binding.drawer.close();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_show_list_customer_using:
                Fragment f = fragmentManager.findFragmentById(R.id.content);
                if (f instanceof ListCustomerUsingFragment) {
                    break;
                } else {
                    FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                    fragmentTransaction3.add(R.id.content, new ListCustomerUsingFragment(), "ListCustomerUsingFragment");
                    fragmentTransaction3.addToBackStack("ListCustomerUsingFragment");
                    fragmentTransaction3.commit();
                }
                break;
            case R.id.nav_show_customer_services:
                Intent intent = new Intent(MainActivity.this, ShowListServicesActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_show_info_app:
                Fragment f4 = fragmentManager.findFragmentById(R.id.content);
                if (f4 instanceof IntroducesFragment) {
                    break;
                } else {
                    FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                    fragmentTransaction3.add(R.id.content, new IntroducesFragment(), "IntroduceFragment");
                    fragmentTransaction3.addToBackStack("IntroduceFragment");
                    fragmentTransaction3.commit();
                }
                break;


        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentFragment = fragmentManager.findFragmentById(R.id.content);
                if (currentFragment instanceof MainFragment) {
                    MainFragment.adapter.getFilter().filter(newText);
                } else if (currentFragment instanceof ListCustomerUsingFragment) {
                    ListCustomerUsingFragment.adapter.getFilter().filter(newText);
                } else if (currentFragment instanceof ListServicesUsingFragment) {
                    ListServicesUsingFragment.adapter.getFilter().filter(newText);
                } else {
                    Toast.makeText(MainActivity.this, "Fragment này không hỗ trợ tìm kiếm", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        return true;
    }


    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content);
        if (f instanceof MainFragment) {
            finish();
        }
        super.onBackPressed();
    }

}