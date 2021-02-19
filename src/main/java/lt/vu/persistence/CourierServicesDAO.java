package lt.vu.persistence;

import lt.vu.entities.CourierService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CourierServicesDAO {
    @Inject
    private EntityManager em;

    public void persist(CourierService courier){
        this.em.persist(courier);
    }

    public CourierService findOne(String companyCode){
        return em.find(CourierService.class, companyCode);
    }

    public CourierService findOneByName(String companyName){
        return em.find(CourierService.class, companyName);
    }

    public CourierService update(CourierService courier){
        return em.merge(courier);
    }

    public List<CourierService> loadAll() {
        return em.createNamedQuery("CourierService.findAll", CourierService.class).getResultList();
    }
}
