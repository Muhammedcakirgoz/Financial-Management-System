package com.ProjeDeneme.Project.repository;

import com.ProjeDeneme.Project.entity.Investment;
import com.ProjeDeneme.Project.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvestmentRepository extends MongoRepository<Investment, String> {

    List<Investment> findByUser(User user);

    Optional<Investment> findByIdAndUser(String id, User user);

    List<Investment> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
