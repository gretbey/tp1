package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.DispatchMapper;
import lt.vu.mybatis.model.Dispatch;
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
public class DispatchInfoMyBatis implements Serializable {

    @Inject
    private DispatchMapper dispatchMapper;

    @Getter
    @Setter
    private lt.vu.mybatis.model.Dispatch dispatch = new Dispatch();

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
        dispatchMapper.updateByPrimaryKey(dispatch);
        return "/myBatis/courierDetails?courierName=" + dispatch.getCourier() + "&faces-redirect=true";
    }

    private void loadDispatch(String dispatchId){
        dispatch = dispatchMapper.selectByPrimaryKey(dispatchId);
    }
}