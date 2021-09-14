package com.project.partnerapiannotation.repository;

import com.project.partnerapiannotation.model.Partner;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface PartnerRepository extends ReactiveMongoRepository<Partner, String> {


    @Query("{coverageArea: {\n" +
            "        $nearSphere: {\n" +
            "            $geometry: {\n" +
            "                type : \"Point\",\n" +
            "                coordinates : [ ?0, ?1 ]\n" +
            "            }, $maxDistance: 100000, $minDistance:1\n" +
            "        }\n" +
            "}}")
    Flux<Partner> searchPartner(double longitude, double latitude);

}
