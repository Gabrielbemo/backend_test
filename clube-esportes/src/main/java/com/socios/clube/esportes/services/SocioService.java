package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.repositories.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioService {

    private SocioRepository socioRepository;

    @Autowired
    public SocioService(final SocioRepository socioRepository){
        this.socioRepository = socioRepository;
    }

    public Socio create(final Socio socio){
        socioRepository.save(socio);
        return socioRepository.getById(socio.getId());
    }
}
