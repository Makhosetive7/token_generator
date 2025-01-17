package com.example.ElectricityTokenGenerator.entity.Tokens;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOKEN TRANSFER DATABASE TABLE")
public class TokenTransferEntity {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "Receiver", referencedColumnName ="UserID")
    private UserEntities receiverAccountNumber;
    
    @ManyToOne
    @JoinColumn(name = "Sender", referencedColumnName ="UserID")
    private UserEntities senderAccountNumber;

    @Column(name = "TokenTransferId")
    private Long TransferTokenId;

    @Column(name = "kiloWatts")
    private Double kiloWatts;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;


}
