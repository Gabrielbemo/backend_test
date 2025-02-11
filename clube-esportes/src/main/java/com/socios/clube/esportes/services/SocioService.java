package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.repositories.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    private final SocioRepository socioRepository;

    @Autowired
    public SocioService(final SocioRepository socioRepository){
        this.socioRepository = socioRepository;
    }

    public Socio create(final Socio socio){
        socioRepository.save(socio);
        return socioRepository.getById(socio.getId());
    }

    public void update(final Socio socio){
        Socio socioBeforeUpdate = socioRepository.getById(socio.getId());
        socioBeforeUpdate.setName(socio.getName());
        socioBeforeUpdate.setLastName(socio.getLastName());
        socioBeforeUpdate.setEmail(socio.getEmail());
        socioBeforeUpdate.setAddress(socio.getAddress());
        socioBeforeUpdate.setBirthDate(socio.getBirthDate());
        socioBeforeUpdate.setPhone(socio.getPhone());

        socioRepository.save(socioBeforeUpdate);
    }

    public Socio getById(Long id){
        return socioRepository.getById(id);
    }

    public List<Socio> list(){
        return socioRepository.findAll();
    }

    public void deleteById(final Long id){
        socioRepository.deleteById(id);
    }
}
