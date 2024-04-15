package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.entity.Company;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import com.example.VOM_HiveJava.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyResource {

    @Autowired
    private CompanyRepository repo;


    @GetMapping
    public List<Company> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Company findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Transactional
    @PostMapping
    public Company save(@RequestBody Company c) {
        return repo.save( c );
    }

}

