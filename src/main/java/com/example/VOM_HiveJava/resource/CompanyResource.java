package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.CompanyRequest;
import com.example.VOM_HiveJava.dto.response.CompanyResponse;
import com.example.VOM_HiveJava.entity.Company;
import com.example.VOM_HiveJava.service.CompanyService;
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
@RequestMapping(value = "/company")
public class CompanyResource implements ResourceDTO<CompanyRequest, CompanyResponse>{

    @Autowired
    private CompanyService service;


    @GetMapping
    public ResponseEntity<Collection<CompanyResponse>> findAll(

            @RequestParam(name = "nmCompany", required = false) String nmCompany,
            @RequestParam(name = "cnpj", required = false) String cnpj,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "dtRegister", required = false) LocalDate dtRegister
            ) {

        var company = Company.builder()
                .nmCompany(nmCompany)
                .cnpj(cnpj).email(email)
                .dtRegister(dtRegister)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("nmCompany", match -> match.contains())
                .withIgnoreNullValues();

        Example<Company> example = Example.of(company, matcher);
        Collection<Company> all = service.findAll(example);
        if (Objects.isNull(all) || all.isEmpty()) return ResponseEntity.notFound().build();
        var response = all.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CompanyResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<CompanyResponse> save(@RequestBody @Valid CompanyRequest c) {
        var entity = service.toEntity(c);

        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entity.getIdCompany())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}

