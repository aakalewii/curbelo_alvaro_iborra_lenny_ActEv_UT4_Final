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
        Habitacion habitacion1 = new Habitacion(101, Tipo.DOBLE, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion2 = new Habitacion(102, Tipo.INDIVIDUAL, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");

        Reserva reserva = new Reserva(LocalDate.of(2025, 4, 3), LocalDate.of(2025, 4, 6));
        Reserva reserva2 = new Reserva(LocalDate.of(2025, 4, 2), LocalDate.of(2025, 4, 4));
        reserva2.anadirCliente(cliente);
        reserva2.anadirHabitacion(habitacion2);


        reserva2.reservasActivas(cliente);

    }
}