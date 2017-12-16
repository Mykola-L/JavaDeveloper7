package ua.servlets.dao;

import ua.servlets.model.Manufacturer;

import java.util.UUID;

public interface IManufacturerDao extends IGenericDao<Manufacturer, UUID> {
    Manufacturer getByName(String name);
}
