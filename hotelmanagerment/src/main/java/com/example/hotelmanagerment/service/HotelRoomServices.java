package com.example.hotelmanagerment.service;

import com.example.hotelmanagerment.model.HotelRoom;
import com.example.hotelmanagerment.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelRoomServices {

    @Autowired
    private HotelRoomRepository hotelRoomRepository;


    public HotelRoom insertOrUpDateHotelRoom(HotelRoom hotelRoom) {
        return hotelRoomRepository.save(hotelRoom);
    }

    public void deleteHotelRoomById(int id) {
        hotelRoomRepository.deleteById(id);
    }


    public List<HotelRoom> getAllHotelRooms() {
        return (List<HotelRoom>) hotelRoomRepository.findAll();
    }

    public HotelRoom getHotelRoomById(int id) {
        return hotelRoomRepository.findById(id).get();
    }

    public long countHotelRoom() {
        return hotelRoomRepository.count();
    }


}
