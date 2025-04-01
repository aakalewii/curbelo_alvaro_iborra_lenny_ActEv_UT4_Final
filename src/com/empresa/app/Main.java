package com.empresa.app;

import java.time.LocalDate;

import com.empresa.controller.Reserva;
import com.empresa.model.Cliente;
import com.empresa.model.Estado;
import com.empresa.model.Habitacion;
import com.empresa.model.Tipo;

public class Main{
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Juan Perez");
        Habitacion habitacion = new Habitacion(101, Tipo.SENCILLA, 100.0, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");

        Reserva reserva = new Reserva(cliente, habitacion, LocalDate.now(), LocalDate.now().plusDays(2), 200.0);

        reserva.anadirCliente(cliente);
        reserva.anadirHabitacion(habitacion);

        reserva.realizarCheckOut(LocalDate.now());
    }
}