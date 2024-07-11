/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.ChuyenDe;
import com.until.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luuvi
 */
public class ChuyenDeDao extends PolyDao<ChuyenDe, String> {

    final String INSERT_SQL = """
                              INSERT INTO ChuyenDe(MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa)
                              VALUES (?, ?, ?, ?, ?, ?)
                              """;

    final String UPDATE_SQL = """
                              UPDATE ChuyenDe
                              SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ?
                              WHERE MaCD = ?
                              """;

    final String DELETE_SQL = """
                              DELETE FROM ChuyenDe
                              WHERE MaCD = ?
                              """;

    final String SELECT_ALL_SQL = """
                                  SELECT * FROM ChuyenDe
                                  """;

    final String SELECT_BY_ID_SQL = """
                                    SELECT * FROM ChuyenDe
                                    WHERE MaCD = ?
                                    """;

    @Override
    public void insert(ChuyenDe entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        } 
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet resultSet = jdbcHelper.query(sql, args);
            while (resultSet.next()) {
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(resultSet.getString(1));
                entity.setTenCD(resultSet.getString(2));
                entity.setHocPhi(resultSet.getDouble(3));
                entity.setThoiLuong(resultSet.getInt(4));
                entity.setHinh(resultSet.getString(5));
                entity.setMoTa(resultSet.getString(6));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }

}
