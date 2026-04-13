package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id; //tinyint dans DB

    @NotBlank
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotBlank
    @Column(name = "orderNumber")
    private Integer orderNumber; //tinyint dans DB

    public Integer getId() {
        return id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public void setOrderNumber(Integer order) {
        this.orderNumber = orderNumber;
    }
}
