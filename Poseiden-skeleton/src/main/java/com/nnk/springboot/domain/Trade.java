package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

/**
 * Représente une opération financière un "trade" dans notre application.
 *
 * <p>Cette entité permet de stocker les différentes informations associées à une transaction financière
 * comme les prix, les quantités et diverses informations.</p>
 *
 * <p>La classe est associée à la table {@code trade} dans la base de données</p>
 */
@Entity
@Table(name = "trade")
@Access(AccessType.FIELD)
public class Trade {

    /**
     * Identifiant unique de l'opération
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TradeId")
    private Integer tradeId;

    /**
     * Compte associé à la transaction financière
     */
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", length = 30)
    private String account;

    /**
     * Type d'opération réalisée
     */
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", length = 30)
    private String type;

    /**
     * Quantités achetées lors de la transaction
     */
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    /**
     * Quantités vendues lors de la transaction
     */
    @Column(name = "sellQuantity")
    private Double sellQuantity;

    /**
     * Prix d'achat lors de la transaction
     */
    @Column(name = "buyPrice")
    private Double buyPrice;

    /**
     * Prix de vente lors de la transaction
     */
    @Column(name = "sellPrice")
    private Double sellPrice;

    /**
     * Date de réalisation de la transaction
     */
    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    /**
     * Instrument financier utilisé pour la réalisation de l'opération
     */
    @Column(name = "security", length = 125)
    private String security;

    /**
     * Statut actuel de l'opération financière
     */
    @Column(name = "status", length = 10)
    private String status;

    /**
     * Nom du trader associé à la transaction
     */
    @Column(name = "trader", length = 125)
    private String trader;

    /**
     * Référence du benchmark utilisé lors de la transaction
     */
    @Column(name = "benchmark", length = 125)
    private String benchmark;

    /**
     * Portefeuille associé à la transaction
     */
    @Column(name = "book", length = 125)
    private String book;

    /**
     * Nom associé à la transaction financière
     */
    @Column(name = "creationName", length = 125)
    private String creationName;

    /**
     * Date de création associé à la transaction
     */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    /**
     * Nom de la dernière modification associé à la transaction
     */
    @Column(name = "revisionName", length = 125)
    private String revisionName;

    /**
     * Date de la dernière révision associée à la transaction
     */
    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    /**
     * Nom associé à l'accord de la transaction financière
     */
    @Column(name = "dealName", length = 125)
    private String dealName;

    /**
     * Type d'accord associé à la transaction financière
     */
    @Column(name = "dealType", length = 125)
    private String dealType;

    /**
     * Identifiant provenant d'une source externe
     */
    @Column(name = "sourceListId", length = 125)
    private String sourceListId;

    /**
     * Sens de l'opération
     */
    @Column(name = "side", length = 125)
    private String side;

    public @NotBlank(message = "Account is mandatory") String getAccount() {
        return account;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public String getBook() {
        return book;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getCreationName() {
        return creationName;
    }

    public String getDealName() {
        return dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public String getSecurity() {
        return security;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public String getSide() {
        return side;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public String getTrader() {
        return trader;
    }

    public @NotBlank(message = "Type is mandatory") String getType() {
        return type;
    }

    public void setAccount(@NotBlank(message = "Account is mandatory") String account) {
        this.account = account;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public void setType(@NotBlank(message = "Type is mandatory") String type) {
        this.type = type;
    }
}