package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.request.SubscriptionRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.dto.response.SubscriptionResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.entity.Subscription;
import com.example.VOM_HiveJava.repository.ProductRepository;
import com.example.VOM_HiveJava.repository.SubscriptionRepository;
import com.example.VOM_HiveJava.service.SubscriptionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionResource implements ResourceDTO<SubscriptionRequest, SubscriptionResponse>{

    @Autowired
    private SubscriptionRepository repo;

    @Autowired
    private SubscriptionService service;

    @GetMapping
    public ResponseEntity<Collection<SubscriptionResponse>> findAll(

            @RequestParam(name = "id_subscription", required = false) Long id_subscription,
            @RequestParam(name = "value", required = false) double value,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "dt_start", required = false) LocalDate dt_start,
            @RequestParam(name = "dt_end", required = false) LocalDate dt_end
    ) {

        var subscription = Subscription.builder()
                .id_subscription(id_subscription)
                .value(value)
                .status(status)
                .dt_start(dt_start)
                .dt_end(dt_end)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("id_subscription", match -> match.contains())
                .withIgnoreNullValues();

        Example<Subscription> example = Example.of(subscription, matcher);
        Collection<Subscription> all = service.findAll(example);
        if (Objects.isNull(all) || all.isEmpty()) return ResponseEntity.notFound().build();
        var response = all.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<SubscriptionResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<SubscriptionResponse> save(@RequestBody @Valid SubscriptionRequest c) {
        var entity = service.toEntity(c);

        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entity.getId_subscription())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}