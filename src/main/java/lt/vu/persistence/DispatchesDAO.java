package lt.vu.persistence;

import lt.vu.entities.Dispatch;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DispatchesDAO {

    @Inject
    private EntityManager entityManager;

    public void persist(Dispatch dispatch){
        this.entityManager.persist(dispatch);
    }

    public Dispatch update(Dispatch dispatch){
        return entityManager.merge(dispatch);
    }

    public List<Dispatch> loadAll() {
        return entityManager.createNamedQuery("Dispatch.findAll", Dispatch.class).getResultList();
    }
}
