package com.nigames.jbdd.service.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.jersey.listing.ApiListingResource;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 07.02.2015.
 */
@Component
@Path("/api-docs")
@Api("/api-docs")
@Produces(MediaType.APPLICATION_JSON)
public class ApiListingResourceJSON extends ApiListingResource {

}
