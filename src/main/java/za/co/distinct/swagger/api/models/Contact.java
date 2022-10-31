
package za.co.distinct.swagger.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author FMphore
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private String id, name, phone;
}
