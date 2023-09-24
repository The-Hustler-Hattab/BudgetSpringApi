package com.mtattab.emailservice.entity;

import com.mtattab.emailservice.model.BudgetModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "budget")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BudgetTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "cost", nullable = false)
    private Long cost;
    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

    public BudgetTableEntity(BudgetModel budgetModel, String userEmail, String userFullName){
        this.category= budgetModel.getCategory();
        this.color= budgetModel.getColor();
        this.cost= budgetModel.getMonthlyCost();
        this.createdBy=userFullName;
        this.email=userEmail;
    }

    public BudgetTableEntity(BudgetModel budgetModel){
        this.id = budgetModel.getId();
        this.category= budgetModel.getCategory();
        this.color= budgetModel.getColor();
        this.cost= budgetModel.getMonthlyCost();

    }
}
