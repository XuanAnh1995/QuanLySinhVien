/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.until.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luuvi
 */
public class ThongKeDao {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet resultSet = jdbcHelper.query(sql, args);
            while (resultSet.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = resultSet.getObject(cols[i]);
                }

                list.add(vals);
            }

            resultSet.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Object[]> getBangDiem(Integer maKH) {
        String sql = "{CALL SP_BangDiem(?)}";
        String[] cols = {"MaNH", "HoTen", "Diem"};
        return getListOfArray(sql, cols, maKH);
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL SP_LuongNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL SP_DoanhThu(?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL SP_DiemChuyenDe}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return getListOfArray(sql, cols);
    }
}
