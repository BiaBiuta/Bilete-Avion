package com.example.examen.repository;

import com.example.examen.domain.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RepositoryTicket extends AbstractDataBaseRepository<Long, Ticket>{
    public RepositoryTicket(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public Ticket findOne(Long aLong) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Ticket findOnePers(String id) throws IllegalArgumentException {
        return null;
    }
    @Override
    public Iterable<Ticket> findAllTicket(String aLong) {
        Set<Ticket> comenzi = new HashSet<>();
        String insertSQL= "select * from tickets where username=?";
        try(var connection= DriverManager.getConnection(url,username,password);
            PreparedStatement statementInsert= connection.prepareStatement(insertSQL)) {
            statementInsert.setString(1, aLong);


            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username1 = resultSet.getString("username");
                Long flight_id = resultSet.getLong("flight_id");
                Timestamp data = resultSet.getTimestamp("purchase_time");

                Ticket com = new Ticket(username1,flight_id, data.toLocalDateTime());
                com.setId(id);
                comenzi.add(com);
            }
            return comenzi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Iterable<Ticket> findAllLocatii(String locatie, String locatie2, LocalDate date) {
        return null;
    }


    @Override
    public Iterable<Ticket> findAll() {
        Set<Ticket> comenzi = new HashSet<>();
        String insertSQL= "SELECT * FROM tickets WHERE EXTRACT(YEAR FROM purchase_time) = 2024 AND EXTRACT(MONTH FROM purchase_time) = 1 AND EXTRACT(DAY FROM purchase_time) = 24\n";
        try(var connection= DriverManager.getConnection(url,username,password);
            PreparedStatement statementInsert= connection.prepareStatement(insertSQL)) {



            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username1 = resultSet.getString("username");
                Long flight_id = resultSet.getLong("flight_id");
                Timestamp data = resultSet.getTimestamp("purchase_time");

                Ticket com = new Ticket(username1,flight_id, data.toLocalDateTime());
                com.setId(id);
                comenzi.add(com);
            }
            return comenzi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Ticket> findAll(Long id) {
        return null;
    }

    @Override
    public Ticket save(Ticket entity) {
        String insertFriendshipStatement = "insert into tickets (username,flight_id,purchase_time) values(?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement insertStatement = connection.prepareStatement(insertFriendshipStatement);) {
            insertStatement.setString(1, entity.getUseranme());
            insertStatement.setLong(2, entity.getFlightId());
            insertStatement.setTimestamp(3, Timestamp.valueOf(entity.getPurchaseTime()));
            int response=insertStatement.executeUpdate();
            return response==0?null:entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket delete(Long aLong) {
        return null;
    }

    @Override
    public Ticket update(Ticket entity) {
        return null;
    }
}
