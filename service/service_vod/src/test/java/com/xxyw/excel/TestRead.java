package com.xxyw.excel;

import com.alibaba.excel.EasyExcel;

public class TestRead {
    public static void main(String[] args) {

        String fileName = "D:/tmp/我的表格.xlsx";
        EasyExcel.read(fileName,User.class, new ExcelListener()).sheet().doRead();
    }
}
