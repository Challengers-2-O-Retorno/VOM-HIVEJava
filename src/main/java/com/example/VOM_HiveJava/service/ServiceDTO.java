package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.entity.Profile;
import org.springframework.data.domain.Example;

import java.util.Collection;
import java.util.List;

public interface ServiceDTO<Entity, Request, Response> {

    Entity toEntity(Request r);

    Response toResponse(Entity e);

    Collection<Entity> findAll(Example<Entity> example);

    Entity findById(Long id);

    Entity save(Entity e);

}