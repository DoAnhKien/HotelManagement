package com.example.hotelmanagerment.repository;

import com.example.hotelmanagerment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User save(User user);
    User findUserByUserEmail(String userEmail);


}
