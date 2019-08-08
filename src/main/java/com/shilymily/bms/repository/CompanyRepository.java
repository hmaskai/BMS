package com.shilymily.bms.repository;

import com.shilymily.bms.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hmaskai on 9/8/18.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findCompaniesByUserId(int id);
    Company findCompanyByCompanyId(int id);
    void deleteCompaniesByUserId(int id);
    void deleteCompanyByCompanyId(int id);
}
