//Copyright Mikhail Kurakin 2020

package ru.ncedu.kurakin.archivecomparator;

import java.io.*;

public class FileChooser {
    public static String[] consolePath() {
        String[] filePaths = new String[2];
        System.out.println("Enter archive paths");
        System.out.println("Example: .\\zip1.zip - archive in root directory of project");
        printFilesAndDirCurDir();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter file path 1:");
            filePaths[0] = reader.readLine();
            System.out.println("Enter file path 2:");
            filePaths[1] = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePaths;
    }

    private static void printFilesAndDirCurDir() {
        String[] pathnames;

        File f = new File(System.getProperty("user.dir"));
        pathnames = f.list();
        System.out.println("Files and directories in current directory:");
        System.out.println("********************************************************************");
        for (int i = 0; i < pathnames.length; i++) {
            if ((i != 0) && (i % 5 == 0)) {
                System.out.println();
            }
            System.out.printf("%s %5s",pathnames[i],"");
        }
        System.out.println("\n********************************************************************");
    }
}
