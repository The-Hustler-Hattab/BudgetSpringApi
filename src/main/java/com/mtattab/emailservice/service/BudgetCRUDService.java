package com.mtattab.emailservice.service;

import com.mtattab.emailservice.entity.BudgetTableEntity;
import com.mtattab.emailservice.model.BudgetModel;
import com.mtattab.emailservice.model.ResponseRestModel;
import com.mtattab.emailservice.repository.BudgetRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;



@Service
@Slf4j
public class BudgetCRUDService {

    @Autowired
    BudgetRepository budgetRepository;


    public ResponseRestModel getSavedRecords(String userEmail){
        ResponseRestModel responseRestModel = new ResponseRestModel();

        List<BudgetTableEntity> budgetTableEntityList =  budgetRepository.getAllRecordsByEmail(userEmail);

        List<BudgetModel> budgetModels = budgetTableEntityList.stream().map(BudgetModel::new).toList();
        responseRestModel.setStatusCode(HttpStatus.OK.value());
        responseRestModel.setStatusMessage("records retrieved successfuly");
        responseRestModel.setBudgetModels(budgetModels);
        return responseRestModel;
    }

    public ResponseRestModel saveBudgetRecord(String userEmail ,String userFullName ,  BudgetModel budgetModel){
        ResponseRestModel responseRestModel = new ResponseRestModel();
        BudgetTableEntity budgetTableEntity = new BudgetTableEntity(budgetModel,userEmail,userFullName);
        budgetTableEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        budgetRepository.save(budgetTableEntity);
        responseRestModel.setStatusCode(HttpStatus.OK.value());
        responseRestModel.setStatusMessage("records saved successfuly");
        return responseRestModel;
    }
    @Transactional
    public ResponseRestModel updateBudgetRecord(String userEmail ,  BudgetModel budgetModel){
        ResponseRestModel responseRestModel = new ResponseRestModel();

        int updatedRecs= budgetRepository.updateBudgetRecord(
                budgetModel.getCategory(), budgetModel.getCost(),
                budgetModel.getColor(), userEmail, budgetModel.getId(),
                Timestamp.valueOf(LocalDateTime.now()));

        if (updatedRecs > 0  ){
            responseRestModel.setStatusCode(HttpStatus.OK.value());
            responseRestModel.setStatusMessage("record was updated successfuly");
        }else {
            responseRestModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseRestModel.setStatusMessage("record failed to update");
        }

        return responseRestModel;
    }

    @Transactional
    public ResponseRestModel deleteBudgetRecord(String userEmail ,  Long id){
        ResponseRestModel responseRestModel = new ResponseRestModel();

        int deletedRecord= budgetRepository.deleteBudgetRecord(userEmail, id);

        if (deletedRecord > 0  ){
            responseRestModel.setStatusCode(HttpStatus.OK.value());
            responseRestModel.setStatusMessage("record was deleted successfuly");
        }else {
            responseRestModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseRestModel.setStatusMessage("failed to delete record");
        }

        return responseRestModel;
    }


}

