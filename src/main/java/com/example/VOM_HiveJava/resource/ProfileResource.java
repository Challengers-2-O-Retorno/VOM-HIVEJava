package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.entity.Profile;
import com.example.VOM_HiveJava.repository.ProductRepository;
import com.example.VOM_HiveJava.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profile")
public class ProfileResource {

    @Autowired
    private ProfileRepository repo;


    @GetMapping
    public List<Profile> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Profile findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

    @PostMapping
    public Profile save(@RequestBody Profile p) {
        return repo.save( p );
    }

}