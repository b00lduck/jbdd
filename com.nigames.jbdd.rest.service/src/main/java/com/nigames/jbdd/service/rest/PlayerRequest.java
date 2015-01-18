package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.PlayerRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.service.player.PlayerService;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/player")
public class PlayerRequest extends AbstractRequest<Player> implements PlayerRequestInterface {

    @Autowired
    private transient PlayerService playerService;

    @SuppressWarnings("SuspiciousGetterSetter")
    @Override
    protected PlayerService getService() {
        return playerService;
    }

    @Override
    @PUT
    @Path("/{id}")
    public Player update(@PathParam("id") final long id, final Player dto) {
        return playerService.update(id, dto);
    }

    @Override
    @GET
    @Path("/{id}")
    public Player getById(@PathParam("id") final long id) {
        return playerService.findById(id);
    }

    @Override
    @POST
    @Path("/")
    public Player create(final Player dto) {
        return playerService.create(dto);
    }

    @Override
    @GET
    @Path("/unused")
    public DtoList<Player> getAllUnused(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                                        @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                        @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {

        final DtoList<Player> ret = new DtoList<>();

        ret.setData(playerService.findAllUnused(LimitParams.create(first, size), SortParams.create(sort, desc)));

        final Long total = playerService.getCountUnused();

        ret.setMeta(AbstractRequest.createMeta(total, first, size));

        return ret;

    }

}
