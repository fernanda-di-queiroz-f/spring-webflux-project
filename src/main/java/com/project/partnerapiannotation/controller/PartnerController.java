package com.project.partnerapiannotation.controller;

import com.project.partnerapiannotation.model.Partner;
import com.project.partnerapiannotation.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import javax.xml.ws.Response;
import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService service;

    @GetMapping
    public Flux<Partner> getAllPartners() {
        return service.findAll();
    }

    // 1.2 Return a specific partner by its id with all the fields

    @GetMapping("{id}")
    public Mono<ResponseEntity<Partner>> getPartner(@PathVariable String id) {
        return service.findById(id)
                .map(partner -> ResponseEntity.ok(partner))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Add a partner

    @PostMapping
    public Mono<Partner> savePartner(@RequestBody Partner partner) {
        return service.insert(partner);
    }

    // 1.3 Search the nearest partner which the coverage area includes the location

    @GetMapping("search")
    public Mono<ResponseEntity<Partner>> searchPartner(@RequestParam(value = "long", defaultValue = "") double longitude,
                                                       @RequestParam(value = "lat", defaultValue = "") double latitude) {

        return service.searchPartner(longitude, latitude)
                .next()
                .map(partner -> ResponseEntity.ok(partner))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
