package com.kroll;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String BASE_URL = "https://api.escuelajs.co/api/v1";
    private static final String[] ENDPOINTS = {"/products", "/categories"};

    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            LOGGER.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomLogFormatter());
            consoleHandler.setLevel(Level.ALL);

            for (String endpoint : ENDPOINTS) {
                String apiUrl = BASE_URL + endpoint;
                JSONArray data = getDataFromApi(apiUrl);
                if (data != null) {
                    writeToExcel(workbook, endpoint.substring(1), data);
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream("products_data.xlsx")) {
                workbook.write(fileOut);
                LOGGER.info("Data written to Excel file successfully");
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONArray getDataFromApi(String apiUrl) {
        String apiResponse = getApiResponse(apiUrl);
        if (apiResponse != null && !apiResponse.isEmpty()) {
            return new JSONArray(apiResponse);
        }
        return null;
    }
    private static String getApiResponse(String apiUrl) {
        return HttpClient.get(apiUrl);
    }
    private static void writeToExcel(XSSFWorkbook workbook, String sheetName, JSONArray data) {
        var sheet = workbook.createSheet(sheetName);

        var headerRow = sheet.createRow(0);
        var jsonObject = data.getJSONObject(0);
        List<String> columnNames = getColumnNames(jsonObject);
        for (int j = 0; j < columnNames.size(); j++) {
            var cell = headerRow.createCell(j);
            cell.setCellValue(columnNames.get(j));
        }

        for (int i = 0; i < data.length(); i++) {
            var row = sheet.createRow(i + 1);
            jsonObject = data.getJSONObject(i);

            for (int j = 0; j < columnNames.size(); j++) {
                var cell = row.createCell(j);
                String columnName = columnNames.get(j);

                if ("id".equals(columnName) || "price".equals(columnName)) {
                    cell.setCellValue(jsonObject.getDouble(columnName));
                } else if ("images".equals(columnName)) {
                    JSONArray jsonArray = jsonObject.getJSONArray(columnName);
                    StringBuilder arrayString = new StringBuilder();
                    for (int k = 0; k < jsonArray.length(); k++) {
                        arrayString.append(jsonArray.getString(k));
                        if (k < jsonArray.length() - 1) {
                            arrayString.append(", ");
                        }
                    }
                    cell.setCellValue(arrayString.toString());
                } else if ("category".equals(columnName)) {
                    JSONObject category = jsonObject.getJSONObject(columnName);
                    cell.setCellValue(category.getString("name"));
                } else {
                    Object value = jsonObject.get(columnName);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }
    }
    private static List<String> getColumnNames(JSONObject jsonObject) {
        List<String> columnNames = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            columnNames.add(key);
        }
        return columnNames;
    }
}