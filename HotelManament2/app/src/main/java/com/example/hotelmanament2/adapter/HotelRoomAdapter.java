package com.example.hotelmanament2.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hotelmanament2.callback.MyOnClicked;
import com.example.hotelmanament2.databinding.ItemHotelRoomBinding;
import com.example.hotelmanament2.model.HotelRoom;

import java.util.ArrayList;
import java.util.List;

public class HotelRoomAdapter extends RecyclerView.Adapter<HotelRoomAdapter.ViewHolder> implements Filterable {

    private List<HotelRoom> hotelRooms;
    private MyOnClicked myOnClicked;

    private List<HotelRoom> hotelRoomsListFull;


    public void setMyOnClicked(MyOnClicked myOnClicked) {
        this.myOnClicked = myOnClicked;
    }

    public HotelRoomAdapter(List<HotelRoom> hotelRoomList) {
        this.hotelRooms = hotelRoomList;
        hotelRoomsListFull = new ArrayList<>(hotelRoomList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHotelRoomBinding binding = ItemHotelRoomBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotelRoom currentHotelRoom = hotelRooms.get(position);
        holder.bind(hotelRooms.get(position));
        holder.itemView.setOnClickListener(v -> {
            myOnClicked.handleHotelRoomClick(currentHotelRoom, position);
        });

    }

    @Override
    public int getItemCount() {
        return hotelRooms.size();
    }

    public HotelRoom getHotelRoomAt(int position) {
        return hotelRooms.get(position);
    }

    public void submitList(List<HotelRoom> hotelRooms) {
        this.hotelRooms.clear();
        this.hotelRooms.addAll(hotelRooms);
        this.hotelRoomsListFull.clear();
        this.hotelRoomsListFull.addAll(hotelRooms);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHotelRoomBinding binding;

        public ViewHolder(ItemHotelRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {

            });
        }

        public void bind(HotelRoom hotelRoom) {
            binding.setHotelRoom(hotelRoom);
        }
    }

    @Override
    public Filter getFilter() {
        return hotelRoomsFilter;
    }


    private Filter hotelRoomsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<HotelRoom> filteredList = new ArrayList<>();

            if (TextUtils.isEmpty(constraint)) {
                filteredList.addAll(hotelRoomsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HotelRoom item : hotelRoomsListFull) {
                    if (item.getStatus().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hotelRooms.clear();
            hotelRooms.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
