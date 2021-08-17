package com.example.springbootjenkins;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by sujith on 07-08-2021
 */
public interface OilRepo extends JpaRepository<Oil, Date> {

    @Query("SELECT o FROM Oil o")
    List<Oil> getOil();

    @Query(nativeQuery = true, value = "SELECT * FROM oil")
    List<Oil> getOilByNative();

    List<Oil> findBy(Pageable pageable);
}
