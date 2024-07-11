/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.HocVien;
import com.until.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luuvi
 */
public class HocVienDao extends PolyDao<HocVien, Integer> {

    final String INSERT_SQL = """
                              INSERT INTO HocVien(MaHV, MaKH, MaNH, Diem)
                              VALUES (?, ?, ?, ?)
                              """;

    final String UPDATE_SQL = """
                              UPDATE HocVien
                              SET MaKH = ?, MaNH = ?, Diem = ?
                              WHERE MaHV = ?
                              """;

    final String DELETE_SQL = """
                              DELETE FROM HocVien
                              WHERE MaHV = ?
                              """;

    final String SELECT_ALL_SQL = """
                                  SELECT * FROM HocVien
                                  """;

    final String SELECT_BY_ID_SQL = """
                                    SELECT * FROM HocVien
                                    WHERE MaHV = ?
                                    """;

    @Override
    public void insert(HocVien entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaHV(), entity.getMaKH(), entity.getMaNH(), entity.getDiemTB());
    }

    @Override
    public void update(HocVien entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiemTB(), entity.getMaHV());

    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.value(DELETE_SQL, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<HocVien> selectBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet resultSet = jdbcHelper.query(sql, args);
            while (resultSet.next()) {
                HocVien entity = new HocVien();
                entity.setMaHV(resultSet.getInt("MaHV"));
                entity.setMaKH(resultSet.getInt("MaKH"));
                entity.setMaNH(resultSet.getString("MaNH"));
                entity.setDiemTB(resultSet.getDouble("Diem"));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }

    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = """
                     SELECT * FROM HocVien
                     WHERE MaKH = ?
                     """;
        return this.selectBySQL(sql, maKH);
    }

}
