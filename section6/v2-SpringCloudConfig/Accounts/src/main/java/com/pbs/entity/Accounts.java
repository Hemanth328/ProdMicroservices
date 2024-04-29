package com.pbs.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accounts extends BaseEntity {

    @Column(name="customer_id")
    private Long customerId;

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
