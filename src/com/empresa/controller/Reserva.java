package com.empresa.controller;
//comentario
import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if (cliente.getReservasActivas() >= 3) {
            System.out.println("Error: El cliente ya tiene 3 reservas activas.");
            return;
        }
        this.cliente.add(cliente);
        cliente.incrementarReservasActivas(); // Incrementar el contador de reservas activas
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
    

    // Decrementar el contador de reservas activas
    for (Cliente c : cliente) {
        c.decrementarReservasActivas();
    }

    }

    public void cancelarReserva() {
    if (fechaCheckIn != null) {
        System.out.println("Error: No se puede cancelar la reserva porque ya se ha realizado el check-in.");
        return;
    }

    // Decrementar el contador de reservas activas
    for (Cliente c : cliente) {
        c.decrementarReservasActivas();
    }

    // Liberar las habitaciones 
    for (Habitacion h : habitacion) {
        h.estado = Estado.DISPONIBLE;
    }

    System.out.println("Reserva cancelada exitosamente.");
}
}
