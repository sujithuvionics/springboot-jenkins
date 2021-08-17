package com.example.springbootjenkins;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by sujith on 09-08-2021
 */
@Entity
@Getter
@Setter
@Table(name = "parking_violation")
public class ParkingViolation {
    @Id
    private Long id;

    @Column(name = "plate_id")
    private String plateId;

    @Column(name = "registration_date")
    private String registrationDate;

    @Column(name = "issue_date")
    private String issueDate;

}
