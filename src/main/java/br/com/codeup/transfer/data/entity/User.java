package br.com.codeup.transfer.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document_number", length = 11, unique = true, nullable = false)
    private String documentNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Account> accounts;

}