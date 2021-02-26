package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.mybatis.dao.CourierMapper;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.model.Courier;
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
public class CourierServiceInfoMyBatis implements Serializable {

    @Inject
    private SenderMapper senderMapper;

    @Inject
    private CourierMapper courierMapper;

    @Getter
    @Setter
    private Courier courierService = new Courier();


    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String courierName = requestParameters.get("courierName");
        loadCourierService(courierName);
    }

    private void loadCourierService(String name){
        courierService = courierMapper.selectByPrimaryKey(name);
    }
}