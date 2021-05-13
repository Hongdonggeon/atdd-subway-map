package wooteco.subway.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {LineController.class})
public class LineControllerAdvice {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateUniqueColumnException(DuplicateKeyException exception) {
        return ResponseEntity.badRequest()
                .body("이미 존재하는 노선 이름입니다.");
    }
}
