package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Dispatch;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.DispatchesDAO;
import lt.vu.persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateDispatchDetails implements Serializable {

    private Dispatch dispatch;

    @Inject
    private DispatchesDAO dispatchesDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateDispatchDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("dispatchID"));
        this.dispatch = dispatchesDAO.findOne(playerId);
    }

    @Transactional
    @LoggedInvocation
    public String updatePlayerJerseyNumber() {
        try{
            dispatchesDAO.update(this.dispatch);
        } catch (OptimisticLockException e) {
            return "/playerDetails.xhtml?faces-redirect=true&playerId=" + this.dispatch.getDispatchID() + "&error=optimistic-lock-exception";
        }
        return "players.xhtml?teamId=" + this.dispatch.getSender().getName() + "&faces-redirect=true";
    }
}