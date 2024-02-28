package com.example.examen.repository;

import com.example.examen.domain.Flight;
import com.example.examen.domain.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ReositoryFlight extends AbstractDataBaseRepository<Long, Flight>{
    public ReositoryFlight(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public Flight findOne(Long aLong) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Flight findOnePers(String id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Flight> findAll() {

        Set<Flight> comenzi = new HashSet<>();
        String insertSQL= "SELECT *\n" +
                "FROM flights f\n";
        try(var connection= DriverManager.getConnection(url,username,password);
            PreparedStatement statementInsert= connection.prepareStatement(insertSQL)) {



            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username1 = resultSet.getString("flight_from");
                String flight_id = resultSet.getString("flight_to");

                Timestamp departure = resultSet.getTimestamp("departure_time");
                Timestamp land = resultSet.getTimestamp("landing_time");
                Integer seats = resultSet.getInt("seats");

                Flight com = new Flight(username1,flight_id, departure.toLocalDateTime(),land.toLocalDateTime(),seats);
                com.setId(id);
                comenzi.add(com);
            }
            return comenzi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Flight> findAll(Long id) {
        return null;
    }

    @Override
    public Iterable<Flight> findAllTicket(String aLong) {
        return null;
    }

    @Override
    public Iterable<Flight> findAllLocatii(String locatie, String locatie2, LocalDate date) {
        Set<Flight> comenzi = new HashSet<>();
        String insertSQL= "SELECT *\n" +
                "FROM flights f\n" +

                "WHERE f.flight_from = ?\n" +
                "  AND f.flight_to = ?\n" +
                "  AND departure_time::date= ?;";
        try(var connection= DriverManager.getConnection(url,username,password);
            PreparedStatement statementInsert= connection.prepareStatement(insertSQL)) {
            statementInsert.setString(1, locatie);
            statementInsert.setString(2, locatie2);
            statementInsert.setDate(3, Date.valueOf(date));
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username1 = resultSet.getString("flight_from");
                String flight_id = resultSet.getString("flight_to");
                //Timestamp data = resultSet.getTimestamp("purchase_time");
                Timestamp departure = resultSet.getTimestamp("departure_time");
                Timestamp land = resultSet.getTimestamp("landing_time");
                Integer seats = resultSet.getInt("seats");

                Flight com = new Flight(username1,flight_id, departure.toLocalDateTime(),land.toLocalDateTime(),seats);
                com.setId(id);
                comenzi.add(com);
            }
            return comenzi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    @Override
    public Flight delete(Long aLong) {
        return null;
    }

    @Override
    public Flight update(Flight entity) {
        String insertFriendshipStatement = "update flights set seats=? where id=?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement insertStatement = connection.prepareStatement(insertFriendshipStatement);) {
            insertStatement.setInt(1, entity.getSeats());
            insertStatement.setLong(2, entity.getId());
            int response=insertStatement.executeUpdate();
            return response==0?null:entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
