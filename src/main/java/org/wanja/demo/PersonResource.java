package org.wanja.demo;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

@Path("/persons")
public class PersonResource {
    
    @GET
    public Uni<List<Person>> findAll() {
        return Person.listAll();
    }

    @POST
    //@ReactiveTransactional
    public Uni<Person> createNew(Person p) {
        return Panache.withTransaction(p::persist)            
            .onItem().ifNotNull().invoke(e -> {
                Log.info(((Person )e).id);
            })
//            .transform(per -> Log.info("TRANS " + per))
            .subscribe()
            .with(per -> Log.info("SUB-WITH " + per));
    }


}
