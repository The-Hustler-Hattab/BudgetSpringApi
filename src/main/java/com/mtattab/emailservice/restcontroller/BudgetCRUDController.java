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
public class BudgetCRUDController {


    @Autowired
    BudgetCRUDService budgetCRUDService;

    @GetMapping("/hello")
    public String helloWorld(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(UserEmailUtil.getUserDetailByClaim(SecurityContextHolder.getContext().getAuthentication() , Constants.CLAIM_FULL_NAME));
        System.out.println(UserEmailUtil.getUserDetailByClaim(SecurityContextHolder.getContext().getAuthentication() , Constants.CLAIM_EMAIL));

        return "{\"word\":\"hello\"}";
    }

    @GetMapping("/getSavedBudgetRecords")
    public ResponseEntity<ResponseRestModel> getAllBudgetRecord(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseRestModel responseRestModel= budgetCRUDService.getSavedRecords(UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_EMAIL));

        return new ResponseEntity<>(responseRestModel, HttpStatusCode.valueOf(responseRestModel.getStatusCode()));

    }

    @PostMapping("/saveBudgetRecord")
    public ResponseEntity<ResponseRestModel> saveBudgetRecord(@Valid @RequestBody BudgetModel budgetModel){
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
    @DeleteMapping("/deleteBudgetRecord")
    public ResponseEntity<ResponseRestModel> deleteBudgetRecord(@Valid @RequestBody BudgetModel budgetModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseRestModel responseRestModel= budgetCRUDService.deleteBudgetRecord(
                UserEmailUtil.getUserDetailByClaim(auth,Constants.CLAIM_EMAIL),budgetModel);

        return new ResponseEntity<>(responseRestModel, HttpStatusCode.valueOf(responseRestModel.getStatusCode()));

    }


}
