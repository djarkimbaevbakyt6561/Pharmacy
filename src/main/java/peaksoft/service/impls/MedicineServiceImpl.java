package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Medicine;
import peaksoft.entities.Pharmacy;
import peaksoft.repository.MedicineRepository;
import peaksoft.repository.PharmacyRepository;
import peaksoft.service.MedicineService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;

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

    @Override
    @Transactional
    public String addMedicineToPharmacy(Long medicineId, Long pharmacyId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new NoSuchElementException("Medicine not found with id: " + medicineId));
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new NoSuchElementException("Pharmacy not found with id: " + pharmacyId));
        if (medicine != null && pharmacy != null) {
            pharmacy.getMedicines().add(medicine);
            medicine.getPharmacies().add(pharmacy);
            return "Medicine added to pharmacy successfully!";
        } else {
            return "Medicine or pharmacy not found!";
        }
    }

}
