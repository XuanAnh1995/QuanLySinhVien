/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.until;

import com.entity.NhanVien;

/**
 *
 * @author luuvi
 */
public class Auth {
    // Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static NhanVien user = null;
    // Xóa thông tin của người dùng khi người dùng đăng xuất
    public static void clear() {
        Auth.user = null;
    }
    // Kiểm tra xem dăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }
    // Kiểm tra xem đăng nhập với vai trò gì?
    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    }

}
