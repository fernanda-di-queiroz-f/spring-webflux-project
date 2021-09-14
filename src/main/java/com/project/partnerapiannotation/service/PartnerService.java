package com.project.partnerapiannotation.service;

import com.project.partnerapiannotation.model.Partner;
import com.project.partnerapiannotation.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;


@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    public Mono<Partner> insert(Partner partnerBody) {
        return repository.save(partnerBody);
    }

    public Mono<Partner> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Partner> searchPartner(double longitude, double latitude) {
        return repository.searchPartner(longitude, latitude);
    }

    public boolean validateDocument(String document) {
        document = document.replace("[^a-zA-Z0-9]", "");
        List<String> partners = findAll().map(p -> p.getDocument().replaceAll("[^a-zA-Z0-9]", ""))
                .collectList().block();

        if (partners.contains(document)) {
            return true;
        }
        return false;
    }

    public Flux<Partner> findAll() {
        return repository.findAll();
    }
}
