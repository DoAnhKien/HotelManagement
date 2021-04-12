package com.example.hotelmanament2.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hotelmanament2.callback.MyCustomerOnClicked;
import com.example.hotelmanament2.databinding.ItemHotelCustomerBinding;
import com.example.hotelmanament2.model.Customer;
import com.example.hotelmanament2.model.HotelRoom;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> implements Filterable {

    private List<Customer> customers;
    private List<Customer> customersListFilterFull;

    private MyCustomerOnClicked myCustomerOnClicked;

    public MyCustomerOnClicked getMyCustomerOnClicked() {
        return myCustomerOnClicked;
    }

    public void setMyCustomerOnClicked(MyCustomerOnClicked myCustomerOnClicked) {
        this.myCustomerOnClicked = myCustomerOnClicked;
    }

    public CustomerAdapter(List<Customer> customers) {
        this.customers = customers;
        this.customersListFilterFull = new ArrayList<>(customers);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHotelCustomerBinding binding = ItemHotelCustomerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer currentCustomer = customers.get(position);
        holder.bind(customers.get(position));
        holder.itemView.setOnClickListener(v -> {
            myCustomerOnClicked.handleMyClicked(position, currentCustomer);
        });
        holder.itemView.setOnLongClickListener(v -> {
            myCustomerOnClicked.handleMyClicked(position, currentCustomer);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public Customer getCustomerAt(int position) {
        return customers.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHotelCustomerBinding binding;

        public ViewHolder(ItemHotelCustomerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {

            });
            itemView.setOnLongClickListener(v -> true);
        }

        public void bind(Customer customer) {
            binding.setCustomer(customer);
        }
    }


    @Override
    public Filter getFilter() {
        return customersFilter;
    }

    public void submitListCustomer(List<Customer> customers1) {
        customers.clear();
        customers.addAll(customers1);
        customersListFilterFull.clear();
        customersListFilterFull.addAll(customers1);
        notifyDataSetChanged();
    }


    private Filter customersFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Customer> filteredList = new ArrayList<>();

            if (TextUtils.isEmpty(constraint)) {
                filteredList.addAll(customersListFilterFull);
            } else {
                String filterString = constraint.toString().toLowerCase();
                for (Customer currentCustomer : customersListFilterFull) {
                    if (currentCustomer.getCustomerName().toLowerCase().contains(filterString)) {
                        filteredList.add(currentCustomer);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customers.clear();
            customers.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
