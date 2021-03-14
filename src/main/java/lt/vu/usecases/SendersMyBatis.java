package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CourierMapper;
import lt.vu.mybatis.dao.CourierSendersMapper;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.model.Courier;
import lt.vu.mybatis.model.CourierSenders;
import lt.vu.mybatis.model.Sender;

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

    @Inject
    CourierMapper courierMapper;

    @Inject
    CourierSendersMapper courierSendersMapper;

    @Getter
    private List<Sender> allSenders;

    @Getter @Setter
    private Sender senderToCreate = new Sender();

    @Getter
    @Setter
    private String couriersString;

    @Getter
    @Setter
    List<Courier> senderCouriers;


    @PostConstruct
    public void init() {
        this.loadAllSenders();
    }

    private void loadAllSenders() {
        this.allSenders = senderMapper.selectAll();
    }

    @Transactional
    public String createSender() {
        senderCouriers = getCouriersFromString();
        senderMapper.insert(senderToCreate);
        senderToCreate.setCourierServices(senderCouriers);
        senderMapper.updateByPrimaryKey(senderToCreate);

        CourierSenders courierSenders;
        for (Courier cour : senderCouriers)
        {
            courierSenders = new CourierSenders();
            courierSenders.setCompanyname(cour.getCompanyname());
            courierSenders.setName(senderToCreate.getName());
            courierSendersMapper.insert(courierSenders);
        }
        return "/myBatis/couriers_senders?faces-redirect=true";
    }

    public List<Courier> getCouriersFromString (){
        List<Courier> couriersList = new ArrayList<>();
        String[] couriersNames = couriersString.trim().split(",");
        Courier foundCourier;
        for(String courierName : couriersNames){
            foundCourier = courierMapper.selectByPrimaryKey(courierName);

            if (foundCourier == null)
            {
                Courier courierToCreate = new Courier();
                courierToCreate.setCompanyname(courierName);
                courierToCreate.setCode(courierName.substring(0,2));
                couriersList.add(courierToCreate);
            }
            else
            {
                couriersList.add(foundCourier);
            }
        }
        return couriersList;
    }
}
