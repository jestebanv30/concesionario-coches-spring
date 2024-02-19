package com.project.coches.controller;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.domain.useCase.ICarBrandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador rest de Marca Coche
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/car-brands")
public class CarBrandController {

    /**
     * Servicio de marca coche
     */
    private final ICarBrandUseCase iCarBrandUseCase;

    /**
     * Devuelve lista de marcas coche
     * @return HttpCode Ok con lista de marcas coche
     */
    @GetMapping()
    public ResponseEntity<List<CarBrandDto>> getAll(){
        return ResponseEntity.ok(iCarBrandUseCase.getAll());
        // return ResponseEntity.status(HttpStatus.OK)
        //        .body(iCarBrandService.getAll()); - ALTERNATIVA es más completa en términos de HTTPSTATUS

        // return new ResponseEntity<>(iCarBrandService.getAll(), HttpStatus.OK); - ALTERNATIVA
    }

    /**
     * Devuelve una marca coche dado su id
     * @param id Id de la marca coche a buscar
     * @return HttpCode Ok si la encuentra, HttpCode Not Found de lo contrario = .of
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<CarBrandDto> getCarBrand(@PathVariable Integer id){
     return ResponseEntity.of(iCarBrandUseCase.getCarBrand(id));
    }

    /**
     * Crea una nueva marca coche
     * @param carBrandDtoNew marca coche a crear
     * @return HttpCode Created si la guarda correctamente, HttpCode BadRequest de lo contrario
     */
    @PostMapping
    public ResponseEntity<CarBrandDto> save(@RequestBody CarBrandDto carBrandDtoNew){

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(iCarBrandUseCase.save(carBrandDtoNew));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Actualiza una marca coche
     * @param carBrandDtoUpdate Marca coche actualizada
     * @return HttpCode Ok si la actualiza correctamente
     */
    @PutMapping
    public ResponseEntity<CarBrandDto> update(@RequestBody CarBrandDto carBrandDtoUpdate){
        return ResponseEntity.of(iCarBrandUseCase.update(carBrandDtoUpdate));
    }

    /**
     * Elimina una marca coche dada su id
     * @param id Id de la marca coche a eliminar
     * @return HttpCode Ok si la elimina, HttpCode Not Found si no Existe
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CarBrandDto> delete(@PathVariable Integer id){
        return new ResponseEntity<>(this.iCarBrandUseCase.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
