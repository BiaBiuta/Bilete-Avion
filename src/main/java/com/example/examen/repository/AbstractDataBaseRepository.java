package com.example.examen.repository;


import com.example.examen.domain.Client;
import com.example.examen.domain.Entity;

public abstract class AbstractDataBaseRepository<ID,E extends Entity<ID>> implements CrudRepository<ID,E> {
    protected String url;
    protected String username;
    protected String password;



    public AbstractDataBaseRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    //public abstract Client findOne(String aLong) throws IllegalArgumentException;

    //public abstract Client findOnePers(String aLong) throws IllegalArgumentException;
}
