package com.example.excelserviceapp.services;

import java.io.IOException;

public interface ExcelService {
    Integer findNthMax(String filePath, int n) throws IOException;
}
