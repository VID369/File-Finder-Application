import java.io.File;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Directory Path: ");
        String directoryPath = scanner.nextLine();

        if (directoryPath.isEmpty()) {
            System.out.println("Please enter a directory path.");
            return;
        }

        System.out.print("Search for All Files (yes/no): ");
        boolean searchAllFiles = scanner.nextLine().equalsIgnoreCase("yes");

        String[] selectedFileTypes;
        if (searchAllFiles) {
            selectedFileTypes = getFileTypes(true, true, true, true, true, true, true, true, true, true, true);
        } else {
            System.out.print("Search for .exe files (yes/no): ");
            boolean exe = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .bat files (yes/no): ");
            boolean bat = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .com files (yes/no): ");
            boolean com = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .cmd files (yes/no): ");
            boolean cmd = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .inf files (yes/no): ");
            boolean inf = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .ipa files (yes/no): ");
            boolean ipa = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .osx files (yes/no): ");
            boolean osx = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .pif files (yes/no): ");
            boolean pif = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .run files (yes/no): ");
            boolean run = scanner.nextLine().equalsIgnoreCase("yes");
            System.out.print("Search for .wsh files (yes/no): ");
            boolean wsh = scanner.nextLine().equalsIgnoreCase("yes");

            selectedFileTypes = getFileTypes(false, exe, bat, com, cmd, inf, ipa, osx, pif, run, wsh);
        }

        AtomicInteger fileCount = new AtomicInteger(0);

        fileCount.set(findFilesRecursive(new File(directoryPath), selectedFileTypes, fileCount));
        System.out.println("Files Found: " + fileCount.get());
    }

    private static int findFilesRecursive(File currentDir, String[] extensions, AtomicInteger fileCount) {
        File[] files = currentDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && hasExtension(file.getName(), extensions)) {
                    System.out.println(file.getAbsolutePath());
                    fileCount.incrementAndGet();
                }

                if (file.isDirectory()) {
                    findFilesRecursive(file, extensions, fileCount);
                }
            }
        }

        return fileCount.get();
    }

    private static boolean hasExtension(String fileName, String[] extensions) {
        for (String extension : extensions) {
            if (fileName.toLowerCase().endsWith(extension.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private static String[] getFileTypes(boolean allFiles, boolean exe, boolean bat, boolean com, boolean cmd, boolean inf, boolean ipa,
                                         boolean osx, boolean pif, boolean run, boolean wsh) {
        if (allFiles) {
            return new String[]{".exe", ".bat", ".com", ".cmd", ".inf", ".ipa", ".osx", ".pif", ".run", ".wsh"};
        }

        java.util.List<String> types = new java.util.ArrayList<>();

        if (exe) {
            types.add(".exe");
        }
        if (bat) {
            types.add(".bat");
        }
        if (com) {
            types.add(".com");
        }
        if (cmd) {
            types.add(".cmd");
        }
        if (inf) {
            types.add(".inf");
        }
        if (ipa) {
            types.add(".ipa");
        }
        if (osx) {
            types.add(".osx");
        }
        if (pif) {
            types.add(".pif");
        }
        if (run) {
            types.add(".run");
        }
        if (wsh) {
            types.add(".wsh");
        }

        return types.toArray(new String[0]);
    }
}
