package com.shilymily.bms.controller;

import com.shilymily.bms.BmsApplication;
import com.shilymily.bms.entity.Company;
import com.shilymily.bms.service.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by hmaskai on 9/14/18.
 */

@SpringBootTest(classes = BmsApplication.class)
@RunWith(SpringRunner.class)
public class CompanyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CompanyController companyController;

    @MockBean
    private CompanyService companyService;

    private Company testCompany = new Company();

    private String companyJson = "{\n" +
            "    \"name\": \"TestCompany\",\n" +
            "    \"address\": \"TestCompanyAddress\",\n" +
            "    \"phoneNumber\": \"TestCompanyPhoneNumber\",\n" +
            "    \"email\": \"TestCompanyEmail\",\n" +
            "    \"panCardNumber\": \"TestCompanyPanCardNumber\"\n" +
            "}";
    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.companyController).build();// Standalone context
    }

    @Before
    public void setUp(){
        testCompany.setName("TestCompany");
        testCompany.setPhoneNumber("TestCompanyPhoneNumber");
        testCompany.setAddress("TestCompanyAddress");
        testCompany.setEmail("TestCompanyEmail");
        testCompany.setPanCardNumber("TestCompanyPanCardNumber");
    }

    @Test
    public void testCreateFunctionality() throws Exception{

        when(companyService.addCompany(Mockito.any(Company.class))).thenReturn(testCompany);

        MockHttpServletResponse response = mockMvc.perform(post("/master/company")
                .accept(MediaType.APPLICATION_JSON)
                .content(companyJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        JSONAssert.assertEquals("{company_id=null, name=TestCompany, address=TestCompanyAddress, phoneNumber=TestCompanyPhoneNumber, email=TestCompanyEmail, panCardNumber=TestCompanyPanCardNumber}", response.getContentAsString(), false);
    }

    @Test
    public void testReadFunctionality() throws Exception{

        mockMvc.perform(get("/master/company/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].name", is("TestCompany")))
                .andExpect(jsonPath(("[0].phoneNumber"),is("TestPhoneNumber")));
    }

    @Test
    public void testUpdateFunctionality(){

    }

    @Test
    public void testDeleteFunctionality(){

    }
}
