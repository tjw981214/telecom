package com.telecom.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 导出excel表工具包
 * @author 唐佳威
 *
 */
public class ExcelUitl {

	public static List<String[]> readXls(String path) {
		FileInputStream is = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			is = new FileInputStream(path);
			HSSFWorkbook excel = new HSSFWorkbook(is);
			// 获取第一个sheet
			HSSFSheet sheet0 = excel.getSheetAt(0);
			// 循环读取所有行数据
			int firstRowNum = sheet0.getFirstRowNum();// 获得当前sheet的开始行
			int lastRowNum = sheet0.getLastRowNum();// 获得当前sheet的结束行
			for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
				HSSFRow row = sheet0.getRow(rowNum);// 读取一行数据
				if (row == null) {// 没有行,跳出循环
					continue;
				}
				// 获得当前开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前结束列
				int lastCellNum = row.getLastCellNum();
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					cells[cellNum] = getCellValue(cell);
				}
				list.add(cells);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
			    //如果是date类型则 ，获取该cell的date值
  				Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
  				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  				cellValue = format.format(date);;
  			}else {// 纯数字
  				BigDecimal big=new BigDecimal(cell.getNumericCellValue());
  				cellValue = big.toString();
  				//解决1234.0  去掉后面的.0
  				if(null!=cellValue&&!"".equals(cellValue.trim())){
  				     String[] item = cellValue.split("[.]");
  				     if(1<item.length&&"0".equals(item[1])){
  				    	cellValue=item[0];
  				     }
  				}
  			}
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
	
}
