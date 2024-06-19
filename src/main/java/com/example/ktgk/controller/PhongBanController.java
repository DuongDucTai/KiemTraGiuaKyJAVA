package com.example.ktgk.controller;

import com.example.ktgk.model.NhanVien;
import com.example.ktgk.model.PhongBan;
import com.example.ktgk.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongBanService;

    @GetMapping("/phongbans/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongban", new PhongBan());
        return "/phongbans/add-phongban";
    }

    @PostMapping("/phongbans/add")
    public String addPhongBan(@Valid PhongBan phongBan, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongbans/add-phongban";
        }
        phongBanService.addPhongBan(phongBan);
        return "redirect:/phongbans";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("/phongbans")
    public String listPhongbans(Model model) {
        List<PhongBan> phongBans = phongBanService.getAllPhongban();
        model.addAttribute("phongbans", phongBans);
        return "/phongbans/phongban-list";
    }
    @GetMapping("/phongbans/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        PhongBan phongBan = phongBanService.getPhongbanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        model.addAttribute("phongban", phongBan);
        return "/phongbans/update-phongban";
    }
    // POST request to update category
    @PostMapping("/phongbans/update/{id}")
    public String updatePhongBan(@PathVariable("id") Long id, @Valid PhongBan phongBan,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongBan.setId(id);
            return "/phongbans/update-phongban";
        }
        phongBanService.updatePhongBan(phongBan);
        model.addAttribute("phongbans", phongBanService.getAllPhongban());
        return "redirect:/phongbans";
    }
    // GET request for deleting category
    @GetMapping("/phongbans/delete/{id}")
    public String deletePhongban(@PathVariable("id") Long id, Model model) {
        PhongBan phongBan = phongBanService.getPhongbanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        phongBanService.deleteById(id);
        model.addAttribute("phongbans", phongBanService.getAllPhongban());
        return "redirect:/phongbans";
    }
}