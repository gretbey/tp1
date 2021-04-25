package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.mybatis.dao.CourierMapper;
import lt.vu.mybatis.dao.DispatchMapper;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.model.Courier;
import lt.vu.mybatis.model.Dispatch;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class CourierServiceInfoMyBatis implements Serializable {
    @Inject
    private CourierMapper courierMapper;

    @Getter
    @Setter
    private Courier courierService = new Courier();

    @Inject
    DispatchMapper dispatchMapper;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String courierName = requestParameters.get("courierName");
        loadCourierService(courierName);
    }

    private void loadCourierService(String name){
        courierService = courierMapper.selectByCompanyName(name);
    }
    public List<Dispatch> loadAllCourierDispatches() { return dispatchMapper.selectByCourier(courierService.getId());}

}