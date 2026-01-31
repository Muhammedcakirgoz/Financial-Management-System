package com.ProjeDeneme.Project.repository;

import com.ProjeDeneme.Project.entity.Revenue;
import com.ProjeDeneme.Project.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface RevenueRepository extends MongoRepository<Revenue, String> {

    List<Revenue> findByUser(User user);

    Optional<Revenue> findByIdAndUser(String id, User user);

    List<Revenue> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
