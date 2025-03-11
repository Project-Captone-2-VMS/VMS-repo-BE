package org.example.vmsproject.repository;

import org.example.vmsproject.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {
}
