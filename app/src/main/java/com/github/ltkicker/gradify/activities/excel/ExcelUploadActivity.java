package com.github.ltkicker.gradify.activities.excel;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.LoginActivity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelUploadActivity extends AppCompatActivity {

    Uri fileuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadexcel);
        getExcelFile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && data != null) {
            fileuri=data.getData();
            //csvText.setText(readCSVFile(getFilePathFromUri(fileuri)));
            Toast.makeText(ExcelUploadActivity.this, getFilePathFromUri(fileuri), Toast.LENGTH_SHORT).show();
        }
    }

    private void getExcelFile() {
        if(SDK_INT >= Build.VERSION_CODES.R) {
             if(Environment.isExternalStorageManager()) {
                 Intent intent = new Intent();
                 intent.setType("*/*");
                 intent.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE, true);
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(Intent.createChooser(intent, "Select Excel File"), 101);
             } else {
                 Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                 Uri uri = Uri.fromParts("package", getPackageName(), null);
                 startActivity(intent);
             }
        } else {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);

            ActivityCompat.requestPermissions(ExcelUploadActivity.this, new String[] {WRITE_EXTERNAL_STORAGE}, 102);
        }
    }

    public String getFilePathFromUri(Uri uri) {
        String[] filename1;
        String fn;
        String filepath = uri.getPath();
        String filePath1[] = filepath.split(":");
        filename1 = filepath.split("/");
        fn = filename1[filename1.length - 1];
        return Environment.getExternalStorageDirectory().getPath()+"/"+filePath1[1];
    }

    public void readExcelFile(String path) {
        File file = new File(path);
        Log.i("TAG", file.toString());

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Row row = sheet.getRow(0); // Get the first row

            // Iterate over the cells in the row
            for (Cell cell : row) {
                CellType cellType = cell.getCellType();

                if (cellType == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    System.out.println("Cell Value (String): " + cellValue);
                } else if (cellType == CellType.NUMERIC) {
                    double cellValue = cell.getNumericCellValue();
                    System.out.println("Cell Value (Numeric): " + cellValue);
                }
                // Handle other cell types as needed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
