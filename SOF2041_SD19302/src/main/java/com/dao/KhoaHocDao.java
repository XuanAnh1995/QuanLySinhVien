/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.KhoaHoc;
import com.until.XJdbc;
import com.until.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author luuvi
 */
public class KhoaHocDao extends PolyDao<KhoaHoc, Integer> {

    final String INSERT_SQL = """
                              INSERT INTO KhoaHoc(MaKH, MaCD, HocPhi, ThoiLuong, NgayDK, GhiChu, MaNV, NgayTao)
                              VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                              """;

    final String UPDATE_SQL = """
                              UPDATE KhoaHoc
                              SET MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayDK = ?, GhiChu = ?, MaNV = ?, NgayTao = ?
                              WHERE MaKH = ?
                              """;

    final String DELETE_SQL = """
                              DELETE FROM KhoaHoc
                              WHERE MaKH = ?
                              """;

    final String SELECT_ALL = """
                              SELECT * FROM KhoaHoc
                              """;

    final String SELECT_BY_ID_SQL = """
                                     SELECT * FROM KhoaHoc
                                     WHERE MaKH = ?
                                     """;

    @Override
    public void insert(KhoaHoc entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

    @Override
    public List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet resultSet = jdbcHelper.query(sql, args);
            while (resultSet.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(resultSet.getInt("MaKH"));
                entity.setMaCD(resultSet.getString("MaCD"));
                entity.setHocPhi(resultSet.getDouble("HocPhi"));
                entity.setThoiLuong(resultSet.getInt("ThoiLuong"));
                entity.setNgayKG(resultSet.getDate("NgayDK"));
                entity.setGhiChu(resultSet.getString("GhiChu"));
                entity.setMaNV(resultSet.getString("MaNV"));
                entity.setNgayTao(resultSet.getDate("NgayTao"));

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }

    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String sql = """
                     SELECT * FROM KhoaHoc
                     WHERE MaCD = ?
                     """;
        return this.selectBySQL(sql, macd);
    }

    public List<Integer> selectYears() {
        String sql = """
                     SELECT DISTINCT YEAR(NgayKG) 
                     FROM KhoaHoc
                     ORDER BY YEAR(NgayKG) DESC
                     """;
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
