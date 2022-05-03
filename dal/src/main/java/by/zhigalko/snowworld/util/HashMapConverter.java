package by.zhigalko.snowworld.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> productInfo) {
        String customerInfoJson = null;
        try {
            customerInfoJson = OBJECT_MAPPER.writeValueAsString(productInfo);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String productInfoJSON) {
        Map<String, Object> customerInfo = null;
        try {
            customerInfo = OBJECT_MAPPER.readValue(productInfoJSON, Map.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
