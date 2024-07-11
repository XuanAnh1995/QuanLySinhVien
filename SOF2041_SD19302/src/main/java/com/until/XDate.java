/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.until;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Chuyển đổi từ Date sang String
 * 
 * date: là Date cần chuyển đổi
 * pattern: là định dạng thời gian
 * String: là kết quả
 * @author luuvi
 */
public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    
    public static Date toDate(String date, String pattern){
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static Date now(){
        return new Date();
    }
    
    public static String toString(Date date, String pattern){
            formater.applyPattern(pattern);
            return formater.format(date);
    }
    /*
    Bổ sung số ngày vào thời gian
    date: thời gian hiện có
    days: số ngày bổ sung vào date
    Date: kết quả
    */
    public static Date addDays(Date date, long days){
            date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
            return date;
    }
}
