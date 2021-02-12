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
        @NamedQuery(name = "Dispatch.findAll", query = "select t from Dispatch as t")
})
@Table(name = "DISPATCH")
@Getter
@Setter
public class Dispatch implements Serializable {
    public Dispatch(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dispatchID;

    //SIUNTOS STATUSAS - pristatymas, issiustas, ruosiamas

    @ManyToOne
    private CourierService courierService;

    @ManyToOne
    private Sender sender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dispatch dispatch = (Dispatch) o;
        return Objects.equals(dispatchID, dispatch.dispatchID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dispatchID);
    }
}
