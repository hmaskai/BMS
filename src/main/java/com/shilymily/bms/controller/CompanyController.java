package com.shilymily.bms.controller;

import com.shilymily.bms.entity.Company;
import com.shilymily.bms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by hmaskai on 9/8/18.
 */
@RestController
@RequestMapping("/master/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateCompany(@RequestBody Company company){
        companyService.addOrUpdateCompany(company);
    }

    @DeleteMapping(path = "/remove/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCompany(@PathVariable String name){
        companyService.removeCompany(name);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Company getCompanyByName(@PathVariable String name){
        return companyService.getCompanyByName(name);
    }
}
