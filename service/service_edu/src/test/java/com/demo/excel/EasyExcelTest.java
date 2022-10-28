package com.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/6 23:59
 */
public class EasyExcelTest {


    //写入文件所在的路径
    static String fileName = "F:\\write.xlsx";



    //循环设置要添加的数据,最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }


    //往excel写数据
    @Test
    public void write(){

        //就是向write.xlsx中写入数据,数据形式是DemoData,表名是学生列表
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(data());

    }

    //读excel的数据
    @Test
    public void read(){

        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();


    }
}
