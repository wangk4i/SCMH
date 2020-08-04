package com.hyd.exportexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.hyd.exportexcel.service.AnnualorganauditExportExcelUtil;
import com.hyd.exportexcel.vo.AnnualorganauditVo;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class AnnualorganauditVoAnnualorganauditExportExcelUtilimpl implements AnnualorganauditExportExcelUtil {

    //根据指定的excel模板导出数据
    public void exportExcel(String desFilePath, InputStream fis, String title, List<AnnualorganauditVo> list, HttpServletResponse response,String yearDateStart) throws IOException {
        ServletOutputStream out = null;
        try{
            out=response.getOutputStream();

            response.reset();
            response.setContentType("application/x-msdownload");
            // 设置头消息
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(desFilePath.getBytes("utf-8"), "iso-8859-1"));
//            // 头的策略
//            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//            // 背景设置为红色
//            headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
//            WriteFont headWriteFont = new WriteFont();
//            headWriteFont.setFontHeightInPoints((short)20);
//            headWriteCellStyle.setWriteFont(headWriteFont);
//            // 内容的策略
//            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//            // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//            contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
//            // 背景绿色
//            contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//            WriteFont contentWriteFont = new WriteFont();
//            // 字体大小
//            contentWriteFont.setFontHeightInPoints((short)20);
//            contentWriteCellStyle.setWriteFont(contentWriteFont);
//            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
//            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
//                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);



            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            ExcelWriter  excelWriter = EasyExcel.write(out).withTemplate(fis).build();//.registerWriteHandler(horizontalCellStyleStrategy).sheet("asdasd").doWrite(list);
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
// 单个对象填充 {name}
            HashMap<String, Object> map = new HashMap<>();
            map.put("title",title);
            if(yearDateStart!=null&&!"".equals(yearDateStart)){
                map.put("year",yearDateStart);
            }
            excelWriter.fill(map, writeSheet);
// 填充集合 {.name}
            excelWriter.fill(list, writeSheet);
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
