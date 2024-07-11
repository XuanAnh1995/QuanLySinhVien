/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.NhanVien;
import com.until.jdbcHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luuvi
 */
public class NhanVienDao extends PolyDao<NhanVien, String> {
    
    final String INSERT_SQL = """
                              INSERT INTO NhanVien(MaNV, MatKhau, HoTen, VaiTro)
                              VALUES (?, ?, ?, ?)
                              """;
    
    final String UPDATE_SQL = """
                              UPDATE NhanVien
                              SET MatKhau = ?, HoTen = ?, VaiTro = ?
                              WHERE MaNV = ?
                              """;
    
    final String DELETE_SQL = """
                              DELETE FROM NhanVien
                              WHERE MaNV = ?
                              """;
    
    final String SELECT_ALL_SQL = """
                                  SELECT * FROM NhanVien
                                  """;
    
    final String SELECT_BY_ID_SQL = """
                                    SELECT * FROM NhanVien
                                    WHERE MaNV = ?
                                    """;
    
    @Override
    public void insert(NhanVien entity) {
        jdbcHelper.update(INSERT_SQL,
                entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro());
    }
    
    @Override
    public void update(NhanVien entity) {
        jdbcHelper.update(UPDATE_SQL,
                entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro(), entity.getMaNV());
    }
    
    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL,
                id);
        
    }
    
    @Override
    public List<NhanVien> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }
    
    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }        
        return list.get(0);
    }
    
    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
