package com.mtattab.emailservice.model;

import com.mtattab.emailservice.annotations.GreaterThanValidator;
import com.mtattab.emailservice.entity.BudgetTableEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetModel {

    private Long id;

    @NotBlank(message="category must not be blank")
    @Size(min=2, message="category must be at least 2 characters long")
    private String category;

    @NotNull
    @GreaterThanValidator(1)
    private Long cost;


    @NotBlank(message="color must not be blank")
    private String color;

    public BudgetModel(BudgetTableEntity budgetTableEntity){
        this.id= budgetTableEntity.getId();
        this.category= budgetTableEntity.getCategory();
        this.cost= budgetTableEntity.getCost();
        this.color= budgetTableEntity.getColor();


    }
}
