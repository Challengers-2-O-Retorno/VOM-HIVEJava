package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.entity.Subscription;
import com.example.VOM_HiveJava.repository.ProductRepository;
import com.example.VOM_HiveJava.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionResource {

    @Autowired
    private SubscriptionRepository repo;

    @GetMapping
    public List<Subscription> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Subscription findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

}