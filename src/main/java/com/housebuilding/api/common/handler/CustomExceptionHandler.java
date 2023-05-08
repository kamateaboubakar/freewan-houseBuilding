package com.housebuilding.api.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housebuilding.api.common.http.ApiError;
import com.housebuilding.api.common.http.ApiResponseCode;
import com.housebuilding.api.common.http.ApiValidationError;
import com.housebuilding.api.exception.ApplicationException;
import com.housebuilding.api.exception.DataNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.housebuilding.api.common.http.ApiResponseCode.INTERNAL_SERVER_ERROR;
import static com.housebuilding.api.common.http.ApiResponseCode.REQUEST_VALIDATION_ERROR;

/**
 * Handle all security exception for the app
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * This method handle all runtime exceptions.
     *
     * @param e the runtime exception.
     * @return ResponseEntity<ApiError>
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException e) {
        log.error("Runtime exception thrown:", e);
        return new ResponseEntity<>(new ApiError(INTERNAL_SERVER_ERROR,
                "Sorry something went wrong on the server."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handle all entity not found exceptions.
     *
     * @param e the dataNotFound exception.
     * @return ResponseEntity<ApiError>
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleDataNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(new ApiError(ApiResponseCode.NOT_FOUND, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * This method handle all validation errors (All object with @valid annotation).
     *
     * @param ex the MethodArgumentNotValidException.
     * @return ResponseEntity<ApiError>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ApiValidationError> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ApiValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return new ResponseEntity<>(new ApiError(REQUEST_VALIDATION_ERROR, "Invalid request.",
                validationErrors), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("Error while reading request: {}", ex.getLocalizedMessage());
        return new ResponseEntity<>(new ApiError(ApiResponseCode.BAD_REQUEST, ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handle custom validation error.
     *
     * @param e the ApplicationException.
     * @return ResponseEntity<ApiError>
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handleApplicationException(ApplicationException e) {
        return new ResponseEntity<>(new ApiError(ApiResponseCode.BAD_REQUEST, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Encodage par défaut des messages json
     */
    public static final Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * Send response as JSON.
     *
     * @param error    Api error to send.
     * @param response The http response.
     */
    public static void setJsonResponse(ApiError error, HttpServletResponse response, HttpStatus httpStatus) {
        response.setStatus(httpStatus.value());
        try {
            String json = mapper.writeValueAsString(error);
            response.setContentType("application/json");
            response.setCharacterEncoding(ENCODING.name());
            OutputStream os = response.getOutputStream();
            os.write(json.getBytes(ENCODING));
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
