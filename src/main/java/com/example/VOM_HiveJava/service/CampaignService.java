package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.CampaignRequest;
import com.example.VOM_HiveJava.dto.response.CampaignResponse;
import com.example.VOM_HiveJava.entity.Campaign;
import com.example.VOM_HiveJava.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;

public class CampaignService implements ServiceDTO<Campaign, CampaignRequest, CampaignResponse>{

    @Autowired
    private CampaignRepository repo;

    @Override
    public Campaign toEntity(CampaignRequest r) {
        return null;
    }

    @Override
    public CampaignResponse toResponse(Campaign e) {
        return null;
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
