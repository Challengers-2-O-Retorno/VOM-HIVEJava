package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.PayRequest;
import com.example.VOM_HiveJava.dto.response.PayResponse;
import com.example.VOM_HiveJava.entity.Pay;
import com.example.VOM_HiveJava.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PayService implements ServiceDTO<Pay, PayRequest, PayResponse> {

    @Autowired
    private PayRepository repo;

    @Override
    public Pay toEntity(PayRequest r) {
        return Pay.builder()
                .value(r.valor())
                .nef(r.nef())
                .method(r.method())
                .dtPayment(r.dtPayment())
                .dtDue(r.dtDue())
                .build();
    }

    @Override
    public PayResponse toResponse(Pay e) {
        return PayResponse.builder()
                .idHistory(e.getIdHistory())
                .value(e.getValue())
                .nef(e.getNef())
                .method(e.getMethod())
                .dtPayment(e.getDtPayment())
                .dtDue(e.getDtDue())
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
