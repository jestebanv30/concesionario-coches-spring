package com.project.coches.domain.service;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.dto.PurchaseBillResponseDto;
import com.project.coches.domain.dto.PurchaseRequestDto;
import com.project.coches.domain.dto.PurchaseResponseDto;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.domain.repository.IPurchaseRepository;
import com.project.coches.domain.useCase.IPurchaseUseCase;
import com.project.coches.exception.typesexceptions.PurchaseNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService implements IPurchaseUseCase {

    private final IPurchaseRepository iPurchaseRepository;

    private final ICarRepository iCarRepository;

    @Override
    public List<PurchaseResponseDto> getAll() {
        return iPurchaseRepository.getAll();
    }

    @Override
    public List<PurchaseResponseDto> getByIdCustomer(String idCustomer) {
        return iPurchaseRepository.getByCustomerCardId(idCustomer);
    }

    @Override
    public PurchaseResponseDto getByNumberBill(Integer numberBill) {

        PurchaseResponseDto purchaseResponseDto = iPurchaseRepository.getByNumberBill(numberBill);

        if (purchaseResponseDto == null) {
            throw new PurchaseNotExistException();
        }

        return purchaseResponseDto;
    }

    /*
    @Override
    public PurchaseBillResponseDto save(PurchaseRequestDto purchaseRequestDto) {

        PurchaseBillResponseDto purchaseBillResponseDto = iPurchaseRepository.save(purchaseRequestDto);

        purchaseRequestDto.getCarsPurchase().forEach(carPurchase -> {
            CarDto carActual = iCarRepository.getCarById(carPurchase.getCodeCar()).get();
            carActual.setStock(carActual.getStock() - carPurchase.getQuantity());

            iCarRepository.save(carActual);
        });

        return purchaseBillResponseDto;
    }
     */

    @Override
    public PurchaseBillResponseDto save(PurchaseRequestDto purchaseRequestDto) {

        PurchaseBillResponseDto purchaseBillResponseDto = iPurchaseRepository.save(purchaseRequestDto);

        purchaseRequestDto.getCarsPurchase().forEach(carPurchase -> {
            CarDto carActual = iCarRepository.getCarById(carPurchase.getCodeCar())
                    .orElseThrow(() -> new IllegalArgumentException("Carro no encontrado"));

            int cantidadAComprar = carPurchase.getQuantity();
            int stockDisponible = carActual.getStock();

            if (cantidadAComprar <= stockDisponible) {
                carActual.setStock(stockDisponible - cantidadAComprar);
                iCarRepository.save(carActual);
            } else {
                throw new IllegalArgumentException("Stock insuficiente para comprar la cantidad especificada");
            }
        });

        return purchaseBillResponseDto;
    }

}
