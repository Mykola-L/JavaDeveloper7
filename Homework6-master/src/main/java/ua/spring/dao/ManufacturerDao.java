package ua.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.spring.model.Manufacturer;

import java.util.UUID;

public interface ManufacturerDao extends JpaRepository<Manufacturer, UUID> {
    Manufacturer getByName(String name);
}
