package com.example.examen.observer;


import com.example.examen.request.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
