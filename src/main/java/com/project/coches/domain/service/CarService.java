package com.project.coches.domain.service;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.domain.useCase.ICarUseCase;
import com.project.coches.exception.typesexceptions.NoExistCarException;
import com.project.coches.exception.typesexceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de coche
 */
@RequiredArgsConstructor
@Service
public class CarService implements ICarUseCase {

    private final ICarRepository iCarRepository;

    /**
     * Obtiene una lista de coche
     *
     * @return lista de coche
     */
    @Override
    public List<CarDto> getAll() {

        // Con esto puedo jugar con la lógica y permitir que el cliente que llegue aquí y tenga el rol customer le salgan 30 autos
        // y si tiene el rol admin le salgan todos, cualquier cosa
        // Valido el rol para cambiar una acción, no para ver si existe
        System.out.println("Lista de Roles");
        var listaRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(listaRoles);

        if (listaRoles.stream().anyMatch(rol -> String.valueOf(rol).equals("ROLE_Administrador"))) {
            System.out.println("Te voy a mostrar la lista de carros por acá");
            return iCarRepository.getAll();
        }
        /**if (listaRoles.stream().anyMatch(rol -> String.valueOf(rol).equals("ROLE_Administrador"))) {
         throw new UnauthorizedException();
         }*/

        return iCarRepository.getAll();
    }

    @Override
    public List<CarDto> getByIdCarBrand(Integer carBrandId) {
        return iCarRepository.getByIdCarBrand(carBrandId);
    }

    @Override
    public List<CarDto> getCarByPriceLessThan(Double price) {
        return iCarRepository.getCarByPriceLessThan(price);
    }

    @Override
    public List<CarDto> getCarByPriceGreaterThan(Double price) {
        return iCarRepository.getCarByPriceGreaterThan(price);
    }

    @Override
    public Optional<CarDto> getCarById(Integer idCar) {

        Optional<CarDto> existingCarById = iCarRepository.getCarById(idCar);

        if (existingCarById.isEmpty()) {
            throw new NoExistCarException();
        }

        return iCarRepository.getCarById(idCar);
    }

    @Override
    public CarDto save(CarDto newCarDto) {
        validateCarDto(newCarDto);

        return iCarRepository.save(newCarDto);
    }

    @Override
    public Optional<CarDto> update(CarDto carDtoNew) {

        Optional<CarDto> existingCar = iCarRepository.getCarById(carDtoNew.getCodeCar());

        if (existingCar.isEmpty()) {
            throw new NoExistCarException();
        }

        return Optional.of(iCarRepository.save(carDtoNew));
    }

    @Override
    public boolean delete(Integer idCar) {

        Optional<CarDto> existingCarById = iCarRepository.getCarById(idCar);

        if (existingCarById.isEmpty()) {
            throw new NoExistCarException();
        }

        iCarRepository.delete(idCar);
        return true;
    }

    private void validateCarDto(CarDto carDto) {
        // Validar Marca de coche
        if (carDto.getCarBrandId() == null) {
            throw new IllegalArgumentException("La marca de coche no puede ser nula");
        }

        // Validar Referencia marca
        if (carDto.getReference() == null || carDto.getReference().length() < 5 || carDto.getReference().length() > 30) {
            throw new IllegalArgumentException("La referencia de marca debe tener entre 5 y 30 caracteres");
        }

        // Validar Precio
        if (carDto.getPrice() == null || carDto.getPrice() <= 0 || carDto.getPrice() > 9999999999.99) {
            throw new IllegalArgumentException("El precio debe ser un número positivo mayor a 0 y de máximo 10 dígitos");
        }

        // Validar Caballos de fuerza
        if (carDto.getHorsepower() == null || carDto.getHorsepower() <= 100 || carDto.getHorsepower() > 1000) {
            throw new IllegalArgumentException("Los caballos de fuerza deben ser un número mayor a 100 y menor o igual que 1000");
        }

        // Validar Cantidad de puertas
        if (!Arrays.asList(2, 4).contains(carDto.getNumberDoor())) {
            throw new IllegalArgumentException("La cantidad de puertas debe ser '2' o '4'");
        }

        // Validar Cilindraje
        if (carDto.getEngineDisplacement() == null || carDto.getEngineDisplacement() <= 0 || carDto.getEngineDisplacement() > 999999) {
            throw new IllegalArgumentException("El cilindraje debe ser un número positivo mayor a 0 y de máximo 6 dígitos");
        }

        // Validar Transmisión
        List<String> validTransmissions = Arrays.asList("Transmisión Manual", "Transmisión automática");
        if (!validTransmissions.contains(carDto.getTransmission())) {
            throw new IllegalArgumentException("La transmisión debe ser 'Transmisión Manual' o 'Transmisión automática'");
        }

        // Validar Tipo de combustible
        List<String> validFuelTypes = Arrays.asList("Gasolina", "Diesel");
        if (!validFuelTypes.contains(carDto.getFuelType())) {
            throw new IllegalArgumentException("El tipo de combustible debe ser 'Gasolina' o 'Diesel'");
        }

        // Validar Cantidad de asientos
        if (carDto.getNumberSeat() == null || carDto.getNumberSeat() <= 1 || carDto.getNumberSeat() >= 9) {
            throw new IllegalArgumentException("La cantidad de asientos debe ser un número entero positivo mayor que 1 y menor que 9");
        }

        // Validar Tracción
        List<String> validTractions = Arrays.asList("Tracción delantera", "Tracción trasera", "Tracción a las cuatro ruedas");
        if (!validTractions.contains(carDto.getTraction())) {
            throw new IllegalArgumentException("La tracción debe ser 'Tracción delantera', 'Tracción trasera' o 'Tracción a las cuatro ruedas'");
        }

        // Validar Dirección
        List<String> validSteerings = Arrays.asList("Dirección de piñón y cremallera", "Dirección asistida eléctricamente", "Dirección asistida hidráulicamente");
        if (!validSteerings.contains(carDto.getSteering())) {
            throw new IllegalArgumentException("La dirección debe ser 'Dirección de piñón y cremallera', 'Dirección asistida eléctricamente' o 'Dirección asistida hidráulicamente'");
        }

        // Validar Categoría
        List<String> validCategories = Arrays.asList("Automóvil", "Camioneta", "Deportivo");
        if (!validCategories.contains(carDto.getCategory())) {
            throw new IllegalArgumentException("La categoría debe ser 'Automóvil', 'Camioneta' o 'Deportivo'");
        }

        // Validar Ruta de imagen
        if (carDto.getImagePath() == null || (!carDto.getImagePath().endsWith(".jpg") && !carDto.getImagePath().endsWith(".png") && !carDto.getImagePath().endsWith(".webp"))) {
            throw new IllegalArgumentException("La ruta de imagen debe tener una extensión válida (.jpg, .png, .webp)");
        }

        // Validar Stock
        if (carDto.getStock() == null || carDto.getStock() <= 0) {
            throw new IllegalArgumentException("El stock debe ser un número entero positivo mayor a 0");
        }
    }
}
