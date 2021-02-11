package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Dispatch;
import lt.vu.entities.Player;
import lt.vu.persistence.DispatchesDAO;
import lt.vu.persistence.PlayersDAO;
import lt.vu.rest.contracts.DispatchDto;
import lt.vu.rest.contracts.PlayerDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/dispatches")
public class DispatchesController {

    @Inject
    @Setter
    @Getter
    private DispatchesDAO dispatchesDAO;

    @Path("/{dispatchID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("dispatchID") final Integer dispatchID) {
        Dispatch dispatch = dispatchesDAO.findOne(dispatchID);
        if (dispatch == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DispatchDto dispatchDto = new DispatchDto();
        dispatchDto.setDispatchID(dispatch.getDispatchID());

        return Response.ok(dispatchDto).build();
    }

    @Path("/{dispatchID}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("dispatchID") final Integer dispatchID,
            DispatchDto dispatchDto) {
        try {
            Dispatch existingDispatch = dispatchesDAO.findOne(dispatchID);
            if (existingDispatch == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingDispatch.setDispatchID(dispatchDto.getDispatchID());
            dispatchesDAO.update(existingDispatch);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

