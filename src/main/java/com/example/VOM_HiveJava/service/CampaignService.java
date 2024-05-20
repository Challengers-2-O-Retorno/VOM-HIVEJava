package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CampaignService implements ServiceDTO<Campaign, CampaignRequest, CampaignResponse> {

    @Autowired
    private CampaignRepository repo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;

    @Override
    public Campaign toEntity(CampaignRequest r) {

        var company = companyService.findById(r.company().idCompany());

        var product = productService.findById(r.product().idProduct());

        return Campaign.builder()
                .nmCampaign(r.nmCampaign())
                .target(r.target())
                .details(r.details())
                .status(r.status())
                .dtRegister(r.dtRegister())
                .company(company)
                .product(product)
                .build();
    }

    @Override
    public CampaignResponse toResponse(Campaign e) {

        var company = companyService.toResponse(e.getCompany());

        var product = productService.toResponse(e.getProduct());

        return CampaignResponse.builder()
                .idCampaign(e.getIdCampaign())
                .nmCampaign(e.getNmCampaign())
                .target(e.getTarget())
                .details(e.getDetails())
                .status(e.getStatus())
                .dtRegister(e.getDtRegister())
                .company(company)
                .product(product)
                .build();
    }

    @Override
    public Collection<Campaign> findAll(Example<Campaign> example) {
        return repo.findAll( example );
    }

    @Override
    public Campaign findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Campaign save(Campaign e) {
        return repo.save( e );
    }
}
