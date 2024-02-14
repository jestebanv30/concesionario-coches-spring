package com.project.coches.controller;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.service.ICarBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/car-brands")
public class CarBrandController {

    private final ICarBrandService iCarBrandService;

    @GetMapping
    public ResponseEntity<List<CarBrandPojo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarBrandPojo> getCarBrand(@PathVariable Integer id) {
        return ResponseEntity.of(iCarBrandService.getCarBrand(id));
    }

    @GetMapping(path = "/description-{description}")
    public ResponseEntity<CarBrandPojo> getCarBrandByDescription(@PathVariable String description) {
        return ResponseEntity.of(iCarBrandService.getCarBrandByDescription(description));
    }

    @PostMapping
    public ResponseEntity<CarBrandPojo> save(@RequestBody CarBrandPojo carBrandPojoNew) {
        try {

            CarBrandPojo savedCarBrandPojo = iCarBrandService.save(carBrandPojoNew);

            if (savedCarBrandPojo == null) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .build();
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedCarBrandPojo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<CarBrandPojo> update(@RequestBody CarBrandPojo carBrandPojo) {
        return ResponseEntity.of(iCarBrandService.update(carBrandPojo));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CarBrandPojo> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(this.iCarBrandService.delete(id)
                ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
