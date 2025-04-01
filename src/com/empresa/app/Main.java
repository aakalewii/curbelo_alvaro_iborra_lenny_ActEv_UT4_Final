package com.empresa.app;

import com.empresa.controller.Reserva;
import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import com.empresa.model.Tipo;
import java.time.LocalDate;

public class Main{
    public static void main(String[] args) throws Reserva.ClienteNoEncontradoException {
        Cliente cliente = new Cliente("Juan Perez");
        Habitacion habitacion = new Habitacion(101, Tipo.SENCILLA, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
        Habitacion habitacion2 = new Habitacion(102, Tipo.SENCILLA, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");


        Reserva reserva = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);
        Reserva reserva2 = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);
        Reserva reserva3 = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);
        Reserva reserva4 = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);



        reserva.anadirCliente(null);

        reserva2.anadirCliente(cliente);
        reserva2.anadirHabitacion(habitacion);

        reserva3.anadirCliente(cliente);
        reserva3.anadirHabitacion(habitacion);

        reserva4.anadirCliente(cliente);
        reserva4.anadirHabitacion(habitacion2);




        reserva.buscarnumero(101);
        reserva.realizarCheckIn(LocalDate.now(), habitacion);
        reserva.buscarEstado("OCUPADO");
    }
}