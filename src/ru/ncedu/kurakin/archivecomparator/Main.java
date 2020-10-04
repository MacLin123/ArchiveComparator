//Copyright Mikhail Kurakin 2020
package ru.ncedu.kurakin.archivecomparator;

public class Main {
    public static void main(String[] args) {
        IArchiveComparator ar = new ArchiveComparator();
        String[] filepaths =  FileChooser.consolePath();
        ar.getReport(filepaths[0], filepaths[1]);

    }
}
