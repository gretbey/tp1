package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispatchDTO {
    public String dispatchID;
    public String dispatchNumber;
    public String status;
    public Integer courierId;
    public Integer senderId;
    public String receiverName;
    public String receiverAddress;
}
