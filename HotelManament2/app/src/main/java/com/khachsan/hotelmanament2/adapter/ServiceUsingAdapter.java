package com.khachsan.hotelmanament2.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanament2.databinding.ItemServiceUsingBinding;
import com.khachsan.hotelmanament2.model.ServiceUsing;

import java.util.List;

public class ServiceUsingAdapter extends RecyclerView.Adapter<ServiceUsingAdapter.ViewHolder> {

    List<ServiceUsing> serviceUsings;

    public ServiceUsingAdapter(List<ServiceUsing> serviceUsings) {
        this.serviceUsings = serviceUsings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServiceUsingBinding binding = ItemServiceUsingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(serviceUsings.get(position));
    }

    @Override
    public int getItemCount() {
        return serviceUsings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiceUsingBinding binding;

        public ViewHolder(ItemServiceUsingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ServiceUsing serviceUsing) {
            binding.setServiecs(serviceUsing);
        }
    }

    public ServiceUsing getServiceUsingAt(int position) {
        return serviceUsings.get(position);
    }
}

