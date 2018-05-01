package edu.uw.demoappjava.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Maxime Deravet
 * Date: 5/1/18
 */
@Data
@Builder
public class HelloDTO {

    private String phrase;
    private String param;


}
