//Copyright Mikhail Kurakin 2020

package ru.ncedu.kurakin.archivecomparator;

/**
 * This interface helps compare to jar or zip files
 * @author Mikhail Kurakin
 *
 */
public interface IArchiveComparator {
    /**
     * This method compare two zip or jar files and save report.txt in root project directory,
     * report.txt contains detailed information about the result of file comparison
     * @param filePath1 - file path for first file
     * @param filePath2 - file path for second file
     */
    void getReport(String filePath1, String filePath2);
}
