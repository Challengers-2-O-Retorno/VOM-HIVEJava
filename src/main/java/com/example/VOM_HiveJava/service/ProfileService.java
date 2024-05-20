package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.ProfileRequest;
import com.example.VOM_HiveJava.dto.response.ProfileResponse;
import com.example.VOM_HiveJava.entity.Profile;
import com.example.VOM_HiveJava.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProfileService implements ServiceDTO<Profile, ProfileRequest, ProfileResponse> {

    @Autowired
    private ProfileRepository repo;

    @Override
    public Profile toEntity(ProfileRequest r) {
        return Profile.builder()
                .nmUser(r.nmUser())
                .passUser(r.passUser())
                .permission(r.permission())
                .status(r.status())
                .dtRegister(r.dtRegister())
                .build();
    }

    @Override
    public ProfileResponse toResponse(Profile e) {
        return ProfileResponse.builder()
                .idUser(e.getIdUser())
                .nmUser(e.getNmUser())
                .passUser(e.getPassUser())
                .permission(e.getPermission())
                .status(e.getStatus())
                .dtRegister(e.getDtRegister())
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
