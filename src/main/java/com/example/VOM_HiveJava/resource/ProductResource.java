package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.ProductRequest;
import com.example.VOM_HiveJava.dto.response.ProductResponse;
import com.example.VOM_HiveJava.entity.Category;
import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/product")
public class ProductResource implements ResourceDTO<ProductRequest, ProductResponse>{

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(
            @RequestParam(name = "nm_product") String nm_product,
            @RequestParam(name = "category") Category category
    ) {
        var product = Product.builder()
                .nm_product( nm_product )
                .category( category )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withMatcher( "nm_product", match -> match.contains())
                .withIgnoreNullValues();

        Example<Product> example = Example.of( product, matcher );
        Collection<Product> all = service.findAll( example );
        if (Objects.isNull( all ) || all.isEmpty()) return ResponseEntity.notFound().build();
        var response = all.stream().map( service::toResponse ).toList();
        return ResponseEntity.ok( response );

    }
    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        var entity = service.findById( id );
        if (Objects.isNull( entity )) return ResponseEntity.notFound().build();
        var response = service.toResponse( entity );
        return ResponseEntity.ok( response );
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest r) {
        var entity = service.toEntity( r );

        entity = service.save( entity );

        var response = service.toResponse( entity );

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( entity.getId_product() )
                .toUri();

        return ResponseEntity.created( uri ).body( response );
    }

}