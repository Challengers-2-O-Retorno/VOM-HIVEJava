package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Pay_hist;
import com.example.VOM_HiveJava.repository.Pay_histRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pay_hist")
public class Pay_histResource {

    @Autowired
    private Pay_histRepository repo;


    @GetMapping
    public List<Pay_hist> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Pay_hist findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

}