package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.entities.Sender;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class SenderInfo implements Serializable {

    @Inject
    private SendersDAO sendersDAO;

    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Getter
    @Setter
    private Sender sender = new Sender();

    @Getter
    private List<Sender> allSenders;

    @Getter
    @Setter
    private String couriersString;

    @Inject
    private Senders senders;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String senderName = requestParameters.get("senderName");
        loadSender(senderName);
    }

    @Transactional
    @LoggedInvocation
    public String updateSenderCourierServices(){
       sender.setCourierServices(senders.getCouriersFromString());
       sendersDAO.update(sender);
       return "senderDetails?senderName=" + sender.getName() + "&faces-redirect=true";
    }

    private void loadSender(String name){
        sender = sendersDAO.findOne(name);
    }
}