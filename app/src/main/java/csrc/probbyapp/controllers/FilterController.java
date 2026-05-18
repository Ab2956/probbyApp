package csrc.probbyapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import csrc.probbyapp.models.PropertyModel;

public class FilterController {
    // Filter and order by functionality

    // Filter properties by type - using stream method
    public List<PropertyModel> filterProperties(List<PropertyModel> properties, String type) {

        if (type == null || type.isEmpty() || type.equalsIgnoreCase("All")) {
            return properties;
        }

        return properties.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase(type))
                .collect(Collectors.toList());

    }

    // Sort properties by rent or mortgage - using sort method from Collections class
    public List<PropertyModel> sortProperties(List<PropertyModel> properties, String type) {
        if (type == null || type.isEmpty() || type.equalsIgnoreCase("All")) {
            return properties;
        }
        List<PropertyModel> sortedProperties = new ArrayList<>(properties);

        if (type.equalsIgnoreCase("Rent")) {
            sortedProperties.sort((p1, p2) -> Integer.compare(p2.getRent(), p1.getRent()));
        }
        else if (type.equalsIgnoreCase("Mortgage")) {
            sortedProperties.sort((p1, p2) -> Integer.compare(p2.getMortgage(), p1.getMortgage()));
        }

        return sortedProperties;
    }
}
