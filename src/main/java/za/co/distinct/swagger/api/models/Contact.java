
package za.co.distinct.swagger.api.models;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Contact {
    private String id, name, phone;
}
