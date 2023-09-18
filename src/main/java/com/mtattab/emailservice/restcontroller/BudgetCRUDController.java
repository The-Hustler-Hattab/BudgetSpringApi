package com.mtattab.emailservice.restcontroller;
import com.mtattab.emailservice.consts.Constants;
import com.mtattab.emailservice.model.BudgetModel;
import com.mtattab.emailservice.model.ResponseRestModel;
import com.mtattab.emailservice.service.BudgetCRUDService;
import com.mtattab.emailservice.util.UserEmailUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;


import static com.mtattab.emailservice.consts.Constants.BUDGET_MAPPING;

@RestController
@RequestMapping(path = BUDGET_MAPPING, produces = Constants.JSON)
@Validated
public class BudgetCRUDController {


    @Autowired
    BudgetCRUDService budgetCRUDService;



    @GetMapping("/getSavedBudgetRecords")
    public ResponseEntity<ResponseRestModel> getAllBudgetRecord(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseRestModel responseRestModel= budgetCRUDService.getSavedRecords(UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_EMAIL));

        return new ResponseEntity<>(responseRestModel, HttpStatusCode.valueOf(responseRestModel.getStatusCode()));

    }

    @PostMapping("/saveBudgetRecord")
    public ResponseEntity<ResponseRestModel> saveBudgetRecord( @RequestBody @Valid BudgetModel budgetModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseRestModel responseRestModel= budgetCRUDService.saveBudgetRecord(
                UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_EMAIL),UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_FULL_NAME),budgetModel);

        return new ResponseEntity<>(responseRestModel, HttpStatusCode.valueOf(responseRestModel.getStatusCode()));

    }
    @PutMapping("/updateBudgetRecord")
    public ResponseEntity<ResponseRestModel> updateBudgetRecord(@Valid @RequestBody BudgetModel budgetModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseRestModel responseRestModel= budgetCRUDService.updateBudgetRecord(
                UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_EMAIL),budgetModel);

        return new ResponseEntity<>(responseRestModel, HttpStatusCode.valueOf(responseRestModel.getStatusCode()));

    }
    @PostMapping("/deleteBudgetRecord")
    public ResponseEntity<ResponseRestModel> deleteBudgetRecord( @RequestBody BudgetModel budgetModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseRestModel responseRestModel= budgetCRUDService.deleteBudgetRecord(
                UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_EMAIL),budgetModel.getId());

        return new ResponseEntity<>(responseRestModel, HttpStatusCode.valueOf(responseRestModel.getStatusCode()));

    }


}
