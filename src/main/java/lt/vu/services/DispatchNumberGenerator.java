package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class DispatchNumberGenerator implements Serializable {
    // Siuntos generavimas : siuntu tarnybos kodas plius random, bet neegzistuojantys skaiciai
    public String generateDispatchNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        String generatedDispatchNumber = "a";
        return generatedDispatchNumber;
    }
}
