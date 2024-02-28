package com.example.examen;

import com.example.examen.Service.Service;
import com.example.examen.allert.ClientAlert;
import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;

import com.example.examen.domain.Ticket;
import com.example.examen.observer.Observer;
import com.example.examen.repository.CrudRepository;
import com.example.examen.request.RequestChangeEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements Observer<RequestChangeEvent> {
    @FXML
    TextField textFieldNume;
    @FXML
    Button buttonLogin;
    CrudRepository<Long, Client>repository;
    CrudRepository<Long, Flight> repositoryFlight;
    CrudRepository<Long, Ticket> repositoryTicket;
    Service service;


    public void initialize(){

    }
    public void initModel(){

    }

    public void setService(CrudRepository<Long, Client> repository,   CrudRepository<Long, Flight> repositoryFlight,    CrudRepository<Long, Ticket> repositoryTicket,Service service, Stage primaryStage) {

        this.repository=repository;
        this.repositoryFlight=repositoryFlight;
        this.repositoryTicket=repositoryTicket;
        this.service=service;
        service.addObserver(this);
    }

    @Override
    public void update(RequestChangeEvent requestChangeEvent) {

    }
    public void handleLogin() throws IOException {
        String username = textFieldNume.getText();
        textFieldNume.setText("");
        Client u = service.findPersoana(username);
        if (u == null) {
            ClientAlert.showErrorMessage(null, "Nu exista clientul");
            return;
        }

        FXMLLoader messageLoader = new FXMLLoader();
        messageLoader.setLocation(getClass().getResource("Client.fxml"));
        AnchorPane messageTaskLayout = messageLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(messageTaskLayout));
        stage.setTitle("Client" + u.getName());
        ClientController clientController = messageLoader.getController();
        clientController.setService(repository, repositoryFlight, repositoryTicket, service, stage, u);
        stage.show();
    }

}
