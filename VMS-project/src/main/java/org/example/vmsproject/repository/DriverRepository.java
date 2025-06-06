package org.example.vmsproject.repository;

import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.dto.response.DriverResponse;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findAllByDriverId(Long id);
    boolean existsByLicenseNumber(String licenseNumber);
    boolean existsByPhoneNumber(String phoneNumber);


    @Query("SELECT d FROM Driver d WHERE d.isDeleted = false")
    List<Driver> findAllDeleted();

    @Query("select u.username from User u join Driver d on d.email = u.email where d.driverId = :driverId ")
    String findUserNameById(@Param("driverId")Long driverId);

    @Query("SELECT d FROM Driver d WHERE d.isDeleted = false and d.status = false ")
    List<Driver> findAllNoActive();

    @Query("select count(*) as total_drivers From Driver d where d.isDeleted = false ")
    int findTotalDrivers();

    @Query("select count(d) from Driver d where d.isDeleted = false and d.status = true ")
    int findTotalOnDeliverys();

    @Query("select count(d) from Driver d where d.isDeleted = false and d.status = false ")
    int findTotalAvailables();

    @Query("select count(d) from Driver d where d.isDeleted = false and d.workSchedule = 'Monday-Friday'")
    int findTotalWeeks();

    Driver findByUserUsername(String username);
    long countByStatus(Boolean status);
    @Query("SELECT r.driver.driverId, COUNT(r) FROM Route r GROUP BY r.driver.driverId")
    Map<String, Long> getDriverRouteCounts();

    Driver findByDriverId(Long id);

//    @Query("SELECT d.firstName, d.lastName, d.email, d.licenseNumber, d.phoneNumber " +
//            "FROM Driver d " +
//            "JOIN d.user u " +
//            "WHERE d.user.username = :username")
//    DriverDTO findByUserUsername(@Param("username") String username);

    Driver findAllByUserUsername(String username);

}
