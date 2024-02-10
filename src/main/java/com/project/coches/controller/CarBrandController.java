package com.project.coches.controller;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.domain.service.ICarBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/marcas-coches")
public class CarBrandController {

    private final ICarBrandService iCarBrandService;

    @GetMapping
    public ResponseEntity<List<CarBrandPojo>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarBrandPojo> getCarBrand(@PathVariable Integer id){
        return ResponseEntity.of(iCarBrandService.getCarBrand(id));
    }

    @PostMapping
    public ResponseEntity<CarBrandPojo> save(CarBrandPojo carBrandPojoNew){
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandService.save(carBrandPojoNew));
    }

    @PutMapping
    public ResponseEntity<CarBrandPojo> update(CarBrandPojo carBrandPojo) {
        return ResponseEntity.of(iCarBrandService.update(carBrandPojo));
    }

    public ResponseEntity<CarBrandPojo> delete(Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandService.delete(id) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
