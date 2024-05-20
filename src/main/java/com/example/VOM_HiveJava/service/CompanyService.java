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

        var profiles = profileService.findById( r.profiles().idUser());

        return Company.builder()
                .nmCompany(r.nmCompany())
                .cnpj(r.cnpj())
                .email(r.email())
                .dtRegister(r.dtRegister())
                .profiles((Set<Profile>) profiles)
                .build();
    }

    @Override
    public CompanyResponse toResponse(Company e) {

        Collection<ProfileResponse> profiles = null;
        if (Objects.nonNull(e.getProfiles()) && !e.getProfiles().isEmpty())
            profiles = e.getProfiles().stream().map(profileService::toResponse).toList();

        return CompanyResponse.builder()
                .idCompany(e.getIdCompany())
                .nmCompany(e.getNmCompany())
                .cnpj(e.getCnpj())
                .email(e.getEmail())
                .dtRegister(e.getDtRegister())
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
