package com.empresa.controller;
//comentario
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import com.empresa.model.Tipo;

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

    public Habitacion buscarnumero(int numero) {
        for (Habitacion habitacion : this.habitacion) {
            if (habitacion.numero == numero) {
                return habitacion;
            }
        }
        System.out.println("Habitación no encontrada: " + numero);
        return null;
    }

    public Habitacion buscarTipo(String tipo){
        try{
            Tipo enumTipo = Tipo.valueOf(tipo.toUpperCase());
            for (Habitacion habitacion : this.habitacion) {
                if (habitacion.tipo == enumTipo) {
                    System.out.println("Habitación" + tipo + " encontrada: " + habitacion.numero);
                    return habitacion;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de habitación no válido: " + tipo);
        }
        System.out.println("Habitación "+tipo+" no encontrada.");
        return null;
    }

    public Habitacion buscarEstado(String estado){
        try{
            Estado enumEstado = Estado.valueOf(estado.toUpperCase());
            for (Habitacion habitacion : this.habitacion) {
                if (habitacion.estado == enumEstado) {
                    System.out.println("Habitación" + estado + " encontrada: " + habitacion.numero);
                    return habitacion;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Estado de habitación no válido: " + estado);
        }
        System.out.println("Habitación "+estado+" no encontrada.");
        return null;
    }

}
