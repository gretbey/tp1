package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.swing.*;
import java.util.Random;

//@Specializes
@ApplicationScoped
public class DispatchNumberWithOneLetterGenerator extends DispatchNumberWithoutLettersGenerator{
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
        generatedDispatchNumber += alphabet.toCharArray()[new Random().nextInt(alphabet.toCharArray().length)];
        generatedDispatchNumber += new Random().nextInt(1000000) + "";
        return generatedDispatchNumber;
    }
}
