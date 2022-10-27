package com.xxyw.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestCreate {
    public static void main(String[] args) {

        String fileName = "D:/tmp/我的表格.xlsx";

        List<User> data = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setName("小名" + i);

            data.add(user);
        }

        EasyExcel.write(fileName, User.class).sheet("我的sheet").doWrite(data);
    }
}
