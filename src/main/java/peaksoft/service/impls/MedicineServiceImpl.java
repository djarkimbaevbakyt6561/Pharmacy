package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Medicine;
import peaksoft.repository.MedicineRepository;
import peaksoft.service.MedicineService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public String saveMedicine(Medicine medicine) {
        if (medicine.getPrice() > 0) {
            medicineRepository.save(medicine);
            return "Successfully saved!";
        }
        return "Price must be greater than 0";
    }

    @Override
    public Medicine findMedicineById(Long medicineId) {
        try {
            return medicineRepository.findById(medicineId).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public String updateMedicine(Long medicineId, Medicine medicine) {
        Medicine foundMedicine = findMedicineById(medicineId);
        if (foundMedicine != null) {
            foundMedicine.setPrice(medicine.getPrice());
            foundMedicine.setName(medicine.getName());
            return "Successfully updated!";
        }
        return "Medicine with id = " + medicineId + " is not found";
    }

    @Override
    public String deleteById(Long medicineId) {
        Medicine foundMedicine = findMedicineById(medicineId);
        if (foundMedicine != null) {
            medicineRepository.deleteById(medicineId);
            return "Successfully deleted!";
        }
        return "Medicine with id = " + medicineId + " is not found";
    }

    @Override
    public List<Medicine> sortByName(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            return medicineRepository.sortByNameAsc();
        }
        if(ascOrDesc.equalsIgnoreCase("desc")){
            return medicineRepository.sortByNameDesc();
        }
        return null;
    }

    @Override
    public List<Medicine> searchMedicinesByName(String name) {
        return medicineRepository.searchMedicinesByName(name);
    }
}
