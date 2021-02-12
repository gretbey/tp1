package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.DispatchNumberGenerator;
import lt.vu.services.JerseyNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateDispatchNumber implements Serializable {
    @Inject
    DispatchNumberGenerator dispatchNumberGenerator;

    private CompletableFuture<Integer> dispatchNumberGenerationTask = null;

    @LoggedInvocation
    public String generateDispatchNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        dispatchNumberGenerationTask = CompletableFuture.supplyAsync(() -> dispatchNumberGenerator.generateDispatchNumber());

        return  "/playerDetails.xhtml?faces-redirect=true&playerId=" + requestParameters.get("playerId");
    }

    public boolean isDispatchGenerationRunning() {
        return dispatchNumberGenerationTask != null && !dispatchNumberGenerationTask.isDone();
    }

    public String getDispatchGenerationStatus() throws ExecutionException, InterruptedException {
        if (dispatchNumberGenerationTask == null) {
            return null;
        } else if (isDispatchGenerationRunning()) {
            return "Dispatch generation in progress";
        }
        return "Suggested dispatch number: " + dispatchNumberGenerationTask.get();
    }
}
