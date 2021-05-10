package lt.vu.persistence;

import lt.vu.entities.Sender;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class SendersDAO {
    @Inject
    private EntityManager entityManager;

    public void persist(Sender sender){
        this.entityManager.persist(sender);
    }

    public Sender findOne(int id){
        return entityManager.find(Sender.class, id);
    }

    public Sender update(Sender sender){
        return entityManager.merge(sender);
    }

    public List<Sender> loadAll() {
        return entityManager.createNamedQuery("Sender.findAll", Sender.class).getResultList();
    }

    public Sender findByName(String name) {
        TypedQuery<Sender> query = entityManager.createNamedQuery("Sender.findByName", Sender.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
