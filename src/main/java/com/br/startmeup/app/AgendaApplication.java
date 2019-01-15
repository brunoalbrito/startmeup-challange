package com.br.startmeup.app;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class AgendaApplication extends ResourceConfig {
    public AgendaApplication(){
        packages("com.br.startmeup.controllers");
    }
}
