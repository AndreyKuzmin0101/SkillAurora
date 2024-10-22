package ru.skillaurora.profileservice.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.skillaurora.profileservice.exception.model.ServiceException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ClientErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        log.warn("{} failed with response {}", methodKey, response);
        return new ServiceException(getResponseBodyAsString(response.body()), HttpStatus.valueOf(response.status()));
    }

    public String getResponseBodyAsString(Response.Body body) {
        try (Reader reader = body.asReader(StandardCharsets.UTF_8)) {
            return Optional.ofNullable(objectMapper.readTree(IOUtils.toString(reader)))
                    .map(node -> node.get("message"))
                    .map(JsonNode::asText)
                    .orElse(null);
        } catch (IOException | NullPointerException e) {
            log.warn("Failed to read the response body with error: {}", e.getMessage());
            return null;
        }
    }
}