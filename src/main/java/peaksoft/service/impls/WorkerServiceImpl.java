package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Worker;
import peaksoft.repository.WorkerRepository;
import peaksoft.service.WorkerService;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    @Override
    public String saveWorker(Worker worker) {
        try {
            workerRepository.save(worker);
            return "Successfully saved";
        } catch (Exception e) {
            return "Email must be unique!";
        }
    }

    @Override
    public Worker findWorkerById(Long workerId) {
        try {
            return workerRepository.findById(workerId).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public String updateWorker(Long workerId, Worker worker) {
        Worker foundWorker = findWorkerById(workerId);
        if (foundWorker != null) {
            foundWorker.setAddress(worker.getAddress());
            foundWorker.setName(worker.getName());
            foundWorker.setEmail(worker.getEmail());
            foundWorker.setDateOfBirth(worker.getDateOfBirth());
            foundWorker.setSalary(worker.getSalary());
            foundWorker.setPharmacy(worker.getPharmacy());
            return "Successfully updated!";
        }
        return "Worker with id = " + workerId + " is not found";
    }

    @Override
    public String deleteById(Long workerId) {
        Worker foundWorker = findWorkerById(workerId);
        if (foundWorker != null) {
            workerRepository.deleteById(workerId);
            return "Successfully deleted!";
        }
        return "Worker with id = " + workerId + " is not found";
    }

    @Override
    public List<Worker> sortWorkersBySalary(String ascOrDesc) {
        if(ascOrDesc.equalsIgnoreCase("asc")){
            return workerRepository.sortWorkersBySalaryAsc();
        }
        if(ascOrDesc.equalsIgnoreCase("desc")){
            return workerRepository.sortWorkersBySalaryDesc();
        }
        return null;
    }

    @Override
    public BigDecimal getMiddleSalaryOfWorkers() {
        return workerRepository.getMiddleSalaryOfWorkers();
    }
}
