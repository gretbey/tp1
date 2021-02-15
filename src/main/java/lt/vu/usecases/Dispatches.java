package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.*;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class Dispatches implements Serializable {
    @Inject
    private DispatchesDAO dispatchesDAO;

    @Inject
    private SendersDAO sendersDAO;

    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Getter
    @Setter
    private Sender sender;

    @Getter
    @Setter
    private CourierService courierService;

    @Getter @Setter
    private Dispatch dispatchesToCreate = new Dispatch();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String compCode = requestParameters.get("companyCode");
        this.courierService = courierServicesDAO.findOne(compCode);
        String senderName = requestParameters.get("name");
        this.sender = sendersDAO.findOne(senderName);
    }

    @Transactional
    @LoggedInvocation
    public String createDispatch() {
        dispatchesToCreate.setDispatchID("0");
        dispatchesToCreate.setSender(sender);
        dispatchesToCreate.setCourierService(courierService);
        dispatchesDAO.persist(dispatchesToCreate);
        return "players?faces-redirect=true&teamId=" + this.dispatchesToCreate.getDispatchID();
    }

    //remove dispatch
}
