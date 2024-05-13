package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.PayRequest;
import com.example.VOM_HiveJava.entity.Pay;
import com.example.VOM_HiveJava.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;

public class PayService implements ServiceDTO<Pay, PayRequest, Pay> {

    @Autowired
    private PayRepository repo;

    @Override
    public Pay toEntity(PayRequest r) {
        return Pay.builder().value(r.valor())
                .nef(r.nef())
                .method(r.method())
                .dt_payment(r.dt_payment())
                .dt_due(r.dt_due())
                .build();
    }

    @Override
    public Pay toResponse(Pay e) {
        return Pay.builder()
                .id_history(e.getId_history())
                .value(e.getValue())
                .nef(e.getNef())
                .method(e.getMethod())
                .dt_payment(e.getDt_payment())
                .dt_due(e.getDt_due())
                .build();
    }

    @Override
    public Collection<Pay> findAll(Example<Pay> example) {
        return repo.findAll( example );
    }

    @Override
    public Pay findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Pay save(Pay e) {
        return repo.save( e );
    }
}
