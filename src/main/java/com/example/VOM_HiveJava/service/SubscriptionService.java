package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.SubscriptionRequest;
import com.example.VOM_HiveJava.dto.response.PayResponse;
import com.example.VOM_HiveJava.dto.response.SubscriptionResponse;
import com.example.VOM_HiveJava.entity.Pay;
import com.example.VOM_HiveJava.entity.Subscription;
import com.example.VOM_HiveJava.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class SubscriptionService implements ServiceDTO<Subscription, SubscriptionRequest, SubscriptionResponse> {

    @Autowired
    private SubscriptionRepository repo;
    @Autowired
    private PayService pagamentosService;
    @Autowired
    private CompanyService companyService;


    @Override
    public Subscription toEntity(SubscriptionRequest r) {

        var pagamentos = pagamentosService.findById(r.pagamentos().idHistory());

        var company = companyService.findById(r.company().idCompany());

        return Subscription.builder()
                .value(r.value())
                .status(r.status())
                .dtStart(r.dtStart())
                .dtEnd(r.dtEnd())
                .pagamentos((Set<Pay>) pagamentos)
                .company(company)
                .build();
    }

    @Override
    public SubscriptionResponse toResponse(Subscription e) {

        var company = companyService.toResponse(e.getCompany());

        List<PayResponse> pagamentos = null;
        if (Objects.nonNull(e.getPagamentos()) && !e.getPagamentos().isEmpty())
            pagamentos = e.getPagamentos().stream().map(pagamentosService::toResponse).toList();
        return SubscriptionResponse.builder()
                .idSubscription(e.getIdSubscription())
                .value(e.getValue())
                .status(e.getStatus())
                .dtStart(e.getDtStart())
                .dtEnd(e.getDtEnd())
                .pagamentos(pagamentos)
                .company(company)
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
