package com.empresa.app;

import com.empresa.controller.Reserva;
import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import com.empresa.model.Tipo;
import java.time.LocalDate;

public class Main{
    public static void main(String[] args) throws Reserva.ClienteNoEncontradoException, Reserva.ReservaNoDisponibleException {
        // Crear un cliente
        Cliente cliente = new Cliente("Juan Perez");
        // Crear habitaciones
        Habitacion habitacion1 = new Habitacion(101, Tipo.DOBLE, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion2 = new Habitacion(102, Tipo.INDIVIDUAL, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion3 = new Habitacion(103, Tipo.SUITE, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        // Crear una reserva
        Reserva reserva2 = new Reserva(LocalDate.of(2025, 4, 3), LocalDate.of(2025, 4, 4));
        // Añadir cliente a la reserva
        reserva2.anadirCliente(cliente);
        // Añadir habitaciones a la reserva
        reserva2.anadirHabitacion(habitacion2);
        reserva2.anadirHabitacion(habitacion1);
        reserva2.anadirHabitacion(habitacion3);
        reserva2.realizarCheckIn(LocalDate.of(2025, 4, 3), habitacion3);
        // Cancelar una reserva
        reserva2.cancelarReserva(habitacion3);

        // Mostrar reservas activas del cliente
        reserva2.mostrarReservasActivasCliente(cliente);
        reserva2.historialClientesHabitaciones();




        // Mostrar resumenes del hotel
        reserva2.resumenClientesHabitacionesReservadas();
        reserva2.resumenHabitaciones();

    }
}