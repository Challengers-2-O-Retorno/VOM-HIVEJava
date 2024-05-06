package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Pay;
import com.example.VOM_HiveJava.repository.PayRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pay_hist")
public class PayResource {

    @Autowired
    private PayRepository repo;


    @GetMapping
    public List<Pay> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Pay findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

    @PostMapping
    public Pay save(@RequestBody Pay p) {
        return repo.save( p );
    }

}