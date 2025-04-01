package com.empresa.model;
import java.util.UUID;

public class Cliente {
    String idCliente;
    String nombre;
    private int reservasActivas;


    public Cliente(String nombre) {
        this.idCliente = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.reservasActivas = 0;
    }

    public int getReservasActivas() {
        return reservasActivas;
    }

    public void incrementarReservasActivas() {
        this.reservasActivas++;
    }

    public void decrementarReservasActivas() {
        if (this.reservasActivas > 0) {
            this.reservasActivas--;
        }
    }
}

