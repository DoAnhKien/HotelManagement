package com.example.hotelmanagerment.repository;

import com.example.hotelmanagerment.model.FeedBack;
import com.example.hotelmanagerment.model.Report;
import com.example.hotelmanagerment.model.UserPokemon;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FeedBackRepository extends CrudRepository<FeedBack, Integer> {

    List<FeedBack> findFeedBackByUserId(int id);
}
