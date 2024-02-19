package com.project.coches.controller;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.useCase.ICarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/cars")
public class CarController {

    private final ICarUseCase iCarUseCase;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok(iCarUseCase.getAll());
    }

    @GetMapping(path = "/carBrandId-{carBrandId}")
    public ResponseEntity<List<CarDto>> getByIdCarBrand(@PathVariable Integer carBrandId) {
        return ResponseEntity.ok(iCarUseCase.getByIdCarBrand(carBrandId));
    }

    @GetMapping(path = "/priceLess-{price}")
    public ResponseEntity<List<CarDto>> getCarByPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(iCarUseCase.getCarByPriceLessThan(price));
    }

    @GetMapping(path = "/priceGreater-{price}")
    public ResponseEntity<List<CarDto>> getCarByPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok(iCarUseCase.getCarByPriceGreaterThan(price));
    }

    @GetMapping(path = "/carId-{idCar}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Integer idCar) {
        return ResponseEntity.of(iCarUseCase.getCarById(idCar));
    }

    @PostMapping
    public ResponseEntity<CarDto> save(@RequestBody CarDto newCarDto) {
        return ResponseEntity.ok(iCarUseCase.save(newCarDto));
    }

    @DeleteMapping(path = "/carId-{idCar}")
    public ResponseEntity<CarDto> delete(@PathVariable Integer idCar) {
        return new ResponseEntity<>(this.iCarUseCase.delete(idCar) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
