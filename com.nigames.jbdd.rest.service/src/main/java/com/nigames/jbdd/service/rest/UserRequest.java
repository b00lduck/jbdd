package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.UserRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.service.service.player.PlayerService;
import com.nigames.jbdd.service.service.user.UserService;
import com.nigames.jbdd.statics.Constants;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/user")
public class UserRequest extends AbstractRequest<User> implements UserRequestInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @SuppressWarnings("SuspiciousGetterSetter")
    @Override
    protected UserService getService() {
        return userService;
    }

    @Override
    @PUT
    @Path("/{id}")
    public User update(@PathParam("id") final long id,
                       @QueryParam("sendpw") final boolean sendPassword, final User dto) {
        return userService.update(id, dto, sendPassword);
    }

    @Override
    @POST
    @Path("/")
    public User create(@QueryParam("sendpw") final boolean sendPassword, final User dto) {
        return userService.create(dto, sendPassword);
    }

    @Override
    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") final long id) {
        return userService.findById(id);
    }

    @Override
    @GET
    @Path("/{id}/player")
    public DtoList<Player> getAllPlayers(@PathParam("id") final long id,
                                         @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first, @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
                                         @QueryParam(Constants.QUERY_PARAM_SORT) final String sort, @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {

        final ResultList<Player> data = playerService.findByUserId(id, LimitParams.create(first, size), SortParams.create(sort, desc));
	    final LimitParams limitParams = LimitParams.create(first, size);

        return new DtoList<>(data, limitParams);
    }

    @Override
    @DELETE
    @Path("/{uid}/player/{pid}")
    public void removePlayer(@PathParam("uid") final long userId,
                             @PathParam("pid") final long playerId) {
        userService.removePlayer(userId, playerId);
    }

    @Override
    @PUT
    @Path("/{uid}/player/{pid}")
    public void addPlayer(@PathParam("uid") final long userId, @PathParam("pid") final long playerId) {
        userService.addPlayer(userId, playerId);
    }

}
