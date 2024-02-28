package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_gen")
    @SequenceGenerator(name = "med_gen", sequenceName = "med_seq", allocationSize = 1)
    private Long id;
    private String name;
    private int price;
    @ManyToMany
    @JoinTable(
            name = "medicine_pharmacy",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id"))
    private List<Pharmacy> pharmacies;
}
