package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.entities.Sender;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.jms.Message;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Getter
    @Setter
    private String couriersString;

    @PostConstruct
    public void init(){
        loadAllSenders();
    }

    @Transactional
    public String createSenders(){
        this.sendersDAO.persist(sendersToCreate);
        sendersToCreate.setCourierServices(getCouriersFromString());
        sendersDAO.update(sendersToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllSenders(){
        this.allSenders = sendersDAO.loadAll();
    }

    public List<CourierService> getCouriersFromString (){
        List<CourierService> couriersList = new ArrayList<CourierService>();
        String[] couriersNames = couriersString.trim().split(",");
        for(String courierName : couriersNames){
            CourierService findedCourier = courierServicesDAO.findOneByName(courierName);
            if (findedCourier == null)
            {
                CourierService courierToCreate = new CourierService();
                courierToCreate.setCompanyName(courierName);
                courierToCreate.setCompanyCode(courierName.substring(0,2));
                couriersList.add(courierToCreate);
            }
            else
            {
                couriersList.add(findedCourier);
            }
        }
        return couriersList;
    }
}
