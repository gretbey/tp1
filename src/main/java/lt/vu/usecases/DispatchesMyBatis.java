package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.mybatis.dao.DispatchMapper;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.model.Courier;
import lt.vu.mybatis.model.Dispatch;
import lt.vu.mybatis.model.Sender;
import lt.vu.persistence.CourierServicesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DispatchesMyBatis {
    @Inject
    private DispatchMapper dispatchMapper;

    @Getter
    private List<Dispatch> allDispatches;

    @Getter
    @Setter
    private Dispatch dispatchToCreate = new Dispatch();

    @Getter
    @Setter
    private String companyName;

    @PostConstruct
    public void init() {
        this.loadAllDispatches();
    }

    private void loadAllDispatches() {
        this.allDispatches = dispatchMapper.selectAll();
    }
    public List<Dispatch> loadDispatchesBySender(String senderName) { return dispatchMapper.selectBySender(senderName);}
    @Transactional
    public String createDispatch(String senderName) {
        dispatchToCreate.setStatus("created");
        dispatchToCreate.setSender(senderName);
        dispatchToCreate.setCourier(companyName);
        this.dispatchMapper.insert(dispatchToCreate);
        return "/myBatis/senderDetails?senderName=" + dispatchToCreate.getSender() + "&faces-redirect=true";
    }
}