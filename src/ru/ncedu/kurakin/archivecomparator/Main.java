package ru.ncedu.kurakin.archivecomparator;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IArchiveComparator ar = new ArchiveComparator();
        String fp1 = ".\\zips\\zip1.zip";
        String fp2 = ".\\zips\\zip2.zip";
//        ar.loadFiles(fp1, fp2);
        ar.getReport(fp1,fp2);
    }
}
