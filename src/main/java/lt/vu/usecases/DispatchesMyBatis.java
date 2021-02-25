package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.entities.Sender;
import lt.vu.mybatis.dao.DispatchMapper;
import lt.vu.mybatis.dao.TeamMapper;
import lt.vu.mybatis.model.Dispatch;
import lt.vu.mybatis.model.Team;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.DispatchesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DispatchesMyBatis {
    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Inject
    private DispatchMapper dispatchMapper;

    @Getter
    private List<Dispatch> allDispatches;

    @Getter @Setter
    private Dispatch dispatchToCreate = new Dispatch();

    @Getter @Setter
    private String companyName;

    @Getter @Setter
    private Sender sender;

    @Getter @Setter
    private CourierService courier;

    @PostConstruct
    public void init() {
        this.loadAllDispatches();
    }

    private void loadAllDispatches() {
        this.allDispatches = dispatchMapper.selectAll();
    }

    @Transactional
    public String createDispatch(String senderName) {
        dispatchToCreate.setStatus("created");
        dispatchToCreate.setSender(senderName);
        dispatchToCreate.setCourier(companyName);
        this.dispatchMapper.insert(dispatchToCreate);
        return "index?faces-redirect=true";
    }
}