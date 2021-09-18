package com.example.hotelmanagerment.repository;

import com.example.hotelmanagerment.model.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    Report findReportByUserId(int userId);
}
