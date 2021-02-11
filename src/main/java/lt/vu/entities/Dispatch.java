package lt.vu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dispatch implements Serializable {
    public Dispatch(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dispatchID;

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
