package br.com.sgcore.sgcore_cloud.modules.core.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalApiErrorPayload> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        log.warn("Entity not found | uri={} | message={}", request.getRequestURI(), ex.getMessage());

        HttpStatus errorHttpStatus = HttpStatus.NOT_FOUND;

        GlobalApiErrorPayload error = new GlobalApiErrorPayload();
        error.setOccurredAt(System.currentTimeMillis());
        error.setHttpStatus(errorHttpStatus.value());
        error.setTitle("ENTITY NOT FOUND");
        error.setDetail(ex.getMessage());
        error.setRequestUri(request.getRequestURI());

        return ResponseEntity.status(errorHttpStatus).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GlobalApiErrorPayload> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.warn("Data Integrity Violation | uri={} | message={}", request.getRequestURI(), ex.getMessage());

        HttpStatus errorHttpStatus = HttpStatus.CONFLICT;

        GlobalApiErrorPayload error = new GlobalApiErrorPayload();
        error.setOccurredAt(System.currentTimeMillis());
        error.setHttpStatus(errorHttpStatus.value());
        error.setTitle("CONFLICT");
        error.setDetail("The operation could not be completed because one or more related resources were not found or are invalid." +
                "Please verify the provided information and try again.");
        error.setRequestUri(request.getRequestURI());

        return ResponseEntity.status(errorHttpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalApiErrorPayload> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus errorHttpStatus = HttpStatus.BAD_REQUEST;

        String detail = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.warn("Validation error | uri={} | errors={}", request.getRequestURI(), detail);

        GlobalApiErrorPayload error = new GlobalApiErrorPayload();
        error.setOccurredAt(System.currentTimeMillis());
        error.setHttpStatus(errorHttpStatus.value());
        error.setTitle("VALIDATION ERROR");
        error.setDetail(detail);
        error.setRequestUri(request.getRequestURI());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalApiErrorPayload> handleGeneric(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error | uri={}", request.getRequestURI(), ex);

        HttpStatus errorHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        GlobalApiErrorPayload error = new GlobalApiErrorPayload();
        error.setOccurredAt(System.currentTimeMillis());
        error.setHttpStatus(errorHttpStatus.value());
        error.setTitle("INTERNAL SERVER ERROR");
        error.setDetail("Unexpected internal error");
        error.setRequestUri(request.getRequestURI());

        return ResponseEntity.status(errorHttpStatus).body(error);
    }
}
