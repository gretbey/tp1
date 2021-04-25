package lt.vu.decorators;

import lt.vu.entities.CourierService;
import lt.vu.exceptions.CompanyNameLengthException;
import lt.vu.interfaces.ICourierServicesDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class CourierCompanyNameLength implements ICourierServicesDAO {
    @Inject @Any @Delegate ICourierServicesDAO courierServicesDAO;

    public void persist(CourierService courierService) throws CompanyNameLengthException {
        if (courierService.getCompanyCode().length() != 3) {
            throw new CompanyNameLengthException("Company code length should be 3");
        }
        courierServicesDAO.persist(courierService);
    }
}
