package peaksoft.service;

import peaksoft.entities.Medicine;

import java.util.List;

public interface MedicineService {
    String saveMedicine(Medicine medicine);

    Medicine findMedicineById(Long medicineId);

    String updateMedicine(Long medicineId, Medicine medicine);

    String deleteById(Long medicineId);

    List<Medicine> sortByName(String ascOrDesc);

    List<Medicine> searchMedicinesByName(String name);
}
