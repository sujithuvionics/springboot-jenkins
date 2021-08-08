package com.example.springbootjenkins;

import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sujith on 07-08-2021
 */
@RestController
public class TestController {

    @Autowired
    private OilRepo oilRepo;

    @Autowired
    private JPAStreamer jpaStreamer;

    @GetMapping("jpa")
    public List<Oil> getJpa() {
        return oilRepo.findAll();
    }

    @GetMapping("stream")
    public List<Oil> getStream() {
        return jpaStreamer.stream(Oil.class).collect(Collectors.toList());
    }

    @GetMapping("jpa-query")
    public List<Oil> getJpaQuery() {
        String s = Oil$.testOne.columnName();
        System.out.println(s);
        return oilRepo.getOil();
    }
    @GetMapping("native")
    public List<Oil> getNativeQuery() {
        return oilRepo.getOilByNative();
    }
}
