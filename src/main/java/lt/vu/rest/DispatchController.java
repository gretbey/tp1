package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.entities.Dispatch;
import lt.vu.entities.Sender;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.DispatchesDAO;
import lt.vu.persistence.SendersDAO;
import lt.vu.rest.contracts.DispatchDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class DispatchController {
    @ApplicationScoped
    @Path("/dispatches")
    public static class DispatchesController {

        @Inject
        @Setter
        @Getter
        private DispatchesDAO dispatchesDAO;

        @Inject
        @Setter
        @Getter
        private CourierServicesDAO courierServicesDAO;

        @Inject
        @Setter
        @Getter
        private SendersDAO sendersDAO;

        @Path("/")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getDispatches() {
            List<Dispatch> dispatches = dispatchesDAO.loadAll();
            List<DispatchDTO> dispatchDTOS = new ArrayList<>();

            for (Dispatch dispatch : dispatches)
            {
                DispatchDTO dispDTO = new DispatchDTO();
                dispDTO.setDispatchID(dispatch.getDispatchID());
                dispDTO.setCourierId(dispatch.getCourierService().getId());
                dispDTO.setStatus(dispatch.getStatus());
                dispDTO.setReceiverAddress(dispatch.getReceiverAddress());
                dispDTO.setReceiverName(dispatch.getReceiverName());
                dispDTO.setDispatchNumber(dispatch.getDispatchNumber());
                dispDTO.setSenderId(dispatch.getSender().getId());
                dispatchDTOS.add(dispDTO);
            }

            return Response.ok(dispatchDTOS).build();
        }

        @Path("/{id}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getDispatchByID(@PathParam("id") final String id) {
            Dispatch dispatch = dispatchesDAO.findOne(id);
            if (dispatch == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            DispatchDTO dispDTO = new DispatchDTO();
            dispDTO.setDispatchID(dispatch.getDispatchID());
            dispDTO.setCourierId(dispatch.getCourierService().getId());
            dispDTO.setStatus(dispatch.getStatus());
            dispDTO.setReceiverAddress(dispatch.getReceiverAddress());
            dispDTO.setReceiverName(dispatch.getReceiverName());
            dispDTO.setDispatchNumber(dispatch.getDispatchNumber());
            dispDTO.setSenderId(dispatch.getSender().getId());

            return Response.ok(dispDTO).build();
        }

        @Path("/{id}")
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Transactional
        public Response updateDispatch(DispatchDTO disp) {
            try {
                if (disp == null || disp.getDispatchNumber() == null || disp.getDispatchID() == null || disp.getStatus() == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                Dispatch dispToCreate = new Dispatch();
                dispToCreate.setDispatchID(disp.getDispatchID());
                CourierService courierService = courierServicesDAO.findOne((disp.getCourierId()));
                if(courierService == null){
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
                dispToCreate.setCourierService(courierService);
                dispToCreate.setStatus(disp.getStatus());
                dispToCreate.setReceiverAddress(disp.getReceiverAddress());
                dispToCreate.setReceiverName(disp.getReceiverName());
                dispToCreate.setDispatchNumber(disp.getDispatchNumber());
                Sender sender = sendersDAO.findOne(disp.getSenderId());
                if(sender == null){
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
                dispToCreate.setSender(sender);
                dispatchesDAO.update(dispToCreate);
                return Response.status(Response.Status.CREATED).build();
            } catch (OptimisticLockException ole) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }

        @Path("/")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Transactional
        public Response createDispatch(DispatchDTO disp) {
            try {
                if (disp == null || disp.getDispatchNumber() == null || disp.getDispatchID() == null || disp.getStatus() == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                Dispatch dispToCreate = new Dispatch();
                dispToCreate.setDispatchID(disp.getDispatchID());
                CourierService courierService = courierServicesDAO.findOne((disp.getCourierId()));
                if(courierService == null){
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
                dispToCreate.setCourierService(courierService);//
                dispToCreate.setStatus(disp.getStatus());
                dispToCreate.setReceiverAddress(disp.getReceiverAddress());
                dispToCreate.setReceiverName(disp.getReceiverName());
                dispToCreate.setDispatchNumber(disp.getDispatchNumber());
                Sender sender = sendersDAO.findOne(disp.getSenderId());
                if(sender == null){
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
                dispToCreate.setSender(sender);//
                dispatchesDAO.persist(dispToCreate);
                return Response.status(Response.Status.CREATED).build();
            } catch (OptimisticLockException ole) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
    }
}
