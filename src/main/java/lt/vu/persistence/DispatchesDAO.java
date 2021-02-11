package lt.vu.persistence;

import lt.vu.entities.Dispatch;
import lt.vu.entities.Player;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DispatchesDAO {

    @Inject
    private EntityManager em;

    public void persist(Dispatch dispatch){
        this.em.persist(dispatch);
    }

    public Dispatch findOne(Integer dispatchID){
        return em.find(Dispatch.class, dispatchID);
    }

    public Dispatch update(Dispatch dispatch){
        return em.merge(dispatch);
    }

    public List<Dispatch> loadAll() {
        return em.createNamedQuery("Dispatch.findAll", Dispatch.class).getResultList();
    }
}
