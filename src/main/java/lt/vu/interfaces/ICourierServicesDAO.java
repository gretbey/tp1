package lt.vu.interfaces;

import lt.vu.entities.CourierService;
import lt.vu.exceptions.CompanyNameLengthException;

import java.util.List;

public interface ICourierServicesDAO {
    void persist(CourierService courier) throws CompanyNameLengthException, CompanyNameLengthException;
    CourierService findOne(int id);
    CourierService update(CourierService courier);
    List<CourierService> loadAll();
    CourierService findByName(String name);
}
