package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Medicine;
import peaksoft.service.MedicineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineAPI {
    private final MedicineService medicineService;
    @GetMapping("/{medicineId}")
    public Medicine getMedicineById(@PathVariable Long medicineId) {
        return medicineService.findMedicineById(medicineId);
    }
    @PostMapping
    public String save(@RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }
    @PostMapping("/addMedicineToPharmacy")
    public String addMedicineToPharmacy(@RequestParam("medicineId") Long medicineId, @RequestParam("pharmacyId") Long pharmacyId){
        return medicineService.addMedicineToPharmacy(medicineId, pharmacyId);
    }

    @PutMapping("/{medicineId}")
    public String update(@RequestBody Medicine medicine, @PathVariable Long medicineId) {
        return medicineService.updateMedicine(medicineId, medicine);
    }

    @DeleteMapping("/{medicineId}")
    public String delete(@PathVariable Long medicineId) {
        return medicineService.deleteById(medicineId);
    }
    @GetMapping("/search")
    public List<Medicine> searchByName(@RequestParam("name") String name){
        return medicineService.searchMedicinesByName(name);
    }
    @GetMapping("/sort")
    public List<Medicine> sortByName(@RequestParam("ascOrDesc") String acsOrDesc){
        return medicineService.sortByName(acsOrDesc);
    }
}
