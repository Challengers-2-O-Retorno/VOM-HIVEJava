package com.example.VOM_HiveJava.service;

import org.springframework.data.domain.Example;

import java.util.Collection;

public interface ServiceDTO<Entity, Request, Response> {

    Entity toEntity(Request r);

    Response toResponse(Entity e);

    Collection<Entity> findAll(Example<Entity> example);

    Entity findById(Long id);

    Entity save(Entity e);

}