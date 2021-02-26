package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class CourierServiceInfo implements Serializable {

    @Inject
    private SendersDAO sendersDAO;

    @Inject
    private CourierServicesDAO courierServicesDAO;


    @Getter
    @Setter
    private CourierService courierService = new CourierService();


    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String courierName = requestParameters.get("courierName");
        loadCourierService(courierName);
    }

    private void loadCourierService(String name){
        courierService = courierServicesDAO.findOne(name);
    }
}