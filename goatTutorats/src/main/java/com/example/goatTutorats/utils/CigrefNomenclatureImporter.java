package com.example.goatTutorats.utils;

import com.example.goatTutorats.entities.CigrefNomenclatures;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Utility class to import Cigref nomenclature from an ODS file
public class CigrefNomenclatureImporter {

    private static final Logger logger = LoggerFactory.getLogger(CigrefNomenclatureImporter.class);

    public static List<CigrefNomenclatures> importFromOds(String resourcePath) {
        List<CigrefNomenclatures> list = new ArrayList<>();

        // Open InputStream from classpath using try-with-resources
        try (InputStream is = CigrefNomenclatureImporter.class.getClassLoader().getResourceAsStream(resourcePath)) {

            if (is == null) {
                logger.error("File not found: {}", resourcePath);
                throw new RuntimeException("File not found: " + resourcePath);
            }

            // Copy InputStream to a temporary file since jOpenDocument requires a File
            File tempFile = File.createTempFile("cigref", ".ods");
            tempFile.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, read);
                }
            }

            // Load the ODS file
            Sheet sheet = SpreadSheet.createFromFile(tempFile).getSheet(0);

            // Find the indices of columns "Référence interne" and "Intitulé"
            int rowCount = sheet.getRowCount();
            int colCount = sheet.getColumnCount();
            int refColIndex = -1;
            int jobColIndex = -1;

            for (int col = 0; col < colCount; col++) {
                String header = sheet.getCellAt(col, 0).getTextValue().trim();
                if (header.equalsIgnoreCase("Référence interne")) {
                    refColIndex = col;
                } else if (header.equalsIgnoreCase("Intitulé")) {
                    jobColIndex = col;
                }
            }

            if (refColIndex == -1 || jobColIndex == -1) {
                logger.error("Columns 'Référence interne' or 'Intitulé' not found!");
                throw new RuntimeException("Columns 'Référence interne' or 'Intitulé' not found!");
            }

            // Iterate through rows starting from the second row (row 0 = headers)
            for (int row = 1; row < rowCount; row++) {
                String ref = sheet.getCellAt(refColIndex, row).getTextValue().trim();
                String job = sheet.getCellAt(jobColIndex, row).getTextValue().trim();

                if (!ref.isEmpty() && !job.isEmpty()) {
                    CigrefNomenclatures cn = new CigrefNomenclatures();
                    cn.setId(UUID.randomUUID());
                    cn.setInternalReference(ref);
                    cn.setJobName(job);
                    list.add(cn);
                }
            }

        } catch (Exception e) {
            logger.error("Error occurred while importing ODS file", e);
        }

        return list;
    }
}
