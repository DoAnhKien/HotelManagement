package com.example.hotelmanagerment.repository;

import com.example.hotelmanagerment.model.FeedBack;
import com.example.hotelmanagerment.model.Report;
import com.example.hotelmanagerment.model.UserPokemon;
import org.springframework.data.repository.CrudRepository;

public interface FeedBackRepository extends CrudRepository<FeedBack, Integer> {

    FeedBack findFeedBackByUserId(int id);
}
