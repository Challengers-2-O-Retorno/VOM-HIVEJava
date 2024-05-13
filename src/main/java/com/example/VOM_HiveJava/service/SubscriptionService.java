package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.SubscriptionRequest;
import com.example.VOM_HiveJava.entity.Subscription;
import com.example.VOM_HiveJava.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;

public class SubscriptionService implements ServiceDTO<Subscription, SubscriptionRequest, Subscription> {

    @Autowired
    private SubscriptionRepository repo;

    @Override
    public Subscription toEntity(SubscriptionRequest r) {
        return Subscription.builder()
                .value(r.value())
                .status(r.status())
                .dt_start(r.dt_start())
                .dt_end(r.dt_end())
                .pagamentos()
                .company()
                .build();
    }

    @Override
    public Subscription toResponse(Subscription e) {
        return Subscription.builder()
                .id_subscription(e.getId_subscription())
                .value(e.getValue())
                .status(e.getStatus())
                .dt_start(e.getDt_start())
                .dt_end(e.getDt_end())
                .pagamentos(e.getPagamentos())
                .company(e.getCompany())
                .build();
    }

    @Override
    public Collection<Subscription> findAll(Example<Subscription> example) {
        return repo.findAll( example );
    }

    @Override
    public Subscription findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Subscription save(Subscription e) {
        return repo.save( e );
    }
}
