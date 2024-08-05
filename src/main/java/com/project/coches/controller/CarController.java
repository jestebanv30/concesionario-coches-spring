package com.project.coches.controller;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.useCase.ICarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador (endpoints) de coche
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cars")
public class CarController {

    private final ICarUseCase iCarUseCase;

    @CrossOrigin(origins = "https://concesionario-coches-front.vercel.app")
    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok().body(iCarUseCase.getAll());
    }

    @GetMapping(path = "/carBrandId-{carBrandId}")
    public ResponseEntity<List<CarDto>> getByIdCarBrand(@PathVariable Integer carBrandId) {
        return ResponseEntity.ok().body(iCarUseCase.getByIdCarBrand(carBrandId));
    }

    @GetMapping(path = "/priceLess-{price}")
    public ResponseEntity<List<CarDto>> getCarByPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok().body(iCarUseCase.getCarByPriceLessThan(price));
    }

    @GetMapping(path = "/priceGreater-{price}")
    public ResponseEntity<List<CarDto>> getCarByPriceGreaterThan(@PathVariable Double price) {
        return ResponseEntity.ok().body(iCarUseCase.getCarByPriceGreaterThan(price));
    }

    @GetMapping(path = "/carId-{idCar}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Integer idCar) {
        return ResponseEntity.of(iCarUseCase.getCarById(idCar));
    }

    @PostMapping()
    public ResponseEntity<CarDto> save(@RequestBody CarDto newCarDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCarUseCase.save(newCarDto));
    }

    @PutMapping
    public ResponseEntity<CarDto> update(@RequestBody CarDto carDtoNew) {
        return ResponseEntity.of(iCarUseCase.update(carDtoNew));
    }

    @DeleteMapping(path = "/{idCar}")
    public ResponseEntity<CarDto> delete(@PathVariable Integer idCar) {
        return new ResponseEntity<>(this.iCarUseCase.delete(idCar) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
