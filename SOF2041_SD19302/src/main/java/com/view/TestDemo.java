/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view;

import com.dao.NhanVienDao;
import com.dao.ThongKeDao;
import com.entity.NhanVien;
import java.util.List;

/**
 *
 * @author luuvi
 */
public class TestDemo {

    public static void main(String[] args) {
//        NhanVienDao nvd = new NhanVienDao();
//        
//        nvd.insert(new NhanVien("NV100", "password100", "Lưu Việt Tiến", true));

        ThongKeDao tkd = new ThongKeDao();

        List<Object[]> list = tkd.getLuongNguoiHoc();

        for (Object[] obj : list) {
            System.out.println("=> " + obj[0] + "---" + obj[1] + "---" + obj[2] + "---" + obj[3]);
        }

    }
}
