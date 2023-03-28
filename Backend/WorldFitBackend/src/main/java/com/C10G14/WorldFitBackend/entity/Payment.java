package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_ID")
    @Enumerated(EnumType.STRING)
    Long idUser;
    @Column(name = "AMOUNT")
    Double amount;
    @Column(name = "PAYMENT_DATE")
    String paymentDate;

    @PrePersist
    protected void onCreate() {
        this.paymentDate = ZonedDateTime.now(ZoneId.of("GMT-3")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
