package com.empresa.model;
//pollita
import java.util.UUID;

public class Cliente {
    String idCliente;
    String nombre;

    Cliente(String nombre) {
        this.idCliente = UUID.randomUUID().toString();
        this.nombre = nombre;
    }

}

