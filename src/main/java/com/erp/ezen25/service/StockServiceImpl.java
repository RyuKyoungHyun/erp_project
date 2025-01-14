package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Export;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    // 재고리스트 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<StockDTO> getListWithProduct() {
        return stockRepository.getImportDateWithImport();

    }

//    private StockDTO objectArrayToStockDTOWithProduct(Object[] objects) {
//        Product_Stock Stock = (Product_Stock) objects[0];
//        Hibernate.initialize(Stock.getProduct());
//        Product_Info product = Stock.getProduct();
//
//        String dateString = objects[1].toString();
//        try {
//            LocalDateTime importDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//            StockDTO dto = StockDTO.builder()
//                    .pNumId(Stock.getPNumId())
//                    .productId(product.getProductId())
//                    .productNum(Stock.getProductNum())
//                    .memberId(Stock.getMember().getMemberId())
//                    .totalPrice(Stock.getTotalPrice())
//                    .productName(product.getProductName())
//                    .mCategory(product.getMCategory())
//                    .sCategory(product.getSCategory())
//                    .originalPrice(String.valueOf(product.getOriginalPrice()))
//                    .sellPrice(String.valueOf(product.getSellPrice()))
//                    .image(product.getImage())
//                    .importDate(importDate)
//                    .build();
//
//            dto.setProduct(product);
//
//            return dto;
//        } catch (DateTimeParseException e) {
//            log.error("Error parsing date string: " + dateString, e);
//            return null;
//        }
//    }

    //withdrawal 페이지에서 exporting 페이지로 넘어갈 때, orderCode = '0' 에서 '1'로 변경
    @Override
    @Transactional
    public void updateOrderStatus(String orderCode, List<Long> productIds) {
        stockRepository.updateOrderStatus(orderCode, productIds);
    }

    @Override
    @Transactional
    public void updateOrderStatus2(String orderCode) {
        stockRepository.updateOrderStatus2(orderCode);
    }

    /*@Override
    @Transactional
    public void updateProductNum(String orderCode) {
        stockRepository.updateProductNum(orderCode);
    }*/

    @Override
    @Transactional(readOnly = true)
    public List<ExportDTO> getListForExportByOrderCodeAndProductIds(String orderCode) {
        return stockRepository.getListForExportByOrderCodeAndProductIds(orderCode);
    }
    @Override
    @Transactional(readOnly = true)
    public List<ExportCompleteDTO> getCompletedExportList() {
        return stockRepository.getCompletedExportList();
    }

}