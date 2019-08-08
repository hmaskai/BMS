package com.shilymily.bms.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by hmaskai on 9/8/18.
 */
@Entity
@Table(name = "companies")
@Data
public class Company extends DateAudit{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int companyId;

    @NotNull
    private int userId;

    @Column(unique = true)
    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private String panCardNumber;
}
