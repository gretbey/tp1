package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CourierService;
import lt.vu.entities.Sender;

import javax.persistence.ManyToOne;

@Getter @Setter
public class DispatchDto {
    private String dispatchID;
}

