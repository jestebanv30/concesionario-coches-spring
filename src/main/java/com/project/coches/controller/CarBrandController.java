package com.project.coches.controller;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.service.ICarBrandService;
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
@RequestMapping(path = "/marcas-coches")
public class CarBrandController {

    /**
     * Servicio de marca coche
     */
    private final ICarBrandService iCarBrandService;

    /**
     * Devuelve lista de marcas coche
     * @return HttpCode Ok con lista de marcas coche
     */
    @GetMapping()
    public ResponseEntity<List<CarBrandPojo>> getAll(){
        return ResponseEntity.ok(iCarBrandService.getAll());
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
    public ResponseEntity<CarBrandPojo> getCarBrand(@PathVariable Integer id){
     return ResponseEntity.of(iCarBrandService.getCarBrand(id));
    }

    /**
     * Crea una nueva marca coche
     * @param carBrandPojoNew marca coche a crear
     * @return HttpCode Created si la guarda correctamente, HttpCode BadRequest de lo contrario
     */
    @PostMapping
    public ResponseEntity<CarBrandPojo> save(@RequestBody CarBrandPojo carBrandPojoNew){

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(iCarBrandService.save(carBrandPojoNew));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Actualiza una marca coche
     * @param carBrandPojoUpdate Marca coche actualizada
     * @return HttpCode Ok si la actualiza correctamente
     */
    @PutMapping
    public ResponseEntity<CarBrandPojo> update(@RequestBody CarBrandPojo carBrandPojoUpdate){
        return ResponseEntity.of(iCarBrandService.update(carBrandPojoUpdate));
    }

    /**
     * Elimina una marca coche dada su id
     * @param id Id de la marca coche a eliminar
     * @return HttpCode Ok si la elimina, HttpCode Not Found si no Existe
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CarBrandPojo> delete(@PathVariable Integer id){
        return new ResponseEntity<>(this.iCarBrandService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
