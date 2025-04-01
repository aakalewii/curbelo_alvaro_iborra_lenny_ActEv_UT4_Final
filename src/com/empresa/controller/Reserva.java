package com.empresa.controller;
//comentario
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.empresa.model.Cliente;
import com.empresa.model.Habitacion;

public class Reserva {
    private String idReserva;
    private List<Cliente> cliente;
    private List<Habitacion> habitacion;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private double precioTotal;

    public Reserva(List<Cliente> cliente, List<Habitacion> habitacion, LocalDate fechaCheckIn,
            LocalDate fechaCheckOut, double precioTotal) {
        this.idReserva = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.precioTotal = precioTotal;
    }

    public void anadirCliente(Cliente cliente) {
        this.cliente.add(cliente);
    }

    public void anadirHabitacion(Habitacion habitacion) {
        this.habitacion.add(habitacion);
    }


    public void realizarCheckIn(LocalDate fecha) {
        this.fechaCheckIn = fecha;
        System.out.println("Check-in realizado el: " + fechaCheckIn);
    }

    public void realizarCheckOut(LocalDate fecha) {
        if (fechaCheckIn == null) {
            System.out.println("Error: No se puede realizar el check-out sin haber hecho el check-in.");
            return;
        }
        if (fecha.isBefore(fechaCheckIn)) {
            System.out.println("Error: La fecha de check-out no puede ser anterior a la fecha de check-in.");
            return;
        }
        this.fechaCheckOut = fecha;
        System.out.println("Check-out realizado el: " + fechaCheckOut);
    }

}
