package com.example.hotelmanagerment.controller;

import com.example.hotelmanagerment.model.HotelRoom;
import com.example.hotelmanagerment.service.HotelRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelroom")
public class HotelRoomController {

    @Autowired
    private HotelRoomServices hotelRoomServices;


    @PostMapping("/add")
    public HotelRoom insertOrSaveHotelRoom(@RequestBody HotelRoom hotelRoom) {
        return hotelRoomServices.insertOrUpDateHotelRoom(hotelRoom);
    }

    @PostMapping("/delete/{id}")
    public void deleteHotelRoomById(@PathVariable int id) {
        hotelRoomServices.deleteHotelRoomById(id);
    }

    @GetMapping("/all")
    public List<HotelRoom> getAllHotelRooms() {
        return hotelRoomServices.getAllHotelRooms();
    }

    @GetMapping("/get/{id}")
    public HotelRoom getHotelRoomById(@PathVariable int id) {
        return hotelRoomServices.getHotelRoomById(id);
    }



}
