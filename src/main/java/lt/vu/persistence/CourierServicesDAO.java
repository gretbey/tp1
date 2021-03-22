package lt.vu.persistence;

import lt.vu.entities.CourierService;
import lt.vu.entities.Sender;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CourierServicesDAO {
    @Inject
    private EntityManager entityManager;

    public void persist(CourierService courier){
        this.entityManager.persist(courier);
    }

    public CourierService findOne(String id){
        return entityManager.find(CourierService.class, id);
    }

    public CourierService update(CourierService courier){
        return entityManager.merge(courier);
    }

    public List<CourierService> loadAll() {
        return entityManager.createNamedQuery("CourierService.findAll", CourierService.class).getResultList();
    }

    public CourierService findByName(String name) {
        TypedQuery<CourierService> query = entityManager.createNamedQuery("CourierService.findByCompanyName", CourierService.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
