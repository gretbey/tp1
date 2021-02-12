package lt.vu.services;

import java.util.Random;

public class DispatchNumberGenerator {
    // Siuntos generavimas : siuntu tarnybos kodas plius random, bet neegzistuojantys skaiciai
    public Integer generateDispatchNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Integer generatedJerseyNumber = new Random().nextInt(100);
        return generatedJerseyNumber;
    }
}
