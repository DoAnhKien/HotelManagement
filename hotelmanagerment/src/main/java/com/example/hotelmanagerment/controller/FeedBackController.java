package com.example.hotelmanagerment.controller;

import com.example.hotelmanagerment.model.FeedBack;
import com.example.hotelmanagerment.model.Report;
import com.example.hotelmanagerment.service.FeedBackServices;
import com.example.hotelmanagerment.service.ReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {

    @Autowired
    private FeedBackServices services;

    @GetMapping("/all")
    public List<FeedBack> getAllFeedBack() {
        return services.getAllFeedBack();
    }

    @PostMapping("/add")
    public FeedBack insertOrUpDateReport(@RequestBody FeedBack report) {
        return services.insertOrUpdateFeedBack(report);
    }

    @PostMapping("/delete/{id}")
    public void deleteReportById(@PathVariable int id) {
        services.deleteFeedBackById(id);
    }

    @PostMapping("/find/{userId}")
    public List<FeedBack> findReportByUserId(@PathVariable int userId) {
        return services.getFeedBackByUserId(userId);
    }
}
