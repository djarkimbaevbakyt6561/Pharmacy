package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Medicine;
import peaksoft.entities.Pharmacy;
import peaksoft.repository.PharmacyRepository;
import peaksoft.service.PharmacyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    @Override
    public Pharmacy savePharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy findPharmacyById(Long pharmacyId) {
        try {
            return pharmacyRepository.findById(pharmacyId).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public String updatePharmacy(Long pharmacyId, Pharmacy pharmacy) {
        Pharmacy foundPharmacy = findPharmacyById(pharmacyId);
        if (foundPharmacy != null) {
            foundPharmacy.setAddress(pharmacy.getAddress());
            foundPharmacy.setName(pharmacy.getName());
            return "Successfully updated!";
        }
        return "Pharmacy with id = " + pharmacyId + " is not found";
    }

    @Override
    public String deleteById(Long pharmacyId) {
        Pharmacy foundPharmacy = findPharmacyById(pharmacyId);
        if (foundPharmacy != null) {
            pharmacyRepository.deleteById(pharmacyId);
            return "Successfully deleted!";
        }
        return "Pharmacy with id = " + pharmacyId + " is not found";
    }

    @Override
    public List<Medicine> getAllMedicinesByPharmacyId(Long pharmacyId) {
        Pharmacy foundPharmacy = findPharmacyById(pharmacyId);
        if (foundPharmacy != null) {
            return pharmacyRepository.getAllMedicinesByPharmacyId(pharmacyId);
        }
        return null;
    }

    @Override
    public Pharmacy findPharmacyByWorkerId(Long workerId) {
        try {
            return pharmacyRepository.findPharmacyByWorkerId(workerId);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Pharmacy> searchPharmaciesByName(String name) {
        return pharmacyRepository.searchPharmaciesByName(name);
    }

    @Override
    public Pharmacy getPharmacyWithTheMostWorkers() {
        return pharmacyRepository.getPharmacyWithTheMostWorkers();
    }
}
