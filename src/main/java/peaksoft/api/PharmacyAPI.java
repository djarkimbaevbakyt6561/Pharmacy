package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Medicine;
import peaksoft.entities.Pharmacy;
import peaksoft.service.PharmacyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pharmacy")
public class PharmacyAPI {
    private final PharmacyService pharmacyService;

    @GetMapping("/{pharmacyId}")
    public Pharmacy getPharmacyById(@PathVariable Long pharmacyId) {
        return pharmacyService.findPharmacyById(pharmacyId);
    }

    @PostMapping
    public Pharmacy save(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.savePharmacy(pharmacy);
    }

    @PutMapping("/{pharmacyId}")
    public String update(@RequestBody Pharmacy pharmacy, @PathVariable Long pharmacyId) {
        return pharmacyService.updatePharmacy(pharmacyId, pharmacy);
    }

    @DeleteMapping("/{pharmacyId}")
    public String delete(@PathVariable Long pharmacyId) {
        return pharmacyService.deleteById(pharmacyId);
    }
    @GetMapping("/allMedicines/{pharmacyId}")
    public List<Medicine> getAllMedicineByPharmacyId(@PathVariable Long pharmacyId){
        return pharmacyService.getAllMedicinesByPharmacyId(pharmacyId);
    }
    @GetMapping("/pharmacyByWorkerId/{workerId}")
    public Pharmacy getPharmacyByWorkerId(@PathVariable Long workerId){
        return pharmacyService.findPharmacyByWorkerId(workerId);
    }
    @GetMapping("/search")
    public List<Pharmacy> searchByName(@RequestParam("name") String name){
        return pharmacyService.searchPharmaciesByName(name);
    }
    @GetMapping("/pharmacyWithTheMostWorkers")
    public Pharmacy findPharmacyWithTheMostWorkers(){
        return pharmacyService.getPharmacyWithTheMostWorkers();
    }

}