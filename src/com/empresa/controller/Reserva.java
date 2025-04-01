package com.empresa.controller;
//comentario
import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import com.empresa.model.Tipo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reserva {
    private String idReserva;
    private List<Cliente> cliente;
    private List<Habitacion> habitacion;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private double precioTotal;

    public Reserva(Cliente cliente, Habitacion habitacion, LocalDate fechaCheckIn,
            LocalDate fechaCheckOut, double precioTotal) {
        this.idReserva = UUID.randomUUID().toString();
        this.cliente = new ArrayList<>(); 
        this.cliente.add(cliente);
        this.habitacion = new ArrayList<>();
        this.habitacion.add(habitacion);
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.precioTotal = precioTotal;
    }

    public void anadirCliente(Cliente cliente) {
        if (cliente.getReservasActivas() >= 3) {
            System.out.println("Error: El cliente ya tiene 3 reservas activas.");
            return;
        }
        this.cliente.add(cliente);
        // Incrementar el contador de reservas activas para todas las habitaciones de la reserva
        for (Habitacion habitacion : this.habitacion) {
            if (habitacion.getEstado() == Estado.DISPONIBLE) {
                cliente.incrementarReservasActivas(habitacion);
            } else {
                System.out.println("La habitación " + habitacion.getNumero() + " no está disponible.");
            }
        }
    }

    public void anadirHabitacion(Habitacion habitacion) {
        if (habitacion.getEstado() != Estado.DISPONIBLE) {
            System.out.println("Error: La habitación " + habitacion.getNumero() + " no está disponible.");
        } else {
            this.habitacion.add(habitacion);
            System.err.println("Habitación " + habitacion.getNumero() + " añadida a la reserva.");
            habitacion.reservar();
        }
    }


    public void realizarCheckIn(LocalDate fecha, Habitacion habitacion) {
        this.fechaCheckIn = fecha;
        System.out.println("Check-in realizado el: " + fechaCheckIn);
        habitacion.ocupar();
    }

    public void realizarCheckOut(LocalDate fecha, Habitacion habitacion) {
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
        habitacion.liberar();
    
        // Decrementar el contador de reservas activas
        for (Cliente c : cliente) {
            c.decrementarReservasActivas();
        }
    }

    public Habitacion buscarnumero(int numero) {
        for (Habitacion habitacion : this.habitacion) {
            if (habitacion.getNumero() == numero) {
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
                if (habitacion.getTipo() == enumTipo) {
                    System.out.println("Habitación " + tipo + " encontrada: " + habitacion.getNumero());
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
                if (habitacion.getEstado() == enumEstado) {
                    System.out.println("Habitación " + estado + " encontrada: " + habitacion.getNumero());
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

