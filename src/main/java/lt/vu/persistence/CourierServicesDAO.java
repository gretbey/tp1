package lt.vu.persistence;

import lt.vu.entities.CourierService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CourierServicesDAO {
    @Inject
    private EntityManager entityManager;

    public void persist(CourierService courier){
        this.entityManager.persist(courier);
    }

    public CourierService findOne(String companyName){
        return entityManager.find(CourierService.class, companyName);
    }

    public CourierService update(CourierService courier){
        return entityManager.merge(courier);
    }

    public List<CourierService> loadAll() {
        return entityManager.createNamedQuery("CourierService.findAll", CourierService.class).getResultList();
    }
}
