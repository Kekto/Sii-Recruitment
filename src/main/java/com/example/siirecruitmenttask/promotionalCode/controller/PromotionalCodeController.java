package com.example.siirecruitmenttask.promotionalCode.controller;

import com.example.siirecruitmenttask.exception.PromotionalCodeAlreadyExistsException;
import com.example.siirecruitmenttask.exception.PromotionalCodeInvalidDataException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNameLengthInvalidException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNotFoundException;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeService;
import com.example.siirecruitmenttask.promotionalCode.PromotionalCodeEntity;
import com.example.siirecruitmenttask.promotionalCode.controller.model.PromotionalCodeRequest;
import com.example.siirecruitmenttask.promotionalCode.controller.model.PromotionalCodeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = PromotionalCodeController.PROMOTIONAL_CODE_ENDPOINT_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class PromotionalCodeController {
    static final String PROMOTIONAL_CODE_ENDPOINT_V1 = "/api/v1/promocodes";
    private final PromotionalCodeService promotionalCodeService;

    @GetMapping
    public ResponseEntity<Iterable<PromotionalCodeEntity>> getAllPromotionalCodes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(promotionalCodeService.getAllPromotionalCodes());
    }

    @GetMapping("{name}")
    public ResponseEntity<PromotionalCodeResponse> getPromotionalCodeByName(@PathVariable String name) {

        try {
            var getPromotionalCodeByName = promotionalCodeService.getPromotionalCodeByName(name);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(getPromotionalCodeByName);
        } catch (PromotionalCodeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PostMapping
    public ResponseEntity<PromotionalCodeResponse> addPromotionalCode(@RequestBody @Valid PromotionalCodeRequest promotionalCodeRequest) {

        try {
            var addPromotionalCode = promotionalCodeService.addPromotionalCode(promotionalCodeRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(addPromotionalCode);
        } catch (PromotionalCodeNameLengthInvalidException | PromotionalCodeInvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        } catch (PromotionalCodeAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.FOUND, exception.getMessage(), exception);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionalCodeResponse> editPromotionalCode(@PathVariable Long id, @RequestBody @Valid PromotionalCodeRequest promotionalCodeRequest) {

        try {
            var editPromotionalCode = promotionalCodeService.editPromotionalCode(id, promotionalCodeRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(editPromotionalCode);
        } catch (PromotionalCodeNameLengthInvalidException | PromotionalCodeInvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        } catch (PromotionalCodeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PromotionalCodeResponse> deletePromotionalCode(@PathVariable Long id) {

        try {
            var deletePromotionalCode = promotionalCodeService.deletePromotionalCode(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(deletePromotionalCode);
        } catch (PromotionalCodeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
