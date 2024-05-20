package com.example.VOM_HiveJava.resource;


import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.service.CampaignService;
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
@RequestMapping(value = "/campaign")
public class CampaignResource implements ResourceDTO<CampaignRequest, CampaignResponse> {

    @Autowired
    private CampaignService service;

    @GetMapping
    public ResponseEntity<Collection<CampaignResponse>> findAll(

            @RequestParam(name = "nm_campaign", required = false) String nm_campaign,
            @RequestParam(name = "target", required = false) String target,
            @RequestParam(name = "details", required = false) byte[] details,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "dt_register", required = false) LocalDate dt_register
    ) {
        var campaign = Campaign.builder()
                .nm_campaign(nm_campaign)
                .target(target)
                .details(details)
                .status(status)
                .dt_register(dt_register)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("nm_campaign", match -> match.contains())
                .withIgnoreNullValues();

        Example<Campaign> example = Example.of(campaign, matcher);
        Collection<Campaign> all = service.findAll(example);
        if (Objects.isNull(all) || all.isEmpty()) return ResponseEntity.notFound().build();
        var response = all.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CampaignResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<CampaignResponse> save(@RequestBody @Valid CampaignRequest c) {
        var entity = service.toEntity(c);

        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entity.getId_campaign())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
