package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "STATUS")
    private String status = "created";

    @ManyToOne
    @JoinColumn(name = "COURIER")
    private CourierService courierService;

    @ManyToOne
    @JoinColumn(name = "SENDER")
    private Sender sender;

    @Column(name = "RECEIVER")
    private String receiverName;

    @Column(name = "RECEIVERADDRESS")
    private String receiverAddress;

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
