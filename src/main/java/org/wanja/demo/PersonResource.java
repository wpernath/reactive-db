package org.wanja.demo;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    
    @GET
    public Uni<List<Person>> findAll() {
        return Person.listAll();
    }

    @POST
    @ReactiveTransactional
    public Uni<Person> createNew(Person p) {
        return p.persist();
    }


}
