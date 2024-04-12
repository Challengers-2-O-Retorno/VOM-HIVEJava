package com.example.VOM_HiveJava.resource;


import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/campaign")
public class CampaignResource {

    @Autowired
    private CampaignRepository repo;

    @GetMapping
    public List<Campaign> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Campaign findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

}
