package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.dao.TeamMapper;
import lt.vu.mybatis.model.Sender;
import lt.vu.mybatis.model.Team;
import lt.vu.persistence.CourierServicesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class SendersMyBatis {
    @Inject
    private SenderMapper senderMapper;

    @Getter
    private List<Sender> allSenders;

    @Getter @Setter
    private Sender senderToCreate = new Sender();

    @Getter
    @Setter
    private String couriersString;

    @Getter
    @Setter
    private CourierServicesDAO courierServicesDAO;

    @PostConstruct
    public void init() {
        this.loadAllSenders();
    }

    private void loadAllSenders() {
        this.allSenders = senderMapper.selectAll();
    }

    @Transactional
    public String createSender() {
        senderMapper.insert(senderToCreate);
        return "/myBatis/couriers_senders?faces-redirect=true\"";
    }
}
