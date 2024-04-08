package com.project.coches.controller;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.domain.useCase.ICarBrandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador (endpoints) de Marca coche
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/car-brands")
public class CarBrandController {

    private final ICarBrandUseCase iCarBrandUseCase;

    @GetMapping
    public ResponseEntity<List<CarBrandDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandUseCase.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarBrandDto> getCarBrand(@PathVariable Integer id) {
        return ResponseEntity.of(iCarBrandUseCase.getCarBrand(id));
    }

    @GetMapping(path = "/description-{description}")
    public ResponseEntity<CarBrandDto> getCarBrandByDescription(@PathVariable String description) {
        return ResponseEntity.of(iCarBrandUseCase.getCarBrandByDescription(description));
    }

    @PostMapping
    public ResponseEntity<CarBrandDto> save(@RequestBody CarBrandDto carBrandDtoNew) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCarBrandUseCase.save(carBrandDtoNew));
    }

    @PutMapping
    public ResponseEntity<CarBrandDto> update(@RequestBody CarBrandDto carBrandDto) {
        return ResponseEntity.of(iCarBrandUseCase.update(carBrandDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CarBrandDto> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(this.iCarBrandUseCase.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
