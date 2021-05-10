package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class DispatchNumberWithoutLettersGenerator implements Serializable, IDispatchNumberGenerator {
    public String generateDispatchNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    "Interrupted process",
                    "Interrupted process",
                    JOptionPane.WARNING_MESSAGE);
        }
        Integer generatedDispatchNumber=  new Random().nextInt(1000000);
        return generatedDispatchNumber + "";
    }
}