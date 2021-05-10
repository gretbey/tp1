package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.*;
import lt.vu.persistence.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.List;
import lt.vu.interceptors.LoggedInvocation;

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
    @LoggedInvocation
    public String createDispatch(String senderName) {
        this.sender = sendersDAO.findByName(senderName);
        dispatchesToCreate.setSender(this.sender);
        this.courier = courierServicesDAO.findByName(companyName);
        if (sender.getCourierServices().contains(courier)) {
            dispatchesToCreate.setCourierService(this.courier);
            this.dispatchesDAO.persist(dispatchesToCreate);
            return "senderDetails?senderName=" + senderName + "&faces-redirect=true";
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "This courier is not available",
                    "This courier is not available",
                    JOptionPane.WARNING_MESSAGE);
            return "senderDetails?senderName=" + senderName + "&faces-redirect=false";
        }
    }

    private void loadAllDispatches(){
        this.allDispatches = dispatchesDAO.loadAll();
    }
}
