package com.shilymily.dataservice.controllers;

import com.shilymily.dataservice.models.Company;
import com.shilymily.dataservice.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by hmaskai
 * 6/8/20.
 */

@RestController
@RequestMapping("/master/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Company getCompanyByName(@PathVariable int id){
        return companyService.getCompanyByName(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Company updateCompany(@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removeCompany(@PathVariable int id){
        companyService.removeCompany(id);
    }
}
