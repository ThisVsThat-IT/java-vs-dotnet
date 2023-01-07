package org.example;

import java.util.List;

import org.example.model.BirthModel;
import org.example.service.BirthEjbService;

import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/birth")
public class BirthResource {
	
	@Inject
	private BirthEjbService birthEjbService;

    @PUT
    @Path("/calculate/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BirthModel> calculateAgeforAll() {
    	birthEjbService.calculateAndSaveAgeForAll();
    	return birthEjbService.findAll();
    }
}
