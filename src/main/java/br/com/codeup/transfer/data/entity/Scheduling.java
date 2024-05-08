package br.com.codeup.transfer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_account", nullable = false)
    private Account originAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account", nullable = false)
    private Account destinationAccount;

    @Column(name = "scheduling_date", nullable = false)
    private LocalDateTime schedulingDate;

    @Column(name = "data_transfer", nullable = false)
    private LocalDateTime dataTransfer;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "transfer_value", nullable = false)
    private BigDecimal transferValue;

    @Column(name = "processed", nullable = false)
    private Boolean processed;

    @PrePersist
    private void prePersist() {
        this.dataTransfer = LocalDateTime.now();
        this.processed = Boolean.FALSE;
    }

}