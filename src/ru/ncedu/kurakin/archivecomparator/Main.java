package ru.ncedu.kurakin.archivecomparator;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IArchiveComparator ar = new ArchiveComparator();
        String fp1 = ".\\zip1.zip";
        String fp2 = ".\\zip2.zip";
        ar.loadFiles(fp1, fp2);

    }
}