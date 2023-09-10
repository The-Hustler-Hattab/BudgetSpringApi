package com.mtattab.emailservice.restcontroller;
import com.mtattab.emailservice.consts.Constants;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(path = "/v1/api", produces = Constants.JSON)
@Validated
public class BudgetCRUDController {



}
