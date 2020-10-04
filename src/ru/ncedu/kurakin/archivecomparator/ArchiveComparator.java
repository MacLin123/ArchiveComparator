//Copyright Mikhail Kurakin 2020

package ru.ncedu.kurakin.archivecomparator;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveComparator implements IArchiveComparator {
    private static final String reportFpath = ".\\report.txt";
    private ArrayList<ArrayList<ZipEntry>> zipFiles;
    private String[] fileNames;

    private void loadFiles(String filePath1, String filePath2) {
        zipFiles = new ArrayList<>();
        zipFiles.add(new ArrayList<>());
        zipFiles.add(new ArrayList<>());
        fileNames = new String[2];
        List<File> files = new ArrayList<>(); // there 2 zip files
        files.add(new File(filePath1));
        files.add(new File(filePath2));
        int zipFilesInd = 0;
        try {
            for (File file : files) {
                InputStream input = new FileInputStream(file);
                ZipInputStream zip = new ZipInputStream(input);
                ZipEntry entry = zip.getNextEntry();
                fileNames[zipFilesInd] = file.getName();
                while (entry != null) {
                    zipFiles.get(zipFilesInd).add(entry);
                    entry = zip.getNextEntry();
                }
                zipFilesInd++;
                zip.close();
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getReport(String filePath1, String filePath2) {
        loadFiles(filePath1, filePath2);
        String header = String.format("%20s | %20s \r\n", fileNames[0], fileNames[1]);
        try {
            OutputStream output = new FileOutputStream(reportFpath);
            output.write(header.getBytes());
            output.write("---------------------+-------------------\r\n".getBytes());
            saveRepToFile(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRepToFile(OutputStream output) {
        ArrayList<ZipEntry> arr1 = zipFiles.get(0);
        ArrayList<ZipEntry> arr2 = zipFiles.get(1);
        ArrayList<Integer> arr2IndexesContains = new ArrayList<>();
        ArrayList<Integer> arr2Indexes = new ArrayList<>(zipFiles.get(1).size());
        for (int i = 0; i < zipFiles.get(1).size(); i++) {
            arr2Indexes.add(i);
        }
        for (int i = 0; i < arr1.size(); i++) {
            boolean found = false;
            for (int j = 0; j < arr2.size(); j++) {
                if (arr1.get(i).getName().equals(arr2.get(j).getName())) {
                    if (arr1.get(i).getSize() != arr2.get(j).getSize()) {
                        updateFilesWriteRep(i, j, output);
                    }
                    found = true;
                    if (!arr2IndexesContains.contains(new Integer(j))) {
                        arr2IndexesContains.add(j);
                    }
                } else if (arr1.get(i).getSize() == arr2.get(j).getSize()) {
                    if (!arr1.get(i).getName().equals(arr2.get(j).getName())) {
                        renamedFilesWriteRep(i, j, output);
                    }
                    found = true;
                    if (!arr2IndexesContains.contains(new Integer(j))) {
                        arr2IndexesContains.add(j);
                    }
                }
            }
            if (!found) {
                deletedFilesWriteRep(i, output);
            }
        }
        newFilesWriteRep(arr2Indexes, arr2IndexesContains, output);
    }

    private void newFilesWriteRep(ArrayList<Integer> arr2Indexes, ArrayList<Integer> arr2IndexesContains, OutputStream output) {
        for (int i = 0; i < arr2IndexesContains.size(); i++) {
            arr2Indexes.remove(arr2IndexesContains.get(i));
        }
        try {
            for (int i : arr2Indexes) {
                String toWrite = String.format("%20s | %20s\r\n", "", "newFile " + zipFiles.get(1).get(i).getName());
                output.write(toWrite.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFilesWriteRep(int i, int j, OutputStream output) {
        String toWrite = String.format("%20s | %20s\r\n", "updated " +
                zipFiles.get(0).get(i).getName(), "updated " + zipFiles.get(1).get(j).getName());
        try {
            output.write(toWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renamedFilesWriteRep(int i, int j, OutputStream output) {
        String toWrite = String.format("%20s | %20s\r\n", "renamed " +
                zipFiles.get(0).get(i).getName(), "renamed " + zipFiles.get(0).get(j).getName());
        try {
            output.write(toWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletedFilesWriteRep(int i, OutputStream output) {
      String toWrite = String.format("%20s | %20s\r\n", "deleted " +
                zipFiles.get(0).get(i).getName(), "");
        try {
            output.write(toWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
