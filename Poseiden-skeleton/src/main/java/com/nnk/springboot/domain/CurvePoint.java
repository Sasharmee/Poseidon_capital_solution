package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id; //tinyint dans DB

    @Column(name = "CurveId")
    private Integer curveId; //tinyint dans DB

    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @Column(name = "term")
    private Double term;

    @Column(name = "value")
    private Double value;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    public @NotNull Integer getId() {
        return id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public Double getValue() {
        return value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
