package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.ProfileRequest;
import com.example.VOM_HiveJava.dto.response.ProfileResponse;
import com.example.VOM_HiveJava.entity.Profile;
import com.example.VOM_HiveJava.repository.ProfileRepository;
import com.example.VOM_HiveJava.service.ProfileService;
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
import java.util.Objects;

@RestController
@RequestMapping(value = "/profile")
public class ProfileResource implements ResourceDTO<ProfileRequest, ProfileResponse> {

    @Autowired
    private ProfileRepository repo;

    @Autowired
    private ProfileService service;

    @GetMapping
    public ResponseEntity<Collection<ProfileResponse>> findAll(

            @RequestParam(name = "nmUser", required = false) String nmUser,
            @RequestParam(name = "passUser", required = false) String passUser,
            @RequestParam(name = "permission", required = false) String permission,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "dtRegister", required = false) LocalDate dtRegister
    ) {
        var profile = Profile.builder()
                .nmUser(nmUser)
                .passUser(passUser)
                .permission(permission).status(status)
                .dtRegister(dtRegister)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("nmUser", match -> match.contains())
                .withIgnoreNullValues();

        Example<Profile> example = Example.of(profile, matcher);
        Collection<Profile> all = service.findAll(example);
        if (Objects.isNull(all) || all.isEmpty()) return ResponseEntity.notFound().build();
        var response = all.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProfileResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<ProfileResponse> save(@RequestBody @Valid ProfileRequest c) {
        var entity = service.toEntity(c);

        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entity.getIdUser())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}