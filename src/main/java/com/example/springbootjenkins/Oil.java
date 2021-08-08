package com.example.springbootjenkins;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by sujith on 07-08-2021
 */
@Entity
@Getter
@Setter
public class Oil {

    @Id
    private Date date;

    private float price;

    @Column(name = "test_one")
    private String testOne;
}
