package com.empresa.controller;

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

    
    // Constructor de la clase Reserva
    public Reserva(LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        long dias = fechaCheckOut.toEpochDay() - fechaCheckIn.toEpochDay(); // Duración de la estancia en días
        if (dias > 90) {
            throw new IllegalArgumentException("La duración de la estancia debe ser menor a 90 días.");
        }

        this.idReserva = UUID.randomUUID().toString();
        this.cliente = new ArrayList<>(); 
        this.habitacion = new ArrayList<>();
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.precioTotal = precioTotal;
    }
    
    //Funcion añadir cliente con excepcion
    public void anadirCliente(Cliente cliente) throws ClienteNoEncontradoException {
        if (cliente == null) {
            throw new ClienteNoEncontradoException("El cliente no puede ser nulo.");
                }
        if (cliente.getReservasActivas() >= 3) {
                throw new ClienteNoEncontradoException("El cliente ya tiene 3 reservas activas.");
        }
            this.cliente.add(cliente);
            cliente.incrementarReservasActivas(); // Incrementar el contador de reservas activas
            System.out.println("El Cliente: "+cliente.getIdCliente()+"("+cliente.getNombre()+"), añadido a la reserva: " + idReserva);
        }
    
        // Clase interna para la excepción personalizada
        public static class ClienteNoEncontradoException extends Exception {
            public ClienteNoEncontradoException(String mensaje) {
                super(mensaje);
            }
        }
    
    // Método para añadir una habitación a la reserva
    public void anadirHabitacion(Habitacion habitacion) throws ReservaNoDisponibleException {
        if (habitacion.getEstado() != Estado.DISPONIBLE) {
            throw new ReservaNoDisponibleException("La habitación "+ habitacion.getNumero()+" no está disponible.");
        } else {
            this.habitacion.add(habitacion);
            System.err.println("Habitación " + habitacion.getNumero() + " añadida a la reserva.");
            habitacion.reservar();
        }
    }

    // Método para cancelar una reserva
    public void cancelarReserva(Habitacion habitacion) {    
        if (habitacion.getEstado() == Estado.OCUPADO) {
            System.out.println("No puedes cancelar una reserva de una habitación si ya ha pasado el Check-In.");
        }else if (habitacion.getEstado() != Estado.RESERVADO) {
            System.out.println("La habitación " + habitacion.getNumero() + " no está reservada.");
        } else {
            habitacion.cancelar();
            System.out.println("Reserva cancelada para la habitación " + habitacion.getNumero());
            calcularPrecio(this); // Calcular el precio total de la reserva
            for (Cliente c : this.cliente) {
                c.decrementarReservasActivas(); // Decrementar el contador de reservas activas
            }
        }
        
    }

    // Clase interna para la excepción 
    public static class ReservaNoDisponibleException extends Exception {
        public ReservaNoDisponibleException(String mensaje) {
            super(mensaje);
        }
    }

    // Método para realizar el check-in
    public void realizarCheckIn(LocalDate fecha, Habitacion habitacion) {
        if (fecha.isAfter(fechaCheckOut)) {
            throw new IllegalArgumentException("La fecha de check-in no puede ser posterior a la fecha de check-out.");
        }
        if (fecha.isBefore(this.fechaCheckIn)) {
            throw new IllegalArgumentException("La fecha de check-in no puede ser anterior a la fecha de inicio de la reserva.");
        }
    
        this.fechaCheckIn = fecha;
        System.out.println("Check-in realizado el: " + fechaCheckIn);
        habitacion.ocupar();
    }

    // Método para realizar el check-out
    public void realizarCheckOut(LocalDate fecha, Habitacion habitacion) {
        if (fechaCheckIn == null) {
            throw new IllegalArgumentException("No se puede realizar el check-out sin haber hecho el check-in.");
        }
        if (fecha.isBefore(fechaCheckIn)) {
            throw new IllegalArgumentException("La fecha de check-out no puede ser anterior a la fecha de check-in.");
        }
        if (fecha.isAfter(fechaCheckOut)) {
            throw new IllegalArgumentException("La fecha de check-out no puede ser posterior a la fecha de check-out de la reserva.");
        }
        this.fechaCheckOut = fecha;
        System.out.println("Check-out realizado el: " + fechaCheckOut);
        habitacion.liberar();
    
        // Decrementar el contador de reservas activas
        for (Cliente c : cliente) {
            c.decrementarReservasActivas();
        }
    }

    // Método para buscar una habitación por número
    public Habitacion buscarnumero(int numero) {
        for (Habitacion habitacion : this.habitacion) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        System.out.println("Habitación no encontrada: " + numero);
        return null;
    }

    // Método para buscar una habitación por tipo
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

    

    // Método para buscar una habitación por estado
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

    // Método para mostrar las reservas activas de un cliente
    public void mostrarReservasActivasCliente(Cliente cliente) {
        List<Integer> habitacionesReservadas = new ArrayList<>();
        for (Habitacion habitacion : this.habitacion) {
            if (habitacion.getEstado() == Estado.RESERVADO || habitacion.getEstado() == Estado.OCUPADO) {
                habitacionesReservadas.add(habitacion.getNumero());
            }
        }
        if (habitacionesReservadas.isEmpty()) {
            System.out.println("El cliente " + cliente.getNombre() + " no tiene reservas activas.");
        } else {
            System.out.println("El cliente " + cliente.getNombre() + " tiene reservadas las habitaciones: " + habitacionesReservadas);
        }
    }

    // Método para calcular el precio total de la reserva
    public void calcularPrecio(Reserva reserva) {
        long dias = reserva.fechaCheckOut.toEpochDay() - reserva.fechaCheckIn.toEpochDay(); // Duración de la estancia en días
        double total = 0; // Reiniciar el total

        for (Habitacion habitacion : reserva.habitacion) {
            // Solo calcular el precio de habitaciones reservadas u ocupadas
            if (habitacion.getEstado() == Estado.RESERVADO || habitacion.getEstado() == Estado.OCUPADO) {
                switch (habitacion.getTipo()) {
                    case INDIVIDUAL:
                        total += 50 * dias; // Precio fijo para habitaciones individuales
                        break;
                    case DOBLE:
                        total += 80 * dias; // Precio fijo para habitaciones dobles
                        break;
                    case SUITE:
                        total += 150 * dias; // Precio fijo para suites
                        break;
                    default:
                        System.out.println("Tipo de habitación no válido: " + habitacion.getTipo());
                }
            } else {
                System.out.println("La habitación " + habitacion.getNumero() + " no se incluye en el cálculo porque está en estado: " + habitacion.getEstado());
            }
        }

        reserva.precioTotal = total; // Actualizar el precio total de la reserva
    System.out.println("El precio total de la reserva es: " + total);
    }

    public void calcularPrecioTotal(){
        calcularPrecio(this);
    }

    // Método para mostrar el resumen de clientes con habitaciones reservadas
    public void resumenClientesHabitacionesReservadas(){
        System.out.println("Resumen de Clientes con habitaciones reservadas:");
        for (Cliente cliente : this.cliente) {
            System.out.println("Cliente: " + cliente.getNombre() + ", ID: " + cliente.getIdCliente());
            for (Habitacion habitacion : this.habitacion) {
                if (habitacion.getEstado() == Estado.RESERVADO) {
                    System.out.println("Habitación " + habitacion.getNumero() + ", Estado: " + habitacion.getEstado());
                }
            }
        }
    }

    // Método para mostrar el historial de clientes con sus reservas
    public void historialClientesHabitaciones(){
        System.out.println("Historial de Clientes con sus reservas:");
        for (Cliente cliente : this.cliente) {
            System.out.println("Cliente: " + cliente.getNombre() + ", ID: " + cliente.getIdCliente());
            for (Habitacion habitacion : this.habitacion) {
                    System.out.println("Habitación " + habitacion.getNumero()+", Fecha Check-In: " + fechaCheckIn + ", Fecha Check-Out: " + fechaCheckOut);
                
            }
        }
    }

    // Método para mostrar el resumen de habitaciones
    public void resumenHabitaciones() {
        System.out.println("Resumen de Habitaciones:");
        for (Habitacion habitacion : this.habitacion) {
            System.out.println("Habitación " + habitacion.getNumero() + 
                               ", Tipo: " + habitacion.getTipo() + 
                               ", Estado: " + habitacion.getEstado() + 
                               ", Descripción: " + habitacion.getDescripcion());
        }
    }

    

}



