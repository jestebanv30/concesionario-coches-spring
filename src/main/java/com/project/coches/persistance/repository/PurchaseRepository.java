package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CarPurchaseResponseDto;
import com.project.coches.domain.dto.PurchaseBillResponseDto;
import com.project.coches.domain.dto.PurchaseRequestDto;
import com.project.coches.domain.dto.PurchaseResponseDto;
import com.project.coches.domain.repository.IPurchaseRepository;
import com.project.coches.persistance.crud.IPurchaseCrudRepository;
import com.project.coches.persistance.entity.PurchaseEntity;
import com.project.coches.persistance.mapper.IPurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PurchaseRepository implements IPurchaseRepository {

    private final IPurchaseCrudRepository iPurchaseCrudRepository;

    private final IPurchaseMapper iPurchaseMapper;

    @Override
    public List<PurchaseResponseDto> getAll() {

        List<PurchaseEntity> purchaseEntityList = iPurchaseCrudRepository.findAll();
        List<PurchaseResponseDto> listPurchaseResponseDto = new ArrayList<>();

        purchaseEntityList.forEach(purchaseEntity -> listPurchaseResponseDto.add(toPurchaseResponseDtoByEntity(purchaseEntity)));

        return listPurchaseResponseDto;
    }

    @Override
    public List<PurchaseResponseDto> getByCustomerCardId(String customerCardId) {

        List<PurchaseEntity> purchaseEntityList = iPurchaseCrudRepository.findAllByCardIdCustomer(customerCardId);
        List<PurchaseResponseDto> purchaseResponseDtoList = new ArrayList<>();

        purchaseEntityList.forEach(purchaseEntity-> purchaseResponseDtoList.add(toPurchaseResponseDtoByEntity(purchaseEntity)));

        return purchaseResponseDtoList;
    }

    @Override
    public PurchaseResponseDto getByNumberBill(Integer numberBill) {

        Optional<PurchaseEntity> searchNumberBill = iPurchaseCrudRepository.findById(numberBill);

        if (searchNumberBill.isEmpty()){
            return null;
        }

        return toPurchaseResponseDtoByEntity(searchNumberBill.get());
    }

    @Override
    public List<PurchaseResponseDto> getPurchaseByTotalLessThan(Double total) {
        return null;
    }

    @Override
    public List<PurchaseResponseDto> getPurchaseByTotalGreaterThan(Double total) {
        return null;
    }

    @Override
    public List<PurchaseResponseDto> getPurchaseBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public PurchaseBillResponseDto save(PurchaseRequestDto purchaseRequestDto) {

        PurchaseEntity purchaseEntity = iPurchaseMapper.toPurchaseEntity(purchaseRequestDto);

        purchaseEntity.getCarsPurchase().forEach(carPurchaseEntity -> carPurchaseEntity.setPurchaseEntity(purchaseEntity));

        PurchaseEntity purchaseEntitySave = iPurchaseCrudRepository.save(purchaseEntity);

        return new PurchaseBillResponseDto(purchaseEntitySave.getNumberBill());
    }

    public PurchaseResponseDto toPurchaseResponseDtoByEntity(PurchaseEntity purchaseEntity){

        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
        purchaseResponseDto.setNumberBill(purchaseEntity.getNumberBill());
        purchaseResponseDto.setCardIdCustomer(purchaseEntity.getCardIdCustomer());
        purchaseResponseDto.setDate(purchaseEntity.getDate());
        purchaseResponseDto.setTotal(purchaseEntity.getTotal());
        purchaseResponseDto.setPaymentMethod(purchaseEntity.getPaymentMethod());

        List<CarPurchaseResponseDto> carPurchaseResponseDtoList = new ArrayList<>();
        purchaseEntity.getCarsPurchase().forEach(carPurchaseEntity -> {
            String reference = carPurchaseEntity.getCarEntity().getReference();
            Integer quantity = carPurchaseEntity.getQuantity();
            Integer total = carPurchaseEntity.getTotal();
            carPurchaseResponseDtoList.add((new CarPurchaseResponseDto(reference, quantity, total)));
        });

        purchaseResponseDto.setCarsPurchase(carPurchaseResponseDtoList);

        return purchaseResponseDto;
    }

}
