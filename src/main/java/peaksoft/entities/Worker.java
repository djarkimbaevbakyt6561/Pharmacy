package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_gen")
    @SequenceGenerator(name = "worker_gen", sequenceName = "worker_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private BigDecimal salary;
    private String address;
    private ZonedDateTime dateOfBirth;
    @ManyToOne
    private Pharmacy pharmacy;
}
