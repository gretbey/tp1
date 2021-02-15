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
        @NamedQuery(name = "CourierService.findAll", query = "select a from CourierService as a")
})
@Table(name = "COURIERSERVICE")
@Getter
@Setter
public class CourierService implements Serializable {

    @Size(max = 50)
    @Column(name = "CODE")
    private String companyCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANYNAME")
    private String companyName;

    @JoinTable(name = "COURIER_SENDERS", joinColumns = {
            @JoinColumn(name = "COMPANYNAME", referencedColumnName = "COMPANYNAME")}, inverseJoinColumns = {
            @JoinColumn(name = "NAME", referencedColumnName = "NAME")})
    @ManyToMany(cascade=CascadeType.ALL)
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
        CourierService courierService = (CourierService) o;
        return Objects.equals(companyCode, courierService.companyCode) &&
                Objects.equals(companyName, courierService.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode, companyName);
    }
}
