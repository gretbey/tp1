package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Dispatch;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.DispatchesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class DispatchInfo implements Serializable {

    @Inject
    private DispatchesDAO dispatchesDAO;

    @Getter
    @Setter
    private Dispatch dispatch = new Dispatch();



    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String dispatchId = requestParameters.get("dispatchId");
        loadDispatch(dispatchId);
    }

    @Transactional
    @LoggedInvocation
    public String updateStatus(){
        dispatchesDAO.update(dispatch);
        return "courierDetails?courierName=" + dispatch.getCourierService().getCompanyName() + "&faces-redirect=true";
    }

    private void loadDispatch(String dispatchId){
        dispatch = dispatchesDAO.findOne(dispatchId);
    }
}