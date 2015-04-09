package com.yammer.litdemo.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.litdemo.SampleProduct;
import com.yammer.litdemo.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SampleProductResource {

    static final ObjectMapper mapper = new ObjectMapper();
    private UserDao userDao;

    public SampleProductResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") String id) {
        try {
            SampleProduct product = userDao.getById(Long.parseLong(id));
            return Response.ok(product).build();
        } catch (UserDao.ProductDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addProduct(String json) {
        SampleProduct product = null;
        try {
            product = mapper.readValue(json, SampleProduct.class);
        } catch (IOException e) {
            // This shouldn't happen unless json is bad
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(product).build();
        }

        try {
            userDao.add(product);
            return Response.status(Response.Status.CREATED).entity("{\"message\": \"created product\"}").build();
        } catch (UserDao.ProductExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\": \"already exists\"}").build();
        }
    }

    @PUT
    public Response updateProduct(String json) {
        SampleProduct product = null;
        try {
            product = mapper.readValue(json, SampleProduct.class);
        } catch (IOException e) {
            // This shouldn't happen unless json is bad
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(product).build();
        }

        try {
            userDao.update(product);
            return Response.status(Response.Status.ACCEPTED).entity("{\"message\": \"updated product\"}").build();
        } catch (UserDao.ProductDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        try {
            userDao.deleteById(Long.parseLong(id));
        } catch (UserDao.ProductDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(id).build();
    }
}
