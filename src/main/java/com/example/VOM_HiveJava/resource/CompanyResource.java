package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.entity.Company;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import com.example.VOM_HiveJava.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

