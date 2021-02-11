package lt.vu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sender implements Serializable {
    public Sender(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer name;

    @ManyToMany(mappedBy = "sender")
    private List<CourierService> courierServices = new ArrayList<>();

    @OneToMany
    private List<Dispatch> dispatches = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(name, sender.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
