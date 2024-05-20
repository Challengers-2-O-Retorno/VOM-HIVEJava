package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.CompanyRequest;
import com.example.VOM_HiveJava.dto.response.CompanyResponse;
import com.example.VOM_HiveJava.dto.response.ProfileResponse;
import com.example.VOM_HiveJava.entity.Company;
import com.example.VOM_HiveJava.entity.Profile;
import com.example.VOM_HiveJava.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CompanyService implements ServiceDTO<Company, CompanyRequest, CompanyResponse> {

    @Autowired
    private CompanyRepository repo;

    @Autowired
    private ProfileService profileService;


    @Override
    public Company toEntity(CompanyRequest r) {

        var profiles = profileService.findById(r.profiles().id_user());

        return Company.builder()
                .nm_company(r.nm_company())
                .cnpj(r.cnpj())
                .email(r.email())
                .dt_register(r.dt_register())
                .profiles((Set<Profile>) profiles)
                .build();
    }

    @Override
    public CompanyResponse toResponse(Company e) {

        List<ProfileResponse> profiles = null;
        if (Objects.nonNull(e.getProfiles()) && !e.getProfiles().isEmpty())
            profiles = e.getProfiles().stream().map(profileService::toResponse).toList();

        return CompanyResponse.builder()
                .id_company(e.getId_company())
                .nm_company(e.getNm_company())
                .cnpj(e.getCnpj())
                .email(e.getEmail())
                .dt_register(e.getDt_register())
                .profiles(profiles)
                .build();
    }

    @Override
    public Collection<Company> findAll(Example<Company> example) {
        return repo.findAll( example );
    }

    @Override
    public Company findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Company save(Company e) {
        return repo.save( e );
    }
}
