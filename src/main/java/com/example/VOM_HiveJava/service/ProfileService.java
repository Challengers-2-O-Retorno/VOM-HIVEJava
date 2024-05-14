package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.ProfileRequest;
import com.example.VOM_HiveJava.dto.response.ProfileResponse;
import com.example.VOM_HiveJava.entity.Profile;
import com.example.VOM_HiveJava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;

public class ProfileService implements ServiceDTO<Profile, ProfileRequest, ProfileResponse> {

    @Autowired
    private ProfileRepository repo;

    @Override
    public Profile toEntity(ProfileRequest r) {
        return Profile.builder()
                .nm_user(r.nm_user())
                .pass_user(r.pass_user())
                .permission(r.permission())
                .status(r.status())
                .dt_register(r.dt_register())
                .build();
    }

    @Override
    public ProfileResponse toResponse(Profile e) {
        return ProfileResponse.builder()
                .id_user(e.getId_user())
                .nm_user(e.getNm_user())
                .pass_user(e.getPass_user())
                .permission(e.getPermission())
                .status(e.getStatus())
                .dt_register(e.getDt_register())
                .build();
    }

    @Override
    public Collection<Profile> findAll(Example<Profile> example) {
        return repo.findAll( example );
    }

    @Override
    public Profile findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Profile save(Profile e) {
        return repo.save( e );
    }
}
