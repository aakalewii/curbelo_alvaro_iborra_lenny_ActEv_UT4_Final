# curbelo_alvaro_iborra_lenny_ActEv_UT4_Final
Actividad evaluativa de la UT4 (Sistema de Reserva de Habitaciones en un Hotel) - Álvaro y Lenny

## Descripción del Proyecto
Este proyecto es un sistema de reserva de habitaciones en un hotel. Permite gestionar clientes, habitaciones y reservas, asegurando que se cumplan ciertas restricciones, como que un cliente no pueda tener más de 3 reservas activas al mismo tiempo. También incluye funcionalidades para realizar check-in, check-out, calcular precios y mostrar resúmenes de reservas y habitaciones.

## Instrucciones de Ejecución
1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/usuario/curbelo_alvaro_iborra_lenny_ActEv_UT4_Final.git

## Ejemplo de uso

Cliente cliente1 = new Cliente("Juan Perez");
Cliente cliente2 = new Cliente("Maria Lopez");

Habitacion habitacion1 = new Habitacion(101, Tipo.DOBLE, Estado.DISPONIBLE, "Habitación sencilla con cama doble.");
Habitacion habitacion2 = new Habitacion(102, Tipo.INDIVIDUAL, Estado.DISPONIBLE, "Habitación individual con baño privado.");

Reserva reserva1 = new Reserva(LocalDate.of(2025, 4, 3), LocalDate.of(2025, 4, 5));
reserva1.anadirCliente(cliente1);
reserva1.anadirHabitacion(habitacion1);

reserva1.realizarCheckIn(LocalDate.of(2025, 4, 3), habitacion1);
reserva1.realizarCheckOut(LocalDate.of(2025, 4, 5), habitacion1);

Reserva reserva2 = new Reserva(LocalDate.of(2025, 4, 6), LocalDate.of(2025, 4, 8));
Reserva reserva3 = new Reserva(LocalDate.of(2025, 4, 9), LocalDate.of(2025, 4, 11));
Reserva reserva4 = new Reserva(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 14));

reserva2.anadirCliente(cliente1);
reserva3.anadirCliente(cliente1);

// Esto lanzará una excepción porque el cliente ya tiene 3 reservas activas
reserva4.anadirCliente(cliente1);

reserva1.resumenClientesHabitacionesReservadas();
reserva1.resumenHabitaciones();