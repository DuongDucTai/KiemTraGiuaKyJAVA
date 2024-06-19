package com.example.ktgk.service;

import com.example.ktgk.model.PhongBan;
import com.example.ktgk.repository.PhongBanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<PhongBan> getAllPhongban() {
        return phongBanRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<PhongBan> getPhongbanById(Long id) {
        return phongBanRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param phongBan the category to add
     */
    public void addPhongBan(PhongBan phongBan) {
        phongBanRepository.save(phongBan);
    }
    /**
     * Update an existing category.
     * @param phongBan the category with updated information
     */
    public void updatePhongBan(@NotNull PhongBan phongBan) {
        PhongBan existingPhongban = phongBanRepository.findById(phongBan.getId())
                .orElseThrow(() -> new IllegalStateException("PhongBan with ID " +
                        phongBan.getId() + " does not exist."));
        existingPhongban.setMaPhong(phongBan.getMaPhong());
        existingPhongban.setName(phongBan.getName());
        phongBanRepository.save(existingPhongban);
    }
    /**
     * Delete a category by its id.
     * @param id the id of the category to delete
     */
    public void deleteById(Long id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("PhongBan with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}