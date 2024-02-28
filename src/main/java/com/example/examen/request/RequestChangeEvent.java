package com.example.examen.request;


import com.example.examen.domain.Flight;

public class RequestChangeEvent implements Event {
    private StatusChange type;
    private Flight task;
    private Flight oldTask;
    public RequestChangeEvent(StatusChange type, Flight task) {
        this.task=task;
        this.type=type;
    }

    public RequestChangeEvent(StatusChange type, Flight task, Flight oldTask) {

        this.type = type;
        this.task = task;
        this.oldTask = oldTask;
    }

    public Flight getTask() {
        return task;
    }
}
