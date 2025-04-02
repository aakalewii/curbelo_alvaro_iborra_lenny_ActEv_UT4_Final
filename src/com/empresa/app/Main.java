package com.empresa.app;

import com.empresa.controller.Reserva;
import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import com.empresa.model.Tipo;
import java.time.LocalDate;

public class Main{
    public static void main(String[] args) throws Reserva.ClienteNoEncontradoException, Reserva.ReservaNoDisponibleException {
        Cliente cliente = new Cliente("Juan Perez");
        Cliente cliente2 = new Cliente("Maria Lopez");
        Habitacion habitacion = new Habitacion(101, Tipo.INDIVIDUAL, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion2 = new Habitacion(102, Tipo.INDIVIDUAL, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");


        Reserva reserva = new Reserva(cliente, habitacion, LocalDate.of(2025, 4, 10), LocalDate.now().plusDays(2), 200.0);
        Reserva reserva2 = new Reserva(cliente, habitacion, LocalDate.of(2025, 4, 2), LocalDate.of(2025, 4, 7), 200.0);
        Reserva reserva3 = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);
        Reserva reserva4 = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);




        reserva2.anadirCliente(cliente);
        reserva2.anadirHabitacion(habitacion);
        reserva2.anadirHabitacion(habitacion2);

        reserva2.calcularPrecio(reserva2);


        
    }
}