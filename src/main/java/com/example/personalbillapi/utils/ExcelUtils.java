package com.example.personalbillapi.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static List<List<String>> readExcelFile(String filePath) {
        List<List<String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowData = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getCellType() == CellType.STRING) {
                        rowData.add(cell.getStringCellValue());
                    }
                }

                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static List<List<String>> readExcelFromStream(byte[] steam) {
        List<List<String>> data = new ArrayList<>();

        ByteArrayInputStream bis = new ByteArrayInputStream(steam);


        try (Workbook workbook = new XSSFWorkbook(bis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowData = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getCellType() == CellType.STRING) {
                        rowData.add(cell.getStringCellValue());
                    }
                }

                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static List<List<String>> parseCSVToArrayList(byte[] csvData) {
        List<List<String>> result = new ArrayList<>();

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(csvData);
             InputStreamReader reader = new InputStreamReader(inputStream, guessEncoding(csvData));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : csvParser) {
                List<String> row = new ArrayList<>();
                for (String value : record) {
                    row.add(value);
                }
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String guessEncoding(byte[] bytes) {
        String DEFAULT_ENCODING = "UTF-8";
        org.mozilla.universalchardet.UniversalDetector detector =
                new org.mozilla.universalchardet.UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        return encoding;
    }

    public static byte[] gb2312ToUTF8(byte[] data) {
        byte[] byteArray = null;
        try {
            InputStream in = new ByteArrayInputStream(data);

            // 使用字节数组输出流写入UTF-8字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                String str = new String(buffer, 0, bytesRead, Charset.forName("GB2312"));
                out.write(str.getBytes(StandardCharsets.UTF_8));
            }
            // 获取转换后的UTF-8字节数组
            byteArray = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}