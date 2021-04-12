package com.example.hotelmanament2.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.adapter.ServicesAdapter;
import com.example.hotelmanament2.callback.MyServicesOnClicked;
import com.example.hotelmanament2.databinding.ActivityShowListServicesBinding;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.util.Const;
import com.example.hotelmanament2.viewmodel.ServiceUsingViewModel;
import com.example.hotelmanament2.viewmodel.ServicesViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowListServicesActivity extends AppCompatActivity {

    private ActivityShowListServicesBinding binding;

    private ServicesViewModel viewModel;
    private ServiceUsingViewModel serviceUsingViewModel;

    private List<Service> services;
    public static ShowListServicesActivity showListServicesActivity;

    public static ServicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowListServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        setUpItemTouchHelper();

    }


    private void initViews() {
        showListServicesActivity = new ShowListServicesActivity();

        services = new ArrayList<>();
        adapter = new ServicesAdapter(services);

        binding.rvServices.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.rvServices.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(ServicesViewModel.class);

        viewModel.getAllServies().observe(this, services1 -> {
            adapter.submitListServices(services1);
        });


        adapter.setMyServicesOnClicked(new MyServicesOnClicked() {
            @Override
            public void handleMyServiceOnClicked(Service service, int position) {
                Intent intent = new Intent(ShowListServicesActivity.this, EditServicesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Const.KEY_TO_EDIT_SERVICE, service);
                intent.putExtra(Const.KEY_BUNDLE_TO_EDIT_SERVICE, bundle);
                startActivity(intent);
            }

            @Override
            public void handleMyServiceOnLongClicked(Service service, int position) {
                Toast.makeText(ShowListServicesActivity.this, "On Long Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        binding.rvServices.setAdapter(adapter);
        binding.fabAddServices.setOnClickListener(v -> {
            startActivity(new Intent(ShowListServicesActivity.this, AddServiceActivity.class));
        });

    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedHotelRoomPosition = viewHolder.getAdapterPosition();
                Service service = adapter.getServiceAt(swipedHotelRoomPosition);
                viewModel.deleteService(service);
                adapter.notifyDataSetChanged();
                Toast.makeText(ShowListServicesActivity.this, "Service is deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvServices);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


}