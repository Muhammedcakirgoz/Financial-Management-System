package com.ProjeDeneme.Project.repository;

import com.ProjeDeneme.Project.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ProjeDeneme.Project.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByUser(User user);
    Optional<Expense> findByIdAndUser(String id, User user);
    List<Expense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
