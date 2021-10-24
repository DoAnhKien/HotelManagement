package com.example.hotelmanagerment.service;

import com.example.hotelmanagerment.model.FeedBack;
import com.example.hotelmanagerment.model.HotelRoom;
import com.example.hotelmanagerment.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServices {

    @Autowired
    private FeedBackRepository feedBackRepository;


    public FeedBack insertOrUpdateFeedBack(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    public void deleteFeedBackById(int id) {
        feedBackRepository.deleteById(id);
    }


    public List<FeedBack> getAllFeedBack() {
        return (List<FeedBack>) feedBackRepository.findAll();
    }

    public List<FeedBack> getFeedBackByUserId(int id) {
        return feedBackRepository.findFeedBackByUserId(id);
    }

    public long countFeedBack() {
        return feedBackRepository.count();
    }
}
