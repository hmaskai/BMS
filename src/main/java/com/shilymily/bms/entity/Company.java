package com.shilymily.bms.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by hmaskai on 9/8/18.
 */
@Entity
@Table(name = "companies")
@Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer company_id;
    @Column(unique = true)
    @NotNull
    String name;
    @NotNull
    String address;
    @NotNull
    String phoneNumber;
    @NotNull
    String email;
    @NotNull
    String panCardNumber;
}
