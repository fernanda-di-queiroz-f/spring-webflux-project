package com.project.partnerapiannotation;

import com.project.partnerapiannotation.model.Partner;
import com.project.partnerapiannotation.repository.PartnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

//https://start.spring.io/

@SpringBootApplication
public class PartnerApiAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartnerApiAnnotationApplication.class, args);
    }

    @Bean
    public GeoJsonModule geoJsonModule() {
        return new GeoJsonModule();
    }

    @Bean
    CommandLineRunner init(ReactiveMongoOperations operations, PartnerRepository repository) {
        return args -> {

            // Example points for object 1
            Point point1 = new Point(-46.70103, -23.61731);
            Point point2 = new Point(-46.72086, -23.63517);
            Point point3 = new Point(-46.7357, -23.62738);
            Point point4 = new Point(-46.74618, -23.60575);
            Point point5 = new Point(-46.7557, -23.60855);
            Point point6 = new Point(-46.76999, -23.5987);
            Point point7 = new Point(-46.7721, -23.58224);
            Point point8 = new Point(-46.76326, -23.57079);
            Point point9 = new Point(-46.73433, -23.54613);
            Point point10 = new Point(-46.70644, -23.56163);
            Point point11 = new Point(-46.70335, -23.56973);
            Point point12 = new Point(-46.69073, -23.58475);
            Point point13 = new Point(-46.70103, -23.61731);

            GeoJsonPolygon polygon1 = new GeoJsonPolygon(point1, point2, point3, point4, point5, point6, point7, point8, point9,
                    point10, point11, point12, point13);
            List<GeoJsonPolygon> polygons1 = new ArrayList<>();
            polygons1.add(polygon1);
            GeoJsonMultiPolygon multiPolygon1 = new GeoJsonMultiPolygon(polygons1);
            GeoJsonPoint pointA = new GeoJsonPoint(-46.720875, -23.584986);

            // Example points for object 2
            Point point01 = new Point(-67.83039, -9.95782);
            Point point02 = new Point(-67.83176, -9.98487);
            Point point03 = new Point(-67.78627, -9.98825);
            Point point04 = new Point(-67.78885, -9.95105);
            Point point05 = new Point(-67.83039, -9.95782);

            GeoJsonPolygon polygon2 = new GeoJsonPolygon(point01, point02, point03, point04, point05);
            List<GeoJsonPolygon> polygons2 = new ArrayList<>();
            polygons2.add(polygon2);
            GeoJsonMultiPolygon multiPolygon2 = new GeoJsonMultiPolygon(polygons2);
            GeoJsonPoint pointB = new GeoJsonPoint(-67.81702, -9.970223);

            // Example points for object 3
            Point point001 = new Point(-43.36556, -22.99669);
            Point point002 = new Point(-43.36539, -23.01928);
            Point point003 = new Point(-43.26583, -23.01802);
            Point point004 = new Point(-43.25724, -23.00649);
            Point point005 = new Point(-43.23355, -23.00127);
            Point point006 = new Point(-43.2381, -22.99716);
            Point point007 = new Point(-43.23866, -22.99649);
            Point point008 = new Point(-43.24063, -22.99756);
            Point point009 = new Point(-43.24634, -22.99736);
            Point point0010 = new Point(-43.24677, -22.99606);
            Point point0011 = new Point(-43.24067, -22.99381);
            Point point0012 = new Point(-43.24886, -22.99121);
            Point point0013 = new Point(-43.25617, -22.99456);
            Point point0014 = new Point(-43.25625, -22.99203);
            Point point0015 = new Point(-43.25346, -22.99065);
            Point point0016 = new Point(-43.29599, -22.98283);
            Point point0017 = new Point(-43.3262, -22.96481);
            Point point0018 = new Point(-43.33427, -22.96402);
            Point point0019 = new Point(-43.33616, -22.96829);
            Point point0020 = new Point(-43.342, -22.98157);
            Point point0021 = new Point(-43.34817, -22.97967);
            Point point0022 = new Point(-43.35142, -22.98062);
            Point point0023 = new Point(-43.3573, -22.98084);
            Point point0024 = new Point(-43.36522, -22.98032);
            Point point0025 = new Point(-43.36696, -22.98422);
            Point point0026 = new Point(-43.36717, -22.98855);
            Point point0027 = new Point(-43.36636, -22.99351);
            Point point0028 = new Point(-43.36556, -22.99669);

            GeoJsonPolygon polygon3 = new GeoJsonPolygon(point001, point002, point003, point004, point005, point006, point007, point008,
                    point009, point0010, point0011, point0012, point0013, point0014, point0015, point0016, point0017,
                    point0018, point0019, point0020, point0021, point0022, point0023, point0024, point0025, point0026,
                    point0027, point0028);
            List<GeoJsonPolygon> polygons3 = new ArrayList<>();
            polygons3.add(polygon3);
            GeoJsonMultiPolygon multiPolygon3 = new GeoJsonMultiPolygon(polygons3);
            GeoJsonPoint pointC = new GeoJsonPoint(-43.297337, -23.013538);

            // Memory Example objects
            Flux<Partner> partnerFlux = Flux.just(
                    new Partner(null, "Adega Osasco", "Ze da Ambev", "02.453.716/000170", multiPolygon1, pointA),
                    new Partner(null, "Behoppy", "Daniboy", "04.433.714/0001-44", multiPolygon2, pointB),
                    new Partner(null, "SOS Cerveja", "Daniel Henrique", "04666182390", multiPolygon3, pointC))
                    .flatMap(repository::save);

            partnerFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);

            /*
            operations.collectionExists(Partner.class)
                    .flatMap(exists -> exists ? operations.dropCollection(Partner.class) : Mono.just(exists))
                    .thenMany(v -> operations.createCollection(Partner.class))
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println); */
        };
    }

}
