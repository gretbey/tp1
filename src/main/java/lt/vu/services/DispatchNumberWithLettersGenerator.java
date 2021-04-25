package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class DispatchNumberWithLettersGenerator implements Serializable, IDispatchNumberGenerator{
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
        int randNumberOfLetters = new Random().nextInt(2) + 1;// nuo 1 iki 3 bus raidziu
        generatedDispatchNumber = "";
        for (int i=0; i<randNumberOfLetters;i++)
        {
            generatedDispatchNumber += alphabet.toCharArray()[new Random().nextInt(alphabet.toCharArray().length)];
        }
        generatedDispatchNumber += new Random().nextInt(1000000) + "";
        return generatedDispatchNumber;
    }
}
