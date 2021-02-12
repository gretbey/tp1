package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Sender;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Senders {
    @Inject
    private SendersDAO sendersDAO;

    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Getter
    @Setter
    private Sender sendersToCreate = new Sender();

    @Getter
    private List<Sender> allSenders;

    @PostConstruct
    public void init(){
        loadAllSenders();
    }

    @Transactional
    public String createCourierService(){
        this.sendersDAO.persist(sendersToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllSenders(){
        this.allSenders = sendersDAO.loadAll();
    }
}
