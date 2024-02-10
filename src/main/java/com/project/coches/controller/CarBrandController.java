package com.project.coches.controller;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.repository.ICarBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/marcas-coches")
public class CarBrandController {

    private final ICarBrandRepository iCarBrandRepository;

    @GetMapping
    public ResponseEntity<List<CarBrandPojo>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandRepository.getAll());
    }

    @GetMapping(path = /{id})
    public ResponseEntity<CarBrandPojo> getCarBrand(PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(iCarBrandRepository.getAll());
    }


}
