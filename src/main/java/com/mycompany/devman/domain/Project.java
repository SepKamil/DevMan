/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author jakub
 */
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    
    @NotEmpty
    @NotNull
    private String name;
    
    @NotNull
    private LocalDate startDate;
    
    @NotNull
    private LocalDate endDate;
}
