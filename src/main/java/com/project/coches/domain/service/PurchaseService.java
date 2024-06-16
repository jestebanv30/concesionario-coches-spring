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

import java.time.LocalDateTime;
import java.util.Arrays;
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

        validatePurchase(purchaseRequestDto);

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

    private void validatePurchase(PurchaseRequestDto purchaseRequestDto){

        List<String> validPayment = Arrays.asList("Efectivo", "Transferencia", "PSE");

        // Validar fecha
        /*LocalDateTime currentDate = LocalDateTime.now();
        if (purchaseRequestDto.getDate() == null || purchaseRequestDto.getDate().isAfter(currentDate)) {
            throw new IllegalArgumentException("La fecha debe ser menor o igual a la fecha actual.");
        }*/

        // Validar total
        if (purchaseRequestDto.getTotal() == null || purchaseRequestDto.getTotal() <= 0) {
            throw new IllegalArgumentException("El total debe ser un número positivo mayor a 0.");
        }

        // Validar metodos de pago
        if (purchaseRequestDto.getPaymentMethod() == null || !validPayment.contains(purchaseRequestDto.getPaymentMethod())) {
            throw new IllegalArgumentException("El medio de pago debe ser uno de: Efectivo, Transferencia, PSE.");
        }
    }
}
