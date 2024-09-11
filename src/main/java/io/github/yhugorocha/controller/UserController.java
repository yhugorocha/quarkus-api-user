package io.github.yhugorocha.controller;

import io.github.yhugorocha.entity.UserEntity;
import io.github.yhugorocha.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page, @QueryParam("size") @DefaultValue("10") Integer size){
        return Response.ok(userService.findAll(page, size)).build();
    }

    @POST
    @Transactional
    public Response createUser(UserEntity userEntity){
        return Response.ok(userService.createUser(userEntity)).build();
    }

    @Path("/{id}")
    @GET
    public Response createUser(@PathParam("id") UUID id){
        return Response.ok(userService.findById(id)).build();
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response updateUser(@PathParam("id") UUID id, UserEntity userEntity){
        return Response.ok(userService.updateUser(id, userEntity)).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteUser(@PathParam("id") UUID id){
        userService.delete(id);
        return Response.noContent().build();
    }
}
