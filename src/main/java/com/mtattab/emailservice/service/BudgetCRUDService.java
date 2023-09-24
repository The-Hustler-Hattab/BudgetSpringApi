package com.mtattab.emailservice.service;

import com.mtattab.emailservice.model.BudgetModel;
import com.mtattab.emailservice.model.ResponseRestModel;

public interface BudgetCRUDService {

    ResponseRestModel getSavedRecords(String userEmail);
    ResponseRestModel saveBudgetRecord(String userEmail, String userFullName, BudgetModel budgetModel);
    ResponseRestModel updateBudgetRecord(String userEmail, BudgetModel budgetModel);

    ResponseRestModel deleteBudgetRecord(String userEmail,  Long id);

}
