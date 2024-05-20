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

            @RequestParam(name = "id_user", required = false) Long id_user,
            @RequestParam(name = "nm_user", required = false) String nm_user,
            @RequestParam(name = "pass_user", required = false) String pass_user,
            @RequestParam(name = "permission", required = false) String permission,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "dt_register", required = false) LocalDate dt_register
    ) {
        var profile = Profile.builder()
                .id_user(id_user)
                .nm_user(nm_user)
                .pass_user(pass_user)
                .permission(permission).status(status)
                .dt_register(dt_register)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("nm_user", match -> match.contains())
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
                .buildAndExpand(entity.getId_user())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}