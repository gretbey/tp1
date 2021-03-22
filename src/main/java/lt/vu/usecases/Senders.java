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
        return "couriers_senders.xhtml?faces-redirect=true";
    }

    private void loadAllSenders(){
        this.allSenders = sendersDAO.loadAll();
    }

    public List<CourierService> getCouriersFromString (){
        List<CourierService> couriersList = new ArrayList<>();
        String[] couriersNames = couriersString.replaceAll(" ", "").split(",");
        for(String courierName : couriersNames){
            CourierService foundCourier = courierServicesDAO.findByName(courierName);
            if (foundCourier == null)
            {
                CourierService courierToCreate = new CourierService();
                courierToCreate.setCompanyName(courierName);
                courierToCreate.setCompanyCode(courierName.substring(0,2));
                couriersList.add(courierToCreate);
            }
            else
            {
                couriersList.add(foundCourier);
            }
        }
        return couriersList;
    }
}
