package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "ordering")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long orderNum;

    @Column(length = 1000)
    private String orderDescription;

    @Column(nullable = false)
    private LocalDateTime orderOutDate;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String orderStatus;

    @Column(length = 1000, nullable = false, unique = true)
    @ColumnDefault("'0'")
    private String orderCode;
}
