package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.sql.Timestamp;

/**
 * Représente une entrée de cotation dans notre application.
 *
 * <p>Elle permet de stocker les informations relatives à une opération financière
 * comme les valeurs d'achat (bid) et de vente (ask) ainsi que les quantités et diverses données.
 * </p>
 *
 * <p>La classe est associé à la table {@code Bidlist} de la base de données.</p>
 *
 */
@Entity
@Table(name = "bidlist")
@Access(AccessType.FIELD)
public class BidList {

    /**
     * Identifiant unique de l'entrée de l'enchère
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private Integer bidListId;

    /**
     * Nom du compte associé à l'opération financière
     */
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", length = 30)
    private String account;

    /**
     * Type d'opération financière
     */
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", length = 30)
    private String type;

    /**
     * Quantité proposée à l'achat
     */
    @PositiveOrZero(message = "Bid quantity must be positive")
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    /**
     * Quantité proposée à la vente
     */
    @PositiveOrZero(message = "Ask quantity must be positive")
    @Column(name = "askQuantity")
    private Double askQuantity;

    /**
     * Valeur d'achat proposée
     */
    @PositiveOrZero(message = "Bid must be positive")
    @Column(name = "bid")
    private Double bid;

    /**
     * Valeur de vente proposée
     */
    @PositiveOrZero(message = "Ask must be positive")
    @Column(name = "ask")
    private Double ask;

    /**
     * Référence de benchmark utilisée pour l'opération financière
     */
    @Column(name = "benchmark", length = 125)
    private String benchmark;

    /**
     * Date de l'entrée en cotation
     */
    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    /**
     * Commentaire associé à l'entrée en cotation
     */
    @Column(name = "commentary", length = 125)
    private String commentary;

    /**
     * Instrument financier concerné
     */
    @Column(name = "security", length = 125)
    private String security;

    /**
     * Statut de l'opération financière
     */
    @Column(name = "status", length = 10)
    private String status;

    /**
     * Trader associé à l'opération financière
     */
    @Column(name = "trader", length = 125)
    private String trader;

    /**
     * Portefeuille associé à l'opération financière
     */
    @Column(name = "book", length = 125)
    private String book;

    /**
     * Nom associé à la création de l'entrée
     */
    @Column(name = "creationName", length = 125)
    private String creationName;

    /**
     * Date de création de l'entrée
     */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    /**
     * Nom associé à la révision de l'entrée
     */
    @Column(name = "revisionName", length = 125)
    private String revisionName;

    /**
     * Date de révision de l'entrée
     */
    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    /**
     * Nom associé à l'accord financier
     */
    @Column(name = "dealName", length = 125)
    private String dealName;

    /**
     * Type d'accord financier
     */
    @Column(name = "dealType", length = 125)
    private String dealType;

    /**
     * Identifiant source de la liste des opérations
     */
    @Column(name = "sourceListId", length = 125)
    private String sourceListId;

    /**
     * Sens de l'opération financière
     */
    @Column(name = "side", length = 125)
    private String side;

    public @NotBlank(message = "Account is mandatory") String getAccount() {
        return account;
    }

    public @PositiveOrZero(message = "Ask must be positive") Double getAsk() {
        return ask;
    }

    public @PositiveOrZero(message = "Ask quantity must be positive") Double getAskQuantity() {
        return askQuantity;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public @PositiveOrZero(message = "Bid must be positive") Double getBid() {
        return bid;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public Integer getBidListId() {
        return bidListId;
    }

    public @PositiveOrZero(message = "Bid quantity must be positive") Double getBidQuantity() {
        return bidQuantity;
    }

    public String getBook() {
        return book;
    }

    public String getCommentary() {
        return commentary;
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

    public String getSide() {
        return side;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public String getStatus() {
        return status;
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

    public void setAsk(@PositiveOrZero(message = "Ask must be positive") Double ask) {
        this.ask = ask;
    }

    public void setAskQuantity(@PositiveOrZero(message = "Ask quantity must be positive") Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setBid(@PositiveOrZero(message = "Bid must be positive") Double bid) {
        this.bid = bid;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
    }

    public void setBidQuantity(@PositiveOrZero(message = "Bid quantity must be positive") Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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

    public void setSide(String side) {
        this.side = side;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public void setType(@NotBlank(message = "Type is mandatory") String type) {
        this.type = type;
    }
}