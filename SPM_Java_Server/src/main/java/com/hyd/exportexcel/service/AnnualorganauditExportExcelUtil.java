package com.hyd.exportexcel.service;

import com.hyd.exportexcel.vo.AnnualorganauditVo;
import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.vo.User;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public interface AnnualorganauditExportExcelUtil {

  void exportExcel(String desFilePath,InputStream fis, String title, List<AnnualorganauditVo> list, HttpServletResponse response,String yearDateStart) throws IOException;


}

