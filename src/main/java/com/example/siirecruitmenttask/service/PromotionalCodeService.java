package com.example.siirecruitmenttask.service;

import com.example.siirecruitmenttask.model.PromotionalCode;
import com.example.siirecruitmenttask.repository.PromotionalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PromotionalCodeService {

    @Autowired
    private PromotionalCodeRepository promotionalCodeRepository;

    public ResponseEntity<?> getAllPromotionalCodes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(promotionalCodeRepository.findAll());
    }

    public ResponseEntity<?> getPromotionalCodeByCode(String code) {
        PromotionalCode promocode = promotionalCodeRepository.findByCode(code);

        if(promocode != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(promocode);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Promotional Code not found");
    }

    public ResponseEntity<?> addPromotionalCode(PromotionalCode promotionalCode) {
        if( promotionalCode.getCode().isEmpty()
                || promotionalCode.getCurrency().isEmpty()
                || promotionalCode.getAmount().compareTo(BigDecimal.valueOf(1)) < 0
                || promotionalCode.getExpirationDate().toString().isEmpty()
                || promotionalCode.getRemainingUses() < 0 ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid data");
        }

        if(promotionalCode.getCode().length() < 3 || promotionalCode.getCode().length() > 24) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Code must have between 3 and 24 letters");
        }

        promotionalCodeRepository.save(promotionalCode);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Promotional Code has been added");
    }

    public ResponseEntity<?> editPromotionalCode(Long id, PromotionalCode promotionalCode) {
        if( promotionalCode.getCode().isEmpty()
                || promotionalCode.getCurrency().isEmpty()
                || promotionalCode.getAmount().compareTo(BigDecimal.valueOf(1)) < 0
                || promotionalCode.getExpirationDate().toString().isEmpty()
                || promotionalCode.getRemainingUses() < 0 ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid data");
        }

        if(promotionalCode.getCode().length() < 3 || promotionalCode.getCode().length() > 24) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Code must have between 3 and 24 letters");
        }

        Optional<PromotionalCode> optional = promotionalCodeRepository.findById(Math.toIntExact(id));
        PromotionalCode promotionalCodeTemp = optional.get();

        promotionalCodeTemp.setCode(promotionalCode.getCode());
        promotionalCodeTemp.setExpirationDate(promotionalCode.getExpirationDate());
        promotionalCodeTemp.setAmount(promotionalCode.getAmount());
        promotionalCodeTemp.setCurrency(promotionalCode.getCurrency());
        promotionalCodeTemp.setRemainingUses(promotionalCode.getRemainingUses());
        promotionalCodeTemp.setIsPercantage(promotionalCode.getIsPercantage());

        promotionalCodeRepository.save(promotionalCodeTemp);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Promotional Code saved successfully");
    }

    public ResponseEntity<?> deletePromotionalCode(Long id) {
        if(promotionalCodeRepository.existsById(Math.toIntExact(id))){
            promotionalCodeRepository.deleteById(Math.toIntExact(id));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Promotional Code has been deleted");
        };
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Promotional Code with this id does not exist");
    }
}
