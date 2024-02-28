package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Medicine;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query("SELECT m FROM Medicine m ORDER BY m.name ASC")
    List<Medicine> sortByNameAsc();

    @Query("SELECT m FROM Medicine m ORDER BY m.name DESC")
    List<Medicine> sortByNameDesc();

    List<Medicine> searchMedicinesByName(String name);
}
