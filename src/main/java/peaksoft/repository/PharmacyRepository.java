package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Medicine;
import peaksoft.entities.Pharmacy;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    @Query("SELECT ph.medicines FROM Pharmacy ph WHERE ph.id = ?1")
    List<Medicine> getAllMedicinesByPharmacyId(Long pharmacyId);

    @Query("SELECT w.pharmacy FROM Worker w WHERE w.id = ?1")
    Pharmacy findPharmacyByWorkerId(Long workerId);

    List<Pharmacy> searchPharmaciesByName(String name);

    @Query("SELECT p FROM Pharmacy p ORDER BY SIZE(p.workers) DESC")
    Pharmacy getPharmacyWithTheMostWorkers();

}
