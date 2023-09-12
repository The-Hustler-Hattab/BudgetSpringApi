package com.mtattab.emailservice.repository;

import com.mtattab.emailservice.entity.BudgetTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@RepositoryRestResource(exported = false)
@Repository
public interface BudgetRepository extends JpaRepository<BudgetTableEntity, Long> {


    @Query("SELECT u FROM BudgetTableEntity u where u.email = :email ORDER BY u.id")
    List<BudgetTableEntity> getAllRecordsByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE BudgetTableEntity u SET u.category = :category, u.cost = :cost, u.color = :color, u.updatedAt= :updatedAt " +
            "WHERE u.email = :email and u.id = :id")
    int updateBudgetRecord(
            @Param("category") String category,
            @Param("cost") String cost,
            @Param("color") String color,
            @Param("email") String email,
            @Param("id") Long id,
            @Param("updatedAt") Timestamp updatedAt
    );

    @Modifying
    @Query("DELETE FROM BudgetTableEntity u WHERE u.email = :email and u.id =:id ")
    int deleteBudgetRecord(
            @Param("email") String email,
            @Param("id") Long id
    );

}
