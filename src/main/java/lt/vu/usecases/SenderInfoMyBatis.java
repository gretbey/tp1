package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.CourierMapper;
import lt.vu.mybatis.dao.CourierSendersMapper;
import lt.vu.mybatis.dao.DispatchMapper;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.model.Courier;
import lt.vu.mybatis.model.CourierSenders;
import lt.vu.mybatis.model.Dispatch;
import lt.vu.mybatis.model.Sender;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class SenderInfoMyBatis implements Serializable {

    @Inject
    private SenderMapper senderMapper;

    @Inject
    private CourierMapper courierMapper;

    @Inject
    private CourierSendersMapper courierSendersMapper;

    @Inject
    private DispatchMapper dispatchMapper;

    @Getter
    @Setter
    private lt.vu.mybatis.model.Sender sender;

    @Getter
    private List<Sender> allSenders;

    @Getter
    @Setter
    private String couriersString;

    @Inject
    private Senders senders;

    @Getter
    @Setter
    private List<Courier> couriers;

    @Getter
    @Setter
    private List<Dispatch> dispatches;

    public List<Courier> loadCouriersBySender(String senderName)
    {
        return courierSendersMapper.getCouriers(senderName);
    }

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String senderName = requestParameters.get("senderName");
        this.sender = senderMapper.selectByPrimaryKey(senderName);
    }

    @Transactional
    @LoggedInvocation
    public String updateSenderCourierServices(){
        //sender.(senders.getCouriersFromString());
        //sendersDAO.update(sender);
        return "/myBatis/senderDetails?senderName=" + sender.getName() + "&faces-redirect=true";
    }
}