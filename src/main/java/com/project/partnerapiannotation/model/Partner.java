package com.project.partnerapiannotation.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.partnerapiannotation.util.GeoJsonMultiPolygonSerializer;
import com.project.partnerapiannotation.util.GeoJsonPointDeserializer;
import com.project.partnerapiannotation.util.GeoJsonPointSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import java.util.Objects;

@Document(collection = "partner")
public class Partner {

    @Id
    private String id;

    private String tradingName;

    private String ownerName;

    private String document;

    @JsonSerialize(using = GeoJsonMultiPolygonSerializer.class)
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonMultiPolygon coverageArea;

    @JsonSerialize(using = GeoJsonPointSerializer.class)
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    @JsonDeserialize(using = GeoJsonPointDeserializer.class)
    private GeoJsonPoint address;

    public Partner() {
    }

    public Partner(String id, String tradingName, String ownerName, String document, GeoJsonMultiPolygon coverageArea, GeoJsonPoint address) {
        this.id = id;
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public GeoJsonMultiPolygon getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(GeoJsonMultiPolygon coverageArea) {
        this.coverageArea = coverageArea;
    }

    public GeoJsonPoint getAddress() {
        return address;
    }

    public void setAddress(GeoJsonPoint address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partner partner = (Partner) o;
        return id.equals(partner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id='" + id + '\'' +
                ", tradingName='" + tradingName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", document='" + document + '\'' +
                ", coverageArea=" + coverageArea +
                ", address=" + address +
                '}';
    }
}
