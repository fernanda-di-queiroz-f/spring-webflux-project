package com.project.partnerapiannotation.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.IOException;

public class GeoJsonMultiPolygonSerializer extends JsonSerializer<GeoJsonMultiPolygon>{

    @Override
    public void serialize(GeoJsonMultiPolygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("type", value.getType());
        gen.writeArrayFieldStart("coordinates");

        for (GeoJsonPolygon pl : value.getCoordinates()) {
            for (GeoJsonLineString  ls : pl.getCoordinates()) {
                gen.writeStartArray();
                for (Point p : ls.getCoordinates()) {
                    gen.writeObject(new double[]{p.getX(), p.getY()});
                }
            }
            gen.writeEndArray();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}



