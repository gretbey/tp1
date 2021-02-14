package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Sender.findAll", query = "select t from Sender as t")
})
@Table(name = "SENDER")
@Getter
@Setter
public class Sender implements Serializable {
    public Sender(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    @ManyToMany//(mappedBy = "SENDER")
    private List<CourierService> courierServices = new ArrayList<>();

    @OneToMany//(mappedBy = "SENDER")
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
