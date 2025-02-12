package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.model.OperationType;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RequestOperation {
    private UUID id;
    private OperationType operationType;
    @NonNull
    @Positive
    private BigDecimal amount;
}
