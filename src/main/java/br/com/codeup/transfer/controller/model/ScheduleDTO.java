package br.com.codeup.transfer.controller.model;

import br.com.codeup.transfer.util.format.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private LocalDateTime schedulingDate;

    @NotNull(message = "deve ser informado.")
    @Min(value = 1, message = "deve ser maio que zero.")
    private BigDecimal transferValue;

}