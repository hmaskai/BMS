package com.shilymily.bms.repository;

import com.shilymily.bms.entity.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hmaskai on 9/14/18.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository companyRepository;

    private Company testCompany = new Company();

    @Before
    public void setUp(){
        //Make an entry in database
        testCompany.setName("TestCompany");
        testCompany.setPhoneNumber("TestCompanyPhoneNumber");
        testCompany.setAddress("TestCompanyAddress");
        testCompany.setEmail("TestCompanyEmail");
        testCompany.setPanCardNumber("TestCompanyPanCardNumber");
        entityManager.persist(testCompany);
        entityManager.flush();
    }

    @Test
    public void testFindByNameFunctionality() {
        //Find the company by name
        Company companyFound = companyRepository.findCompanyByCompanyId(1);
        assertThat(companyFound.getName()).isEqualTo(testCompany.getName());
    }

    @Test
    public void testDeleteByNameFunctionality() {
        //Delete the company
        companyRepository.deleteCompanyByCompanyId(1);

        Company companyFound = companyRepository.findCompanyByCompanyId(1);
        assertThat(companyFound).isNull();
    }
}
