package com.example.ktgk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "nhanvien")
public class NhanVien {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank(message = "Mã Nhân Viên là bắt buộc")
        private String maNV;
        @NotBlank(message = "Tên là bắt buộc")
        private String ten;
        private String phai;
        private String noiSinh;

        private double luong;
        @ManyToOne
        @JoinColumn(name = "phongban_id")
        private PhongBan phongBan;
}
