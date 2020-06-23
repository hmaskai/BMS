package com.shilymily.dataservice.repositories;

import com.shilymily.dataservice.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hmaskai
 * 6/8/20.
 */

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findCompaniesByUserId(int id);

    Company findCompanyByCompanyId(int id);

    void deleteCompaniesByUserId(int id);
    
    void deleteCompanyByCompanyId(int id);
}
