package lt.vu.persistence;

import lt.vu.entities.Dispatch;
import lt.vu.entities.Player;
import lt.vu.entities.Sender;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SendersDAO {
    @Inject
    private EntityManager em;

    public void persist(Sender sender){
        this.em.persist(sender);
    }

    public Sender findOne(String name){
        return em.find(Sender.class, name);
    }

    public Sender update(Sender sender){
        return em.merge(sender);
    }

    public List<Sender> loadAll() {
        return em.createNamedQuery("Sender.findAll", Sender.class).getResultList();
    }
}
