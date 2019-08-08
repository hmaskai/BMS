package com.shilymily.bms.service;

import com.shilymily.bms.entity.Company;
import com.shilymily.bms.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmaskai on 9/8/18.
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company){
        companyRepository.save(company);
        return company;
    }

    public List<Company> getAllCompanies(){
        List<Company> companyList = new ArrayList<>();
        companyRepository.findAll().forEach(companyList :: add);
        return companyList;
    }

    public Company getCompanyByName(int id){
        return companyRepository.findCompanyByCompanyId(id);
    }

    public Company updateCompany(Company company){
        return companyRepository.save(company);
    }

    public void removeCompany(int id){
        companyRepository.deleteCompanyByCompanyId(id);
    }
}
