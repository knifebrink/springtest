package com.spring.test.chapter6.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.sax.handler.RowHandler;

import java.io.*;
import java.util.List;

public class HutoolTest {

    public static void main(String[] args){
        System.out.println("111()".replace("()",""));
//        testHutool();
        excelReadTest();
//        excelWriteTest();
        excelWriteTest3();
    }


    private static void testHutool(){
        System.out.println(ObjectUtil.isNull(null));
        System.out.println(StrUtil.isEmpty(""));
    }

    /**
     * 测试excel 读
     */
    private static void excelReadTest(){
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("D:/test/employee_record.xlsx"));
//        reader = ExcelUtil.getReader(FileUtil.file("D:\\test.xlsx"), "sheet1");
        // 读取大数据量的Excel
//        ExcelUtil.readBySax("aaa.xlsx", 0, createRowHandler());
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);
    }

    private static void excelWriteTest(){
        TestBean bean1 = new TestBean();
        bean1.setName("张三");
        bean1.setAge(22);
        bean1.setPass(true);
        bean1.setScore(66.30);
        bean1.setExamDate(DateUtil.date());

        TestBean bean2 = new TestBean();
        bean2.setName("李四");
        bean2.setAge(28);
        bean2.setPass(false);
        bean2.setScore(38.50);
        bean2.setExamDate(DateUtil.date());

        List<TestBean> rows = CollUtil.newArrayList(bean1, bean2);


        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
//        writer.merge(4, "一班成绩单");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.passRows(7);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();

    }


    private static void excelWriteTest2(){
        TestBean bean1 = new TestBean();
        bean1.setName("张三");
        bean1.setAge(22);
        bean1.setPass(true);
        bean1.setScore(66.30);
        bean1.setExamDate(DateUtil.date());

        TestBean bean2 = new TestBean();
        bean2.setName("李四");
        bean2.setAge(28);
        bean2.setPass(false);
        bean2.setScore(38.50);
        bean2.setExamDate(DateUtil.date());

        List<TestBean> rows = CollUtil.newArrayList(bean1, bean2);

        ExcelWriter writer = new ExcelWriter(true);
        writer.write(rows, true);
        File file = new File("D:\\test\\1.xlsx");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.flush(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void excelWriteTest3(){




        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("D:/test/test.xlsx"));
        ExcelWriter writer = reader.getWriter();
        writer.setStyleSet(null);
        writer.writeCellValue("B2","这是B2");
        writer.writeCellValue("B6","这是B6");
        writer.writeCellValue("B10","这是B10");
        writer.writeCellValue("A1","这是A1");
        writer.writeCellValue("C4","C4");
        try {
            writer.flush(new FileOutputStream(new File("D:/test/testC.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static RowHandler createRowHandler() {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowlist) {
                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
            }
        };
    }
}
