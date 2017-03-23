/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jakub
 */

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    private String login;
    
    @NotNull
    @Size(min = 6)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType accountType;
    
    @NotNull
    @Size(min = 3)
    private String name;
    
    @NotNull
    @Size(min = 3)
    private String lastName;
    
    @Email 
    private String email;
    
    @NotNull
    @Size(min = 11, max = 11)
    private String pesel;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the accountType
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pesel
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * @param pesel the pesel to set
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * @return the manager
     */
    public User getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(User manager) {
        this.manager = manager;
    }
    
    @Override
    public String toString() {
        return getName()+" "+getLastName();
    }
}
