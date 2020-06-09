package com.shilymily.dataservice.models;

import javax.persistence.*;

/**
 * Created by hmaskai
 * 6/8/20.
 */

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int companyId;

    private int userId;

    @Column(unique = true)
    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String panCardNumber;
}
