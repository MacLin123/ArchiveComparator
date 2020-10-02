//Copyright Mikhail Kurakin 2020

package ru.ncedu.kurakin.archivecomparator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveComparator implements IArchiveComparator {
    private Map<String, ArrayList<ZipEntry>> zipFiles;

    @Override
    public void loadFiles(String filePath1, String filePath2) {
        zipFiles = new HashMap<>(); // there will be files from zip files

        List<File> files = new ArrayList<>(); // there 2 zip files
        files.add(new File(filePath1));
        files.add(new File(filePath2));
        ArrayList<ArrayList<ZipEntry>> zipEntries = new ArrayList<>();
        zipEntries.add(new ArrayList<ZipEntry>());
        zipEntries.add(new ArrayList<ZipEntry>());
        int zipEntrInd = 0;
        try {
            for (File file : files) {
                InputStream input = new FileInputStream(file);
                ZipInputStream zip = new ZipInputStream(input);
                ZipEntry entry = zip.getNextEntry();

                while (entry != null) {
                    zipEntries.get(zipEntrInd).add(entry);
                    entry = zip.getNextEntry();
                }
                zipFiles.put(file.getName(), zipEntries.get(zipEntrInd)); // putting in map < zip name , file in zip >
                zipEntrInd++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getReport() {
        if(zipFiles == null) {
            return;
        }
    }
}
