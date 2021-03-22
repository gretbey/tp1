package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "CourierService.findAll", query = "select a from CourierService as a"),
        @NamedQuery(name = "CourierService.findByCompanyName", query = "select a from CourierService as a where a.companyName = :name")
})
@Table(name = "COURIER")
@Getter @Setter
public class CourierService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE")
    private String companyCode;

    @Size(max = 50)
    @Column(name = "COMPANYNAME")
    private String companyName;

    @ManyToMany(mappedBy = "courierServices")
    private List<Sender> senders = new ArrayList<>();

    @OneToMany(mappedBy = "courierService")
    private List<Dispatch> dispatches = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public CourierService() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourierService courier = (CourierService) o;
        return Objects.equals(companyCode, courier.companyCode) &&
                Objects.equals(companyName, courier.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode, companyName);
    }
}
