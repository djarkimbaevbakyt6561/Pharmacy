package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pharmacies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_gen")
    @SequenceGenerator(name = "pharmacy_gen", sequenceName = "pharmacy_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    @ManyToMany(mappedBy = "pharmacies", cascade = CascadeType.REMOVE)
    private List<Medicine> medicines;
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.REMOVE)
    private List<Worker> workers;
}
