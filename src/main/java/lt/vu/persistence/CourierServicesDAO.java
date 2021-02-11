package lt.vu.persistence;

import lt.vu.entities.CourierService;
import lt.vu.entities.Dispatch;
import lt.vu.entities.Player;
import lt.vu.entities.Team;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CourierServicesDAO {
    @Inject
    private EntityManager em;

    public void persist(CourierService courierService){
        this.em.persist(courierService);
    }

    public CourierService findOne(String companyCode){
        return em.find(CourierService.class, companyCode);
    }

    public CourierService update(CourierService courierService){
        return em.merge(courierService);
    }

    public List<CourierService> loadAll() {
        return em.createNamedQuery("CourierService.findAll", CourierService.class).getResultList();
    }
}
