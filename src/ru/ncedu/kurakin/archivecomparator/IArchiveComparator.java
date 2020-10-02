package ru.ncedu.kurakin.archivecomparator;

public interface IArchiveComparator {
    void loadFiles(String filePath1, String filePath2);
    void getReport();
}
