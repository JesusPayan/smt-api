package utils;

import com.smtcoders.api.dto.ResourceDTO;
import com.smtcoders.api.entity.Resource;

import javax.swing.text.html.parser.Entity;

public class Converter {

    public static Resource converToEntity(ResourceDTO resourceDTO){
        Resource entity = new Resource(
                resourceDTO.getName(),
                resourceDTO.getCarrierPath(),
                resourceDTO.getTecnologyStack(),
                resourceDTO.getType(),
                resourceDTO.getPoints(),
                resourceDTO.getDescription(),
                resourceDTO.getAvailable());

        return entity;
    }
}
