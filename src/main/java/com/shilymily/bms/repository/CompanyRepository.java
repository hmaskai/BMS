package com.shilymily.bms.repository;

import com.shilymily.bms.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hmaskai on 9/8/18.
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Company findCompanyByName(String name);
    void deleteByName(String name);
}
