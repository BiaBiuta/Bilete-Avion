package com.example.examen.Service;

import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;

import com.example.examen.domain.Ticket;
import com.example.examen.observer.Observable;
import com.example.examen.observer.Observer;
import com.example.examen.repository.CrudRepository;
import com.example.examen.repository.Repository;
import com.example.examen.request.RequestChangeEvent;
import com.example.examen.request.StatusChange;
import javafx.scene.control.DatePicker;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<RequestChangeEvent> {
    public List<Observer<RequestChangeEvent>> observers=new ArrayList<>();
    CrudRepository<Long, Flight> repositoryFlight;
    CrudRepository<Long, Ticket> repositoryTicket;
    CrudRepository<Long, Client> repository;


    public Service(CrudRepository<Long, Client> repository,CrudRepository<Long, Ticket> repositoryTicket,CrudRepository<Long, Flight> repositoryFlight) {
        this.repository = repository;
        this.repositoryTicket=repositoryTicket;
        this.repositoryFlight=repositoryFlight;

    }

    @Override
    public void addObserver(Observer<RequestChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<RequestChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(RequestChangeEvent t) {
        observers.forEach(x->x.update(t));
    }

    public Client findPersoana(String username) {
        return repository.findOnePers(username);
    }

    public Iterable<Ticket> getAllTicjet(String username) {
        return repositoryTicket.findAllTicket(username);
    }

    public Iterable<Ticket> getAllTicjetData() {
        return repositoryTicket.findAll();
    }
    public Iterable<Flight> getAllFlights(String locatie1, String locatie2, LocalDate date) {
        return repositoryFlight.findAllLocatii(locatie1,locatie2,date);
    }

    public Iterable<Flight> getAllFlight() {
        return repositoryFlight.findAll();
    }

    public Ticket cumparaBilet(Flight flight, String username) {

        Ticket ticket =new Ticket(username,flight.getId(), LocalDateTime.now());
        flight.setSeats(flight.getSeats()-1);
        repositoryFlight.update(flight);
        notifyObservers(new RequestChangeEvent(StatusChange.Cumparat,flight));
        return repositoryTicket.save(ticket);
    }
}
