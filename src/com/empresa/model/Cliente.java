package com.empresa.model;
import java.util.UUID;

public class Cliente {
    String idCliente;
    String nombre;

    Cliente(String nombre) {
        this.idCliente = UUID.randomUUID().toString();
        this.nombre = nombre;
    }

}

