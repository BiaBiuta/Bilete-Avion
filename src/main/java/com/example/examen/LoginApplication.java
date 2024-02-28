package com.example.examen;

import com.example.examen.Service.Service;
import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;

import com.example.examen.domain.Ticket;
import com.example.examen.repository.CrudRepository;
import com.example.examen.repository.ReositoryFlight;
import com.example.examen.repository.Repository;
import com.example.examen.repository.RepositoryTicket;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApplication extends Application {
    CrudRepository<Long, Client>repository;
    CrudRepository<Long, Flight> repositoryFlight;
    CrudRepository<Long, Ticket> repositoryTicket;
    Service service;

    @Override
    public void start(Stage primaryStage) throws Exception {

        repository=new Repository("jdbc:postgresql://localhost:5432/examen","postgres","liana23062003");
        repositoryFlight=new ReositoryFlight("jdbc:postgresql://localhost:5432/examen","postgres","liana23062003");
        repositoryTicket=new RepositoryTicket("jdbc:postgresql://localhost:5432/examen","postgres","liana23062003");
        service=new Service(repository,repositoryTicket,repositoryFlight);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));
        AnchorPane anchorPane =fxmlLoader.load();
        primaryStage.setScene(new javafx.scene.Scene(anchorPane));
        LoginController loginController= fxmlLoader.getController();
        loginController.setService(repository,repositoryFlight,  repositoryTicket,service,primaryStage);
        primaryStage.show();

    }
}
