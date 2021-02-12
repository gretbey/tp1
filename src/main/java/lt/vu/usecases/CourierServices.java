package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.entities.Team;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CourierServices {
    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Inject
    private SendersDAO sendersDAO;

    @Getter
    @Setter
    private CourierService couriersToCreate = new CourierService();

    @Getter
    private List<CourierService> allCourierServices;

    @PostConstruct
    public void init(){
        loadAllCourierServices();
    }

    @Transactional
    public String createCourierService(){
        this.courierServicesDAO.persist(couriersToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllCourierServices(){
        this.allCourierServices = courierServicesDAO.loadAll();
    }
}
