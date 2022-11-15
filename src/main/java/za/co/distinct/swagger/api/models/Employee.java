
package za.co.distinct.swagger.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Boiki Mphore
 * @since 2022-11-15
 */
@Component
@Data
@NoArgsConstructor
public class Employee {
    
    private Integer id;
    private String name, designation;
    private double salary;
}
