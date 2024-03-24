package net.uzaki.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/users")
@ApplicationScoped
public class UserResource {
    protected UserService service;

    public UserResource(){}

    public @Inject UserResource(UserRepositoryInterface userRepo) {
        this.service = new UserService(userRepo);
    }

    public UserResource(UserService userService) {
        this.service = userService;
    }

    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return this.service.getAllUsersJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") String id) {
        String result = this.service.getUserJSON(id);

        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }

}
