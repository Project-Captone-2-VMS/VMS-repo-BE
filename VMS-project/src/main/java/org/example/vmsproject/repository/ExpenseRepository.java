package org.example.vmsproject.repository;

import org.example.vmsproject.entity.VehicleExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<VehicleExpense, Long> {
}
