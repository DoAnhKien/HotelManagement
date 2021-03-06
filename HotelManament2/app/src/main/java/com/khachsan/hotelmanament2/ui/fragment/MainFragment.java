package com.khachsan.hotelmanament2.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.khachsan.hotelmanament2.adapter.HotelRoomAdapter;
import com.example.hotelmanament2.databinding.DialogCustomItemHotelRoomBinding;
import com.example.hotelmanament2.databinding.FragmentMainBinding;
import com.khachsan.hotelmanament2.model.HotelRoom;
import com.khachsan.hotelmanament2.ui.activity.AddHotelRoomActivity;
import com.khachsan.hotelmanament2.ui.activity.EditRoomActivity;
import com.khachsan.hotelmanament2.ui.activity.GeneralInformationActivity;
import com.khachsan.hotelmanament2.util.Const;
import com.khachsan.hotelmanament2.util.VerticalSpaceItemDecoration;

import com.khachsan.hotelmanament2.viewmodel.HotelRoomViewmodel;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    public static final int SIZE_CUSTOMER = 100;
    private static FragmentMainBinding binding;
    public static MainFragment INSTANCES;

    public static List<HotelRoom> hotelRoomList;
    private static DialogCustomItemHotelRoomBinding dialogBinding;

    private static final int VERICALSPACEHEIGHT = 10;
    public static HotelRoomAdapter adapter;
    public static HotelRoomViewmodel hotelRoomViewmodel;
    public static int POSITION;

    public static MainFragment getInstance() {
        if (INSTANCES == null) {
            INSTANCES = new MainFragment();
        }
        return INSTANCES;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.fabAddHotelRoom.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddHotelRoomActivity.class);
            startActivityForResult(intent, SIZE_CUSTOMER);
        });

        hotelRoomList = new ArrayList<>();
        binding.rvMain.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        binding.rvMain.setHasFixedSize(true);
        binding.rvMain.addItemDecoration(new VerticalSpaceItemDecoration(VERICALSPACEHEIGHT));
        adapter = new HotelRoomAdapter(hotelRoomList);
        binding.rvMain.setAdapter(adapter);
        adapter.setMyOnClicked((hotelRoom, position) -> {
//            createBottomDialog(hotelRoom, position);
            createDialogForChoose(hotelRoom, position);
        });


        hotelRoomViewmodel = new ViewModelProvider(requireActivity()).get(HotelRoomViewmodel.class);
        hotelRoomViewmodel.getAllHotelRoom().observe(getActivity(), hotelRooms ->
                adapter.submitList(hotelRooms));
        //        hotelRoomViewmodel.createRoomHotel();
        setUpItemTouchHelper();

        return binding.getRoot();
    }


    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedHotelRoomPosition = viewHolder.getAdapterPosition();
                HotelRoom hotelRoom = adapter.getHotelRoomAt(swipedHotelRoomPosition);
                if (hotelRoom.getStatus().contains("??ang s??? d???ng")) {
                    Toast.makeText(getActivity(), "Ph??ng n??y ??ang s??? d???ng kh??ng ???????c ph??p x??a", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } else {
                    hotelRoomViewmodel.deleteHotelRoom(hotelRoom);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "HotelRoom is deleted", Toast.LENGTH_SHORT).show();
                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvMain);
    }

    private void createDialogForChoose(HotelRoom hotelRoom, int position) {
        POSITION = position;
        Dialog dialog = new Dialog(getActivity());
        dialogBinding = DialogCustomItemHotelRoomBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.tvDialogHotelRoomTitle.setText("B???n hay ch???n thao t??c c???n th???c hi???n v???i ph??ng " + String.valueOf(hotelRoom.getRoomNumber()));
        dialogBinding.tvDialogCreateBill.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), GeneralInformationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Const.KEY_TO_SEND_HOTEL_ROOM, hotelRoom);
            intent.putExtra(Const.KEY_BUNDLE_TO_SEND_HOTEL_ROOM, bundle);
            startActivityForResult(intent, SIZE_CUSTOMER);
        });

        dialogBinding.tvDialogEditHotelRoom.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditRoomActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Const.KEY_TO_EDIT_HOTEL_ROOM, hotelRoom);
            intent.putExtra(Const.KEY_BUNDLE_TO_EDIT_HOTEL_ROOM, bundle);
            startActivity(intent);
        });

        dialogBinding.tvDialogCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SIZE_CUSTOMER) {
            if (resultCode == getActivity().RESULT_OK) {
                if (data != null) {
                    int size = data.getIntExtra("sizePosition", 1);
                    if (size == 0) {
                        HotelRoom hotelRoom = hotelRoomList.get(POSITION);
                        hotelRoom.setStatus("C??n tr???ng");
                        hotelRoomViewmodel.updateHotelRoom(hotelRoom);
                    } else {
                        HotelRoom hotelRoom = hotelRoomList.get(POSITION);
                        hotelRoom.setStatus("??ang s??? d???ng");
                        hotelRoomViewmodel.updateHotelRoom(hotelRoom);
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
