package br.com.codeup.transfer.controller.model;

import br.com.codeup.transfer.util.format.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    @NotBlank(message = "deve ser informado.")
    private String originAccount;

    @NotBlank(message = "deve ser informado.")
    private String destinationAccount;

    @NotNull(message = "deve ser informado.")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataTransfer;

    @NotNull(message = "deve ser informado.")
    @Min(value = 1, message = "deve ser maio que zero.")
    private BigDecimal transferValue;

}