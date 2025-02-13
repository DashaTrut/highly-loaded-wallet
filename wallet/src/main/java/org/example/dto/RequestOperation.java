package org.example.dto;

import lombok.*;
import org.example.model.OperationType;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOperation {
    private UUID id;
    private OperationType operationType;
    @NonNull
    @Positive
    private BigDecimal amount;
}
