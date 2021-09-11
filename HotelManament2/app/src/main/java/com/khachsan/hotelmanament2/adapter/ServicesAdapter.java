package com.khachsan.hotelmanament2.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khachsan.hotelmanament2.callback.MyServicesOnClicked;
import com.example.hotelmanament2.databinding.ItemServicesBinding;
import com.khachsan.hotelmanament2.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> implements Filterable {

    private List<Service> services;
    private List<Service> serviceListFitlterFull;
    private MyServicesOnClicked myServicesOnClicked;

    public ServicesAdapter(List<Service> services) {
        this.services = services;
        this.serviceListFitlterFull = new ArrayList<>(services);
    }

    public MyServicesOnClicked getMyServicesOnClicked() {
        return myServicesOnClicked;
    }

    public void setMyServicesOnClicked(MyServicesOnClicked myServicesOnClicked) {
        this.myServicesOnClicked = myServicesOnClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServicesBinding binding = ItemServicesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(services.get(position));
        holder.itemView.setOnLongClickListener(v -> {
            myServicesOnClicked.handleMyServiceOnLongClicked(services.get(position), position);
            return true;
        });
        holder.itemView.setOnClickListener(v -> myServicesOnClicked.handleMyServiceOnClicked(services.get(position), position));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemServicesBinding binding;

        public ViewHolder(ItemServicesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {

            });
            itemView.setOnLongClickListener(v -> true);
        }

        public void bind(Service service) {
            binding.setServiecs(service);
        }
    }

    public Service getServiceAt(int position) {
        return services.get(position);
    }

    public void submitListServices(List<Service> services) {
        this.services.clear();
        this.services.addAll(services);
        serviceListFitlterFull.clear();
        serviceListFitlterFull.addAll(services);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return servicesFilter;
    }

    private Filter servicesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Service> filtedServices = new ArrayList<>();

            if (TextUtils.isEmpty(constraint)) {
                filtedServices.addAll(serviceListFitlterFull);
            } else {
                String filteredString = constraint.toString().toLowerCase();
                for (Service currentServices : serviceListFitlterFull) {
                    if (currentServices.getNameServices().toLowerCase().contains(filteredString)) {
                        filtedServices.add(currentServices);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtedServices;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            services.clear();
            services.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
