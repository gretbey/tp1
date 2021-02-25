package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.*;
import lt.vu.persistence.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Dispatches {
    @Inject
    private DispatchesDAO dispatchesDAO;

    @Inject
    private SendersDAO sendersDAO;

    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Getter
    @Setter
    private Dispatch dispatchesToCreate = new Dispatch();

    @Getter
    private List<Dispatch> allDispatches;

    @Getter @Setter
    private Sender sender;

    @Getter @Setter
    private CourierService courier;

    @Getter @Setter
    private String senderName;

    @Getter @Setter
    private String companyName;

    @PostConstruct
    public void init(){
        loadAllDispatches();
    }

    @Transactional
    public String createDispatch(String senderName) {
        this.sender = sendersDAO.findOne(senderName);
        dispatchesToCreate.setSender(this.sender);
        this.courier = courierServicesDAO.findOne(companyName);
        dispatchesToCreate.setCourierService(this.courier);
        this.dispatchesDAO.persist(dispatchesToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllDispatches(){
        this.allDispatches = dispatchesDAO.loadAll();
    }
}
