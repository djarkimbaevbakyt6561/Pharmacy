package peaksoft.service;

import peaksoft.entities.Medicine;
import peaksoft.entities.Pharmacy;

import java.util.List;

public interface PharmacyService {
    Pharmacy savePharmacy(Pharmacy pharmacy);

    Pharmacy findPharmacyById(Long pharmacyId);

    String updatePharmacy(Long pharmacyId, Pharmacy pharmacy);

    String deleteById(Long pharmacyId);

    List<Medicine> getAllMedicinesByPharmacyId(Long pharmacyId);

    Pharmacy findPharmacyByWorkerId(Long workerId);

    List<Pharmacy> searchPharmaciesByName(String name);

    Pharmacy getPharmacyWithTheMostWorkers();
}
