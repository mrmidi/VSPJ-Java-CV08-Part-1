package pj.cv09;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        long l;
        int i;
        final String filename = "out.bin"; //for later usage? // naah, let's make it constant
        try {
            boolean checkFile = Files.deleteIfExists(Path.of(filename));
            if (checkFile) {
                System.out.println("file " + filename + "is already exists. deleting...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Zadejte integer: ");
        try {
            i = Integer.valueOf(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Wrong number format! Default value will be set (0)");
            i = 0;
        }
        System.out.printf("Zadejte long: ");
        try {
            l = Long.valueOf(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Wrong number format! Default value will be set (0L)");
            l = 0L;
        }
        System.out.println("writing file " + filename);
        try {
            writeBin(filename, i, l);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not write the file" + e.getMessage());
        }
        /*
        Order 0 is normal, -1 is reverse
        */
        try {

            readBin(filename, 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not read the file" + e.getMessage());
        }
        try {
            readBin(filename, -1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        getFileSize(filename); //print size of file

    }

    public static void writeBin(String filename, int i, long l) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filename);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeInt(i);
            dataOutputStream.writeLong(l);

            dataOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    public static void readBin(String filename, int order) throws IOException, FileNotFoundException {
        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;

        long l;
        int i;
        try {
            fileInputStream = new FileInputStream(filename);
            dataInputStream = new DataInputStream(fileInputStream);

            if (order == 0) { //normal order
                i = dataInputStream.readInt();
                l = dataInputStream.readLong();
                System.out.println("NORMAL ORDER");
                System.out.println("Number 1 (integer): " + i);
                System.out.println("Number 2 (long): " + l);
            }
            if (order == -1) { //reverse order
                l = dataInputStream.readLong();
                i = dataInputStream.readInt();
                System.out.println("REVERSE ORDER");
                System.out.println("Number 1 (integer): " + i);
                System.out.println("Number 2 (long): " + l);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    public static void getFileSize(String filename) {
        Path path = Paths.get(filename);
        try {
            long bytes = Files.size(path);
            System.out.println(String.format("Size is: %,d", bytes));
            System.out.println("Velikost soubora je musi byt rovna 12 bytu kvuli velikosti datovych typu int (4b) a long (8b)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
