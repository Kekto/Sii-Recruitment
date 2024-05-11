package com.example.siirecruitmenttask.promotionalCode;

import com.example.siirecruitmenttask.exception.PromotionalCodeAlreadyExistsException;
import com.example.siirecruitmenttask.exception.PromotionalCodeNotFoundException;
import com.example.siirecruitmenttask.promotionalCode.controller.model.PromotionalCodeRequest;
import com.example.siirecruitmenttask.promotionalCode.controller.model.PromotionalCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionalCodeService {

    private final PromotionalCodeRepository promotionalCodeRepository;

    public List<PromotionalCodeEntity> getAllPromotionalCodes() {
        return promotionalCodeRepository.findAll();
    }

    public PromotionalCodeResponse getPromotionalCodeByName(String name) throws PromotionalCodeNotFoundException {

        PromotionalCodeEntity promotionalCodeEntity = promotionalCodeRepository.findByName(name).orElseThrow(() -> new PromotionalCodeNotFoundException());

        return new PromotionalCodeResponse(promotionalCodeEntity, "Promotional code found successfully");
    }

    public PromotionalCodeResponse addPromotionalCode(PromotionalCodeRequest promotionalCodeRequest) throws PromotionalCodeAlreadyExistsException {

        Optional<PromotionalCodeEntity> optionalDuplicatePromotionalCodeEntity = promotionalCodeRepository.findByName(promotionalCodeRequest.name());
        if (optionalDuplicatePromotionalCodeEntity.isPresent() && Objects.equals(promotionalCodeRequest.name(), optionalDuplicatePromotionalCodeEntity.get().getName())) {
            throw new PromotionalCodeAlreadyExistsException();
        }

        PromotionalCodeEntity promotionalCodeEntity = new PromotionalCodeEntity();
        promotionalCodeEntity.setName(promotionalCodeRequest.name());
        promotionalCodeEntity.setAmount(promotionalCodeRequest.amount());
        promotionalCodeEntity.setCurrency(promotionalCodeRequest.currency());
        promotionalCodeEntity.setExpirationDate(promotionalCodeRequest.expirationDate());
        promotionalCodeEntity.setPercentage(promotionalCodeRequest.percentage());
        promotionalCodeEntity.setRemainingUses(promotionalCodeRequest.remainingUses());

        promotionalCodeRepository.save(promotionalCodeEntity);
        return new PromotionalCodeResponse(promotionalCodeEntity, "Promotional code successfully added");
    }

    public PromotionalCodeResponse editPromotionalCode(Long id, PromotionalCodeRequest promotionalCodeRequest) throws PromotionalCodeNotFoundException {

        PromotionalCodeEntity promotionalCodeEntity = promotionalCodeRepository.findById(id).orElseThrow(() -> new PromotionalCodeNotFoundException());

        promotionalCodeEntity.setName(promotionalCodeRequest.name());
        promotionalCodeEntity.setExpirationDate(promotionalCodeRequest.expirationDate());
        promotionalCodeEntity.setAmount(promotionalCodeRequest.amount());
        promotionalCodeEntity.setCurrency(promotionalCodeRequest.currency());
        promotionalCodeEntity.setRemainingUses(promotionalCodeRequest.remainingUses());
        promotionalCodeEntity.setPercentage(promotionalCodeRequest.percentage());

        promotionalCodeRepository.save(promotionalCodeEntity);

        return new PromotionalCodeResponse(promotionalCodeEntity, "Promotional code saved successfully");
    }

    public PromotionalCodeResponse deletePromotionalCode(Long id) throws PromotionalCodeNotFoundException {

        PromotionalCodeEntity promotionalCodeEntity = promotionalCodeRepository.findById(id).orElseThrow(() -> new PromotionalCodeNotFoundException());
        promotionalCodeRepository.deleteById(id);

        return new PromotionalCodeResponse(promotionalCodeEntity, "Promotional code deleted successfully");
    }

    public void save(PromotionalCodeEntity promotionalCodeEntity) {
        promotionalCodeRepository.save(promotionalCodeEntity);
    }

    public PromotionalCodeEntity findByName(String promotionalCodeName) throws PromotionalCodeNotFoundException {
        return promotionalCodeRepository.findByName(promotionalCodeName).orElseThrow(() -> new PromotionalCodeNotFoundException());
    }
}
