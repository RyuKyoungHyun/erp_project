package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandId")
    private Long brandId;

    @Column(length = 1000, nullable = false, unique = true)
    private String brandName;

    @Column(length = 1000)
    private String brandPhone;

    @Column(length = 1000)
    private String brandEmail;

    @Column(length = 1000)
    private String brandDescription;

    public void changeBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void changeBrandPhone(String brandPhone) {
        this.brandPhone = brandPhone;
    }

    public void changeBrandEmail(String brandEmail) {
        this.brandEmail = brandEmail;
    }

    public void changeBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public Brand setBrandId(long brandId) {
        this.brandId = brandId;
        return this;
    }
}
