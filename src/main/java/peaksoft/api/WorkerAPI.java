package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Worker;
import peaksoft.service.WorkerService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/worker")
public class WorkerAPI {
    private final WorkerService workerService;

    @GetMapping("/{workerId}")
    public Worker getWorkerById(@PathVariable Long workerId) {
        return workerService.findWorkerById(workerId);
    }

    @PostMapping
    public String save(@RequestBody Worker worker) {
        return workerService.saveWorker(worker);
    }

    @PutMapping("/{workerId}")
    public String update(@RequestBody Worker worker, @PathVariable Long workerId) {
        return workerService.updateWorker(workerId, worker);
    }

    @DeleteMapping("/{workerId}")
    public String delete(@PathVariable Long workerId) {
        return workerService.deleteById(workerId);
    }

    @GetMapping("/sort")
    public List<Worker> sortWorkersBySalary(@RequestParam("ascOrDesc") String acsOrDesc) {
        return workerService.sortWorkersBySalary(acsOrDesc);
    }

    @GetMapping("/middleSalary")
    public BigDecimal getMiddleSalary() {
        return workerService.getMiddleSalaryOfWorkers();
    }
}
