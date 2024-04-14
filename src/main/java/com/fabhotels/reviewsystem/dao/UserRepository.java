package com.fabhotels.reviewsystem.dao;

import com.fabhotels.reviewsystem.enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
