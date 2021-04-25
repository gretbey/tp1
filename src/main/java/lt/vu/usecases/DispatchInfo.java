package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Dispatch;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.DispatchesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
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
        try
        {
            this.dispatchesDAO.update(dispatch);
        } catch (OptimisticLockException e) {
            return "/dispatchDetails.xhtml?faces-redirect=true&dispatchId=" + dispatch.getDispatchID() + "&error=optimistic-lock-exception";
        }
        return "dispatchDetails?dispatchId=" + dispatch.getDispatchID() + "&faces-redirect=true";
    }

    private void loadDispatch(String dispatchId){
        dispatch = dispatchesDAO.findOne(dispatchId);
    }
}