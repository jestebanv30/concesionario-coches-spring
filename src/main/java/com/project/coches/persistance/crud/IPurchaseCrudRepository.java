package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz de crud en la base de datos para compras
 */
public interface IPurchaseCrudRepository extends JpaRepository<PurchaseEntity, Integer> {


    List<PurchaseEntity> findAllByCardIdCustomer(String id);

    List<PurchaseEntity> findAllByTotalLessThanEqualOrderByTotalDesc(Double total);

    List<PurchaseEntity> findAllByTotalGreaterThanEqualOrderByTotalAsc(Double total);

    List<PurchaseEntity> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
