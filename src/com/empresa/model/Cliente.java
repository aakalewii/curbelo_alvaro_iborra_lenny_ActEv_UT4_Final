package com.empresa.model;
import java.util.UUID;

public class Cliente {
    String idCliente;
    String nombre;
    private int reservasActivas;

    // Constructor
    public Cliente(String nombre) {
        this.idCliente = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.reservasActivas = 0;
    }


    public int getReservasActivas() {
        return reservasActivas;
    }

    // Método para incrementar el contador de reservas activas
    public void incrementarReservasActivas() {
        this.reservasActivas++;
    }

    // Método para decrementar el contador de reservas activas
    public void decrementarReservasActivas() {
        if (this.reservasActivas > 0) {
            this.reservasActivas--;
        }
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    
}

