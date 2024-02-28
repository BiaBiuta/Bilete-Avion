package com.example.examen.repository;

import com.example.examen.domain.Client;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Repository extends AbstractDataBaseRepository<Long, Client> {


    public Repository(String url, String username, String password) {
        super(url, username, password);

    }




    @Override
    public Client findOne(Long aLong) throws IllegalArgumentException {
        return null;
    }
    @Override
    public Client findOnePers(String aLong) throws IllegalArgumentException {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from  clienti where username=?")) {
            {
                statement.setString(1, aLong);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String firstName = resultSet.getString("username");
                    String lastName = resultSet.getString("name");
                    Long longID = resultSet.getLong("id");

                    Client u = new Client(firstName, lastName);
                    u.setId(longID);
                    return u;
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public Iterable<Client> findAll() {
        return null;
    }

    @Override
    public Iterable<Client> findAll(Long id) {
        return null;
    }

    @Override
    public Iterable<Client> findAllTicket(String aLong) {
        return null;
    }

    @Override
    public Iterable<Client> findAllLocatii(String locatie, String locatie2, LocalDate date) {
        return null;
    }


    @Override
    public Client save(Client entity) {
        return null;
    }

    @Override
    public Client delete(Long aLong) {
        return null;
    }



    @Override
    public Client update(Client entity) {
        return null;
    }
}