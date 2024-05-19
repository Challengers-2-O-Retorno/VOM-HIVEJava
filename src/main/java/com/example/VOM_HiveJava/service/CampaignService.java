package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;

public class CampaignService implements ServiceDTO<Campaign, CampaignRequest, CampaignResponse> {

    @Autowired
    private CampaignRepository repo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;

    @Override
    public Campaign toEntity(CampaignRequest r) {

        var company = companyService.findById(r.company().id_company());

        var product = productService.findById(r.product().id_product());

        return Campaign.builder()
                .nm_campaign(r.nm_campaign())
                .target(r.target())
                .details(r.details())
                .status(r.status())
                .dt_register(r.dt_register())
                .company(company)
                .product(product)
                .build();
    }

    @Override
    public CampaignResponse toResponse(Campaign e) {

        var company = companyService.toResponse(e.getCompany());

        var product = productService.toResponse(e.getProduct());

        return CampaignResponse.builder()
                .id_campaign(e.getId_campaign())
                .nm_campaign(e.getNm_campaign())
                .target(e.getTarget())
                .details(e.getDetails())
                .status(e.getStatus())
                .dt_register(e.getDt_register())
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
