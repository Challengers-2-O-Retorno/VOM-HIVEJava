package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.request.CompanyRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.dto.response.CompanyResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.entity.Company;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import com.example.VOM_HiveJava.repository.CompanyRepository;
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
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/company")
public class CompanyResource implements ResourceDTO<CompanyRequest, CompanyResponse>{

    @Autowired
    private CompanyService service;


    @GetMapping
    public ResponseEntity<Collection<CompanyResponse>> findAll(

            @RequestParam(name = "nm_company", required = false) String nm_company,
            @RequestParam(name = "cnpj", required = false) String cnpj,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "dt_register", required = false) LocalDate dt_register
            ) {

        var company = Company.builder()
                .nm_company(nm_company)
                .cnpj(cnpj).email(email)
                .dt_register(dt_register)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher("nm_company", match -> match.contains())
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
                .buildAndExpand(entity.getId_company())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}

