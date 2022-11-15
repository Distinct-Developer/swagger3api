package za.co.distinct.swagger.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.text.SimpleDateFormat;

/**
 *
 * @author Boiki Mphore
 * @since 2022-11-15
 */
public class JsonUtil {
    
    /**
     * Sets all the necessary object mapper data formats that are preferred for JSON objects.
     * @return A newly constructed object writer with preferred data formats.
     */
    public static ObjectWriter setWriterJsonDataFormats() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper.writerWithDefaultPrettyPrinter();
    }
}
