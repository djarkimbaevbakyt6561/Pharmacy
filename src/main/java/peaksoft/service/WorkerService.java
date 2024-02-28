package peaksoft.service;

import peaksoft.entities.Worker;

import java.math.BigDecimal;
import java.util.List;

public interface WorkerService {
    String saveWorker(Worker worker);

    Worker findWorkerById(Long workerId);

    String updateWorker(Long workerId, Worker worker);

    String deleteById(Long workerId);

    List<Worker> sortWorkersBySalary(String ascOrDesc);

    BigDecimal getMiddleSalaryOfWorkers();
}
