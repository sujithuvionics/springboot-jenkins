package com.example.springbootjenkins;

import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ParkViolationRepo parkViolationRepo;

    @Autowired
    private OilRepo oilRepo;

    @Autowired
    private JPAStreamer jpaStreamer;

    @GetMapping("jpa")
    public List<String> getJpa() {
//        Pageable pageable = create();
//        return parkViolationRepo.findBy(pageable);
//        return parkViolationRepo.findByPlateId("BLANKPLATE");
        return parkViolationRepo.findDistinct();
    }

    @GetMapping("stream")
    public List<String> getStream() {
//        Pageable pageable = create();
//        return jpaStreamer.stream(ParkingViolation.class).filter(d -> d.getPlateId().equals("BLANKPLATE"))
//                .map(ParkingViolation::getId).collect(Collectors.toList());
//        System.out.println(blankplate.size());
//        return jpaStreamer.stream(ParkingViolation.class).limit(50000).collect(Collectors.toList());
//        return jpaStreamer.stream(Oil.class).limit(5000).collect(Collectors.toList());
        return jpaStreamer.stream(ParkingViolation.class).distinct().map(ParkingViolation::getPlateId).collect(Collectors.toList());
    }

    @GetMapping("jpa-query")
    public List<ParkingViolation> getJpaQuery() {
        Pageable pageable = create();
        return parkViolationRepo.getOil(pageable);
    }

    @GetMapping("native")
    public List<ParkingViolation> getNativeQuery() {
        Pageable pageable = create();
        return parkViolationRepo.getByNative(pageable);
    }

    private Pageable create() {
        return PageRequest.of(0, 5000);
    }
}
