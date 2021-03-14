package lt.vu.persistence;

import lt.vu.entities.Sender;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SendersDAO {
    @Inject
    private EntityManager entityManager;

    public void persist(Sender sender){
        this.entityManager.persist(sender);
    }

    public Sender findOne(String name){
        return entityManager.find(Sender.class, name);
    }

    public Sender update(Sender sender){
        return entityManager.merge(sender);
    }

    public List<Sender> loadAll() {
        return entityManager.createNamedQuery("Sender.findAll", Sender.class).getResultList();
    }
}
