package com.project.coches.domain.repository;

import com.project.coches.domain.dto.PurchaseBillResponseDto;
import com.project.coches.domain.dto.PurchaseRequestDto;
import com.project.coches.domain.dto.PurchaseResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de compras
 */
public interface IPurchaseRepository {

    List<PurchaseResponseDto> getAll();

    List<PurchaseResponseDto> getByCustomerCardId(String customerCardId);

    PurchaseResponseDto getByNumberBill(Integer numberBill);

    List<PurchaseResponseDto> getPurchaseByTotalLessThan(Double total);

    List<PurchaseResponseDto> getPurchaseByTotalGreaterThan(Double total);

    List<PurchaseResponseDto> getPurchaseBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    PurchaseBillResponseDto save(PurchaseRequestDto purchaseRequestDto);

}
