package com.example.springbootjenkins;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by sujith on 09-08-2021
 */
public interface ParkViolationRepo extends JpaRepository<ParkingViolation, Long> {

    @Query("SELECT  p FROM ParkingViolation p")
    List<ParkingViolation> getOil(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM parking_violation")
    List<ParkingViolation> getByNative(Pageable pageable);

    List<ParkingViolation> findBy(Pageable pageable);

    @Query("SELECT p.id FROM ParkingViolation p WHERE p.plateId=?1")
    List<Long> findByPlateId(String blankplate);

    @Query("SELECT DISTINCT(p.plateId) FROM ParkingViolation p")
    List<String> findDistinct();
}
