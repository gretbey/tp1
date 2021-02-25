package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CourierMapper;
import lt.vu.mybatis.model.Courier;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CouriersMyBatis {
    @Inject
    private CourierMapper courierMapper;

    @Getter
    private List<Courier> allCourierServices;

    @Getter @Setter
    private Courier courierToCreate = new Courier();

    @PostConstruct
    public void init() {
        this.loadAllCouriers();
    }

    private void loadAllCouriers() {
        this.allCourierServices = courierMapper.selectAll();
    }

    @Transactional
    public String createCourier() {
        courierMapper.insert(courierToCreate);
        return "index?faces-redirect=true";
    }
}