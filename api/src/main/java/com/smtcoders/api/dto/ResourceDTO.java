package com.smtcoders.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private Long id;


    private String name;

    private String carrierPath;

    private String type;

    private String tecnologyStack;


    private Integer score;

    private String description;

    private String available;


}
