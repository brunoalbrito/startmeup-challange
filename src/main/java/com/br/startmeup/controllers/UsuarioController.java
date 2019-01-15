package com.br.startmeup.controllers;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@Path("hello")
public class UsuarioController extends Application {

    @GET
    public String get(){
        System.out.println("Saiu aqui");
        return "funcionou";
    }
}
