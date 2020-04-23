package com.hobbymatcher.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Event {

    private Integer id;
    @NonNull
    private Integer hobbyId;
    private Date onDatetime;
    @NonNull
    private String location;
    private String locationShort;
    private Double geoLat;
    private Double geoLon;
    @NotBlank
    private Integer capacity;
    @NotBlank
    private String description;
    private Boolean plus18Only;
    private Double fee;
    private String organizer;
    @NotBlank
    private String title;
    // @NotNull
    private String photoId;
    // @NonNull
    private Integer createdById;

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getOnDatetime() {
        return onDatetime;
    }

    public void setOnDatetime(Date onDatetime) {
        this.onDatetime = onDatetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationShort() {
        return locationShort;
    }

    public void setLocationShort(String locationShort) {
        this.locationShort = locationShort;
    }

    public Double getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(Double geoLat) {
        this.geoLat = geoLat;
    }

    public Double getGeoLon() {
        return geoLon;
    }

    public void setGeoLon(Double geoLon) {
        this.geoLon = geoLon;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPlus18Only() {
        return plus18Only;
    }

    public void setPlus18Only(Boolean plus18Only) {
        this.plus18Only = plus18Only;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }
}
