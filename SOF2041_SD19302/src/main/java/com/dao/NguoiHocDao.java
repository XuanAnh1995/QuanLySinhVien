/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.NguoiHoc;
import com.until.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luuvi
 */
public class NguoiHocDao extends PolyDao<NguoiHoc, String> {

    final String INSERT_SQL = """
                              INSERT INTO NguoiHoc(MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK)
                              VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)
                              """;

    final String UPDATE_SQL = """
                              UPDATE NguoiHoc
                              SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ?, NgayDK = ?
                              WHERE MaNH = ?
                              """;

    final String DELETE_SQL = """
                              DELETE FROM NguoiHoc
                              WHERE MaNH = ?
                              """;

    final String SELECT_ALL = """
                              SELECT * FROM NguoiHoc
                              """;

    final String SELECT_BY_ID_SQL = """
                                     SELECT * FROM NguoiHoc
                                     WHERE MaNH = ?
                                     """;

    @Override
    public void insert(NguoiHoc entity) {
        jdbcHelper.update(INSERT_SQL,
                entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(), entity.getSoDT(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK());

    }

    @Override
    public void update(NguoiHoc entity) {
        jdbcHelper.update(UPDATE_SQL,
                entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(), entity.getSoDT(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK(), entity.getMaNH());

    }

    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();

        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setSoDT(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));

                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<NguoiHoc> selectByKeyword(String hoTen) {
        String sql = """
                        SELECT * FROM NguoiHoc
                        WHERE HoTen LIKE ?
                     """;
        return (ArrayList<NguoiHoc>) this.selectBySQL(sql, "%" + hoTen + "%");

    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql = """
                     SELECT * FROM NguoiHoc
                     WHERE HoTen LIKE ? AND MaNH NOT IN (
                                                        SELECT MaNH
                                                        FROM HocVien
                                                        WHERE MaKH = ?
                     )
                     """;

        return this.selectBySQL(sql, "%" + keyword + "%", keyword);
    }

}
