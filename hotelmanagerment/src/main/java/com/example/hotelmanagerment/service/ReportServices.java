package com.example.hotelmanagerment.service;

import com.example.hotelmanagerment.model.FeedBack;
import com.example.hotelmanagerment.model.Report;
import com.example.hotelmanagerment.repository.FeedBackRepository;
import com.example.hotelmanagerment.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServices {

    @Autowired
    private ReportRepository reportRepository;


    public Report insertOrUpdateFeedBack(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReportById(int id) {
        reportRepository.deleteById(id);
    }


    public List<Report> getAllReport() {
        return (List<Report>) reportRepository.findAll();
    }

    public Report getReportByUserId(int id) {
        return reportRepository.findReportByUserId(id);
    }

    public long countFeedBack() {
        return reportRepository.count();
    }
}
