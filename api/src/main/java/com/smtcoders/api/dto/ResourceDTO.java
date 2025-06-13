package com.smtcoders.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private Long id;
    private String name;
    private String carrierPath;
    private String type;
    private String tecnologyStack;
    private Integer points;
    private String description;
    private String available;
}
