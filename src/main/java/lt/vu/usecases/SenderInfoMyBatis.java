package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Sender;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CourierServicesDAO;
import lt.vu.persistence.SendersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class SenderInfoMyBatis implements Serializable {

    @Inject
    private SendersDAO sendersDAO;

    @Inject
    private CourierServicesDAO courierServicesDAO;

    @Getter
    @Setter
    private Sender sender = new Sender();

    @Getter
    private List<Sender> allSenders;

    @Getter
    @Setter
    private String couriersString;

    @Inject
    private Senders senders;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String senderName = requestParameters.get("senderName");
        loadSender(senderName);
    }

    @Transactional
    @LoggedInvocation
    public String updateSenderCourierServices(){
        sender.setCourierServices(senders.getCouriersFromString());
        sendersDAO.update(sender);
        return "index?faces-redirect=true";
        //return "senderInfo?senderName=" + sender.getName() + "&faces-redirect=true";
    }
    /*
    //example of handling code with transaction (always require new)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleOptimisticLockException(){

    }

    @Transactional
    @LoggedInvocation
    public String createMovie(){
        movieToCreate.setProducer(producer);

        List<Actor> actors = actorHelpers.getActorsFromString(movieToCreateActors, movieToCreate);
        for(Actor actor : actors)
            actorsDAO.persist(actor);

        List<Actor> actorsToSet = new ArrayList<Actor>();
        for(Actor actor : actors) {
            List<Actor> found = actorsDAO.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName());
            if(found.size() == 0) {
                actorsDAO.persist(actor);
                actorsToSet.add(actor);
            }else {
                Actor act = found.get(0);
                actorsToSet.add(act);
            }
        }

        movieToCreate.setActorList(actorsToSet);

        moviesDAO.persist(movieToCreate);
        return "producerInfo?producerId=" + producer.getId() + "&faces-redirect=true";
    }*/

    private void loadSender(String name){
        sender = sendersDAO.findOne(name);
    }
}