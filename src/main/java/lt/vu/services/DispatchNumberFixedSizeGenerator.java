package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.swing.*;
import java.util.Random;

@Specializes
@ApplicationScoped
public class DispatchNumberFixedSizeGenerator extends DispatchNumberWithoutLettersGenerator{
    String generatedDispatchNumber = "";
    public String generateDispatchNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    "Interrupted process",
                    "Interrupted process",
                    JOptionPane.WARNING_MESSAGE);
        }
        generatedDispatchNumber = "";
        int minimum = 1000000;
        //range 1000000-9999999
        generatedDispatchNumber += minimum + new Random().nextInt(10000000) + "";
        return generatedDispatchNumber;
    }
}
