package lt.vu.decorators;

import lt.vu.entities.CourierService;
import lt.vu.exceptions.CompanyNameLengthException;
import lt.vu.interfaces.ICourierServicesDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.swing.*;

@Decorator
public abstract class CourierCompanyNameLength implements ICourierServicesDAO {
    @Inject @Any @Delegate ICourierServicesDAO courierServicesDAO;

    public void persist(CourierService courierService) throws CompanyNameLengthException {
        String compCode = courierService.getCompanyCode();
        String compName = courierService.getCompanyName();
        if (compCode.length() != 3) {
            if (compCode.length() > 3)
            {
                compCode = compName.substring(0, 3);
            }
            else
            {
                int rem = 3 - compCode.length();
                for (int i=0;i<rem;i++)
                {
                    compCode += "X";
                }
            }
            System.out.println("Company code length should be 3. Setting code to: " + compCode);
            courierService.setCompanyCode(compCode);
        }
        courierServicesDAO.persist(courierService);
    }
}
