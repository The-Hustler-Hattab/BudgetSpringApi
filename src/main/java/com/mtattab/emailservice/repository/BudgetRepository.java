package com.mtattab.emailservice.repository;

import com.mtattab.emailservice.entity.BudgetTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface BudgetRepository extends JpaRepository<BudgetTableEntity, Long> {

}
