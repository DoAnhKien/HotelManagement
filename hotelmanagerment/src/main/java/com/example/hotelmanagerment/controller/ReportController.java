package com.example.hotelmanagerment.controller;

import com.example.hotelmanagerment.model.Report;
import com.example.hotelmanagerment.model.User;
import com.example.hotelmanagerment.service.ReportServices;
import com.example.hotelmanagerment.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServices services;

    @GetMapping("/all")
    public List<Report> getAllUser() {
        return services.getAllReport();
    }

    @PostMapping("/add")
    public Report insertOrUpDateReport(@RequestBody Report report) {
        return services.insertOrUpdateFeedBack(report);
    }

    @PostMapping("/delete/{id}")
    public void deleteReportById(@PathVariable int id) {
        services.deleteReportById(id);
    }

    @PostMapping("/find/{userId}")
    public Report findReportByUserId(@PathVariable int userId) {
        return services.getReportByUserId(userId);
    }

}
