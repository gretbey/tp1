package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped//@SessionScoped
public class PirmasKomponentas implements Serializable {


    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString();
    }

    @Inject
    Antras antras = new Antras();
    public String kazkas() { return antras.getClass().getName();}
    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}