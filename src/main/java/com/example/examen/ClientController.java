package com.example.examen;

import com.example.examen.Service.Service;
import com.example.examen.allert.ClientAlert;
import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;
import com.example.examen.domain.Ticket;
import com.example.examen.observer.Observer;
import com.example.examen.repository.CrudRepository;
import com.example.examen.request.RequestChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController implements Observer<RequestChangeEvent>{
    CrudRepository<Long, Client>repository;
    CrudRepository<Long, Flight> repositoryFlight;
    CrudRepository<Long, Ticket> repositoryTicket;
    Service service;
    Client u;
    @FXML
    Button buttonBileteVandute;
    ObservableList<Ticket> model = FXCollections.observableArrayList();
    ObservableList<Flight> model2 = FXCollections.observableArrayList();
    @FXML
    TableView<Ticket> ticketTableView;
    @FXML
    TableColumn<Ticket,String> tableColumnDestinatie;
    @FXML
    TableColumn<Ticket,Long> idFlight;
    @FXML
    TableColumn<Ticket, LocalDateTime> tableColumnData;
    ObservableList<String> items1 = FXCollections.observableArrayList();
    ObservableList<String> items2 = FXCollections.observableArrayList();

    @FXML
    TableView<Flight> ticketTableView1;
    @FXML
    TableColumn<Flight,String> tableColumnDestinatie1;
    @FXML
    TableColumn<Flight,String> idFlight1;
    @FXML
    TableColumn<Flight, LocalDateTime> tableColumnData1;
    @FXML
    TableColumn<Flight, LocalDateTime> tableColumnData2;
    @FXML
    TableColumn<Flight, Integer> tableColumnLocuri;
    @FXML
    ComboBox<String>from;
    @FXML
    ComboBox<String>to;
    @FXML
    DatePicker datePicker;
    @FXML
    Button cumpara;

    public void initialize(){


//        initModel(null);
//        combo.setOnAction(event -> {
//            String selectedLocation = combo.getSelectionModel().getSelectedItem();
//            if (selectedLocation != null) {
//                Location x=service.getLocatie(selectedLocation);
//                initModel(x);
//            }
//        });
        tableColumnDestinatie.setCellValueFactory(new PropertyValueFactory<Ticket, String>("useranme"));
        idFlight.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("flightId"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDateTime>("purchaseTime"));
        tableColumnDestinatie1.setCellValueFactory(new PropertyValueFactory<Flight, String>("from"));
        idFlight1.setCellValueFactory(new PropertyValueFactory<Flight, String>("to"));
        tableColumnData1.setCellValueFactory(new PropertyValueFactory<Flight, LocalDateTime>("departureTime"));
        tableColumnData2.setCellValueFactory(new PropertyValueFactory<Flight, LocalDateTime>("landingTime"));
        tableColumnLocuri.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("seats"));
        ticketTableView.setItems(model);
        ticketTableView1.setItems(model2);

    }
    public void handleBileteVandute() throws IOException {
        FXMLLoader messageLoader = new FXMLLoader();
        messageLoader.setLocation(getClass().getResource("bileteVandute.fxml"));
        AnchorPane messageTaskLayout = messageLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(messageTaskLayout));

        BileteController bileteController= messageLoader.getController();
        bileteController.setService(repository, repositoryFlight, repositoryTicket, service, stage, u);
        stage.show();
    }
    public void setService(CrudRepository<Long, Client> repository, CrudRepository<Long, Flight> repositoryFlight, CrudRepository<Long, Ticket> repositoryTicket, Service service, Stage stage, Client u) {
        this.repository=repository;
        this.repositoryFlight=repositoryFlight;
        this.repositoryTicket=repositoryTicket;
        this.service=service;
        this.u=u;
        service.addObserver(this);
        initModel();
        Iterable<Flight>list=service.getAllFlight();
        for(Flight location1:list){
            items1.add(location1.getFrom());
            items2.add(location1.getTo());
        }
        from.setItems(items1);
        to.setItems(items2);
        initialize();

    }
    public void handleCumpara(){
        Flight flight = ticketTableView1.getSelectionModel().getSelectedItem();
        if (flight == null) {
            ClientAlert.showErrorMessage(null, "Nu ati selectat nimic");
            return;
        }
        if(flight.getSeats()==0){
            ClientAlert.showErrorMessage(null, "Nu mai sunt locuri");
            return;
        }
        service.cumparaBilet(flight,u.getUsername());

    }


    private void initModel() {
        Iterable<Ticket> messages = service.getAllTicjet(u.getUsername());
        List<Ticket> messageTaskList = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(messageTaskList);

        datePicker.setOnAction(event -> {
            String locatie1=from.getValue();
            String locatie2=to.getValue();
            LocalDate selectedDate = datePicker.getValue();
            System.out.println("Selected Date: " + selectedDate);
            if(locatie1=="" || locatie2=="" || selectedDate==null){
                ClientAlert.showErrorMessage(null, "nu ati selectat tot");
            }
            Iterable<Flight> flights = service.getAllFlights( locatie1, locatie2,selectedDate);
            List<Flight> flightList = StreamSupport.stream(flights.spliterator(), false)
                    .collect(Collectors.toList());
            model2.setAll(flightList);
        });

    }
public void initModel2(){
    String locatie1=from.getValue();
    String locatie2=to.getValue();
    LocalDate selectedDate = datePicker.getValue();
    System.out.println("Selected Date: " + selectedDate);
    if(locatie1=="" || locatie2=="" || selectedDate==null){
        ClientAlert.showErrorMessage(null, "nu ati selectat tot");
    }
    Iterable<Flight> flights = service.getAllFlights( locatie1, locatie2,selectedDate);
    List<Flight> flightList = StreamSupport.stream(flights.spliterator(), false)
            .collect(Collectors.toList());
    model2.setAll(flightList);
}

    @Override
    public void update(RequestChangeEvent requestChangeEvent) {
        initModel2();
        initModel();
    }
}
