package com.neo.kttvapi.service;

import com.neo.kttvapi.dto.MenuListDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MenuExportExcel {
   private XSSFWorkbook workbook;
   private XSSFSheet sheet;
   private List<MenuListDto> listMenuDto;

   public MenuExportExcel(List<MenuListDto> listMenuDto) {
      this.listMenuDto = listMenuDto;
      workbook = new XSSFWorkbook();
   }

   private void writeHeaderLine(){
      sheet = workbook.createSheet("MenuList");
      Row row = sheet.createRow(0);
      CellStyle style = workbook.createCellStyle();
      XSSFFont font = workbook.createFont();
      font.setBold(true);
      font.setFontHeight(16);
      style.setFont(font);

      createCell(row, 0, "STT", style);
      createCell(row, 1, "Tên Menu", style);
      createCell(row, 2, "Tên Menu cha", style);
      createCell(row, 3, "Icon", style);
      createCell(row, 4, "Đường dẫn Menu", style);
      createCell(row, 5, "Cấp", style);
      createCell(row, 6,   "Người tạo", style);
      createCell(row, 7, "Ngày tạo", style);
      createCell(row, 8, "Trạng thái", style);
   }
   private void createCell(Row row, int columnCount, Object value, CellStyle style) {
      sheet.autoSizeColumn(columnCount);
      Cell cell = row.createCell(columnCount);
      if (value instanceof Integer) {
         cell.setCellValue((Integer) value);
      } else if (value instanceof Boolean) {
         cell.setCellValue((Boolean) value);
      }
      else if (value instanceof Date) {
         cell.setCellValue((Date) value);
      }
      else {
         cell.setCellValue((String)value);
      }
      cell.setCellStyle(style);
   }
   private void writeDataLines() {
      int rowCount = 1;

      CellStyle style = workbook.createCellStyle();
      XSSFFont font = workbook.createFont();
      font.setFontHeight(14);
      style.setFont(font);

      for (MenuListDto menuListDto : listMenuDto) {
         Row row = sheet.createRow(rowCount++);
         int columnCount = 0;

         createCell(row, columnCount++, rowCount - 1, style);
         createCell(row, columnCount++, menuListDto.getName(), style);
         createCell(row, columnCount++, menuListDto.getParentname(), style);
         createCell(row, columnCount++, menuListDto.getPictureFile(), style);
         createCell(row, columnCount++, menuListDto.getDetailsFile(), style);
         createCell(row, columnCount++, menuListDto.getMenuLevel(), style);
         createCell(row, columnCount++, menuListDto.getCreatedUser(), style);
         createCell(row, columnCount++, menuListDto.getCreatedDate(), style);
         createCell(row, columnCount++, menuListDto.getStatus(), style);

      }
   }

   public void export(HttpServletResponse response) throws IOException {
      writeHeaderLine();
      writeDataLines();

      ServletOutputStream outputStream = response.getOutputStream();
      workbook.write(outputStream);
      workbook.close();

      outputStream.close();

   }

}
