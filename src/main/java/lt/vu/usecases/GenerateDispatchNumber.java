package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.DispatchNumberWithLetters;
import lt.vu.services.DispatchNumberWithoutLetters;
import lt.vu.services.IDispatchNumberGenerator;

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
    IDispatchNumberGenerator dispatchNumberGenerator;
    private CompletableFuture<String> dispatchNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewDispatchNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        dispatchNumberGenerationTask = CompletableFuture.supplyAsync(() -> dispatchNumberGenerator.generateDispatchNumber());
        return  "/senderDetails.xhtml?faces-redirect=true&senderName=" + requestParameters.get("senderName");
    }

    public boolean isDispatchNumberGenerationRunning() {
        return dispatchNumberGenerationTask != null && !dispatchNumberGenerationTask.isDone();
    }

    public String getDispatchNumberGenerationStatus() throws ExecutionException, InterruptedException {
        if (dispatchNumberGenerationTask == null) {
            return null;
        } else if (isDispatchNumberGenerationRunning()) {
            return "Dispatch number generation in progress";
        }
        return "Suggested dispatch number: " + dispatchNumberGenerationTask.get();
    }
}
