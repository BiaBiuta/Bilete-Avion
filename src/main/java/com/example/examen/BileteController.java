package com.example.examen;

import com.example.examen.Service.Service;
import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;
import com.example.examen.domain.Ticket;
import com.example.examen.observer.Observer;
import com.example.examen.repository.CrudRepository;
import com.example.examen.request.RequestChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BileteController implements Observer<RequestChangeEvent> {
    CrudRepository<Long, Client>repository;
    CrudRepository<Long, Flight> repositoryFlight;
    CrudRepository<Long, Ticket> repositoryTicket;
    Service service;
    Client u;
    ObservableList<Ticket> model = FXCollections.observableArrayList();
    @FXML
    TableView<Ticket> ticketTableView1;
    @FXML
    TableColumn<Ticket,String> tableColumnDestinatie1;
    @FXML
    TableColumn<Ticket,Long> idFlight1;
    @FXML
    TableColumn<Ticket, LocalDateTime> tableColumnData1;


    public void initialize(){
        tableColumnDestinatie1.setCellValueFactory(new PropertyValueFactory<Ticket, String>("useranme"));
        idFlight1.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("flightId"));
        tableColumnData1.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDateTime>("purchaseTime"));
        ticketTableView1.setItems(model);

    }
    public void setService(CrudRepository<Long, Client> repository, CrudRepository<Long, Flight> repositoryFlight, CrudRepository<Long, Ticket> repositoryTicket, Service service, Stage stage, Client u) {
        this.repository=repository;
        this.repositoryFlight=repositoryFlight;
        this.repositoryTicket=repositoryTicket;
        this.service=service;
        this.u=u;
        initModel();
    }

    private void initModel() {
        Iterable<Ticket> messages = service.getAllTicjetData();
        List<Ticket> messageTaskList = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(messageTaskList);
    }

    @Override
    public void update(RequestChangeEvent requestChangeEvent) {
        initModel();
    }
}
