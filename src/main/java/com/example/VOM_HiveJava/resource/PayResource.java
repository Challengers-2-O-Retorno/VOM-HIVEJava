package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.request.PayRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.dto.response.PayResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.entity.Pay;
import com.example.VOM_HiveJava.repository.PayRepository;
import com.example.VOM_HiveJava.service.PayService;
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
@RequestMapping(value = "/pay_hist")
public class PayResource implements ResourceDTO<PayRequest, PayResponse>{

    @Autowired
    private PayService service;


    @GetMapping
    public ResponseEntity<Collection<PayResponse>> findAll(

            @RequestParam(name = "value", required = false) double value,
            @RequestParam(name = "nef", required = false) byte[] nef,
            @RequestParam(name = "method", required = false) String method,
            @RequestParam(name = "dt_payment", required = false)LocalDate dt_payment,
            @RequestParam(name = "dt_due", required = false) LocalDate dt_due

            ) {

        var pay = Pay.builder()
                .value(value)
                .nef(nef)
                .method(method)
                .dt_payment(dt_payment)
                .dt_due(dt_due)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("nef", match -> match.contains())
                .withIgnoreNullValues();


        Example<Pay> example = Example.of(pay, matcher);
        Collection<Pay> all = service.findAll(example);
        if (Objects.isNull(all) || all.isEmpty()) return ResponseEntity.notFound().build();
        var response = all.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PayResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<PayResponse> save(@RequestBody @Valid PayRequest c) {
        var entity = service.toEntity(c);

        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entity.getId_history())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}