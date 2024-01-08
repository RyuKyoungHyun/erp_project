package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    @Column(length = 1000, nullable = false)
    private String contractType;

    @Column(nullable = false)
    private Long contractTotalMoney;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private LocalDateTime contractDate;

    @Column(nullable = false)
    private String contractDescription;



}