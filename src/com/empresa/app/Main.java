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
        Cliente cliente2 = new Cliente("Maria Lopez");
        // Crear habitaciones
        Habitacion habitacion1 = new Habitacion(101, Tipo.DOBLE, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion2 = new Habitacion(102, Tipo.INDIVIDUAL, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion3 = new Habitacion(103, Tipo.SUITE, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        // Crear una reserva
        Reserva reserva1 = new Reserva(LocalDate.of(2025, 4, 3), LocalDate.of(2025, 4, 5));
        Reserva reserva2 = new Reserva(LocalDate.of(2025, 4, 3), LocalDate.of(2025, 4, 8));
        Reserva reserva3 = new Reserva(LocalDate.of(2025, 4, 9), LocalDate.of(2025, 4, 11));
        Reserva reserva4 = new Reserva(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 14));
        // Execpcion de duración menos de 90 días, (quitar el comentario)
        //Reserva reserva3 = new Reserva(LocalDate.of(2025, 4, 3), LocalDate.of(2025, 7, 3));
        
        reserva1.anadirCliente(cliente2);
        reserva2.anadirCliente(cliente2);
        reserva3.anadirCliente(cliente2);
        
        // Intentar añadir el cliente a una cuarta reserva (esto debería lanzar una excepción)
        //reserva4.anadirCliente(cliente2);
        

        System.out.println("------------------");
        // Añadir cliente a la reserva
        reserva2.anadirCliente(cliente);
        // Añadir habitaciones a la reserva

        //Exepcion para no añadir un cliente nulo (quitar el comentario)
        // reserva2.anadirCliente(null);

        System.out.println("------------------");

        reserva2.anadirHabitacion(habitacion2);
        reserva2.anadirHabitacion(habitacion1);
        reserva2.anadirHabitacion(habitacion3);
        reserva2.realizarCheckIn(LocalDate.of(2025, 4, 3), habitacion3);
        
        //Realizar el check-in antes que la fecha de la reserva (quitar el comentario)
        //reserva2.realizarCheckIn(LocalDate.of(2023, 4, 2), habitacion1);

        //Realizar el check-out antes que la fecha de la reserva (quitar el comentario)
        //reserva2.realizarCheckOut(LocalDate.of(2025, 4, 2), habitacion1);
        
        System.out.println("------------------");
        // Cancelar una reserva
        reserva2.cancelarReserva(habitacion3);//Execepciones para no cancelar una reserva ocupada
        reserva2.cancelarReserva(habitacion1);

        System.out.println("------------------");

        // Mostrar resumenes del hotel
        reserva2.resumenClientesHabitacionesReservadas();
        reserva2.resumenHabitaciones();

        System.out.println("------------------");
        // Mostrar reservas activas del cliente
        reserva2.mostrarReservasActivasCliente(cliente);
        reserva2.historialClientesHabitaciones();

        System.out.println("------------------");

        // Calcular el precio total de la reserva
        reserva2.calcularPrecioTotal();


        //ESTO SOLO ES UN EJEMPLO, HAY MÁS POSIBLES EXEPCIONES QUE SE PUEDEN HACER EN EL PROYECTO.
    }
}