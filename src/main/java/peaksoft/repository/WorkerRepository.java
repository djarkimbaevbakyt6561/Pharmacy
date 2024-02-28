package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Worker;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT w FROM Worker w ORDER BY w.salary ASC")
    List<Worker> sortWorkersBySalaryAsc();

    @Query("SELECT w FROM Worker w ORDER BY w.salary DESC")
    List<Worker> sortWorkersBySalaryDesc();

    @Query("SELECT AVG(w.salary) FROM Worker w")
    BigDecimal getMiddleSalaryOfWorkers();

}
