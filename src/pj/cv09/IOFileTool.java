package pj.cv09;

import java.io.*;

public class IOFileTool {
    private static final String FILENAME_1 = "1.txt";
    private static final String FILENAME_2 = "2.txt";
    private static final String FILE_OSOBA = "osoba.bin";
    private static final String FILE_DATUM = "datum.bin";
    private static final String NAME = "Aleksandr";
    private static final String LASTNAME = "Shabelnikov";

    IOFileTool() {

    }

    public static void init() {
        int znaky = pocet_znaku(FILENAME_1);
        System.out.println("Pocet znaku ve " + FILENAME_1 + ": " + znaky);
        copy_file(FILENAME_1, FILENAME_2);
        znaky = pocet_znaku(FILENAME_2);
        System.out.println("Pocet znaku po upraveni: " + znaky);

        //now serialization
        System.out.println();
        System.out.println("========================");
        System.out.println("=       PART 3         =");
        System.out.println("========================");
        System.out.println();


        Datum date = new Datum(14, 8, 1983);

        try {
            serializace(date, FILE_DATUM);
            Datum newdate = datum_deserialize(FILE_DATUM);
            System.out.println(newdate);
            Osoba osoba = new Osoba(NAME, LASTNAME, newdate);
            serializace(osoba, FILE_OSOBA);
            Osoba newosoba = osoba_deserializace(FILE_OSOBA);
            System.out.println(newosoba);
            //System.out.println(osoba_deserializace(FILE_OSOBA));
            //System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }

    private static Osoba osoba_deserializace(String filename) throws IOException {
        Osoba osoba = null;
        ObjectInputStream objectInputStream = null;
        Datum date = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            osoba = (Osoba) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return osoba;
    }

    private static void copy_file(String filename1, String filename2) {
        int i = 0;
        try {
            i = process_file(filename1, filename2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (i < 0) System.out.println("Something went wrong");
    }

    private static int pocet_znaku(String filename)  {
        int i;

        try {
            //send empty filename2 as a parameter to do counting instead of copying and stripping
            i = process_file(filename, "");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            i = -1;
        }
        return i;
    }

    //Following DRY rule. One method for solving similar problems
    //I see in exercise that I may have to complete it with byte or char loop, but it is possible to
    //with only strings
    private static int process_file(String filename1, String filename2) throws IOException, FileNotFoundException {
        int charsCount = 0;
        File file1 = new File(filename1);
        File file2 = new File(filename2);
        String line;

        FileReader fileReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(file1);
            bufferedReader = new BufferedReader(fileReader);

            /*
            Cause java doesn't support default parameters check if filename2
            is not exists and do counting or copying
            */

            if (filename2.isEmpty()) { //count characters if filename2 is empty
                while ((line = bufferedReader.readLine()) != null) {
                    charsCount += line.length();
                }

            } else {
                //strip data
                fileReader = new FileReader(file1);
                bufferedReader = new BufferedReader(fileReader);
                fileWriter = new FileWriter(file2);
                bufferedWriter = new BufferedWriter(fileWriter);

                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.trim().isEmpty()) { //check if line is not empty
                        bufferedWriter.write(line.trim()); //and write line to file2
                        bufferedWriter.newLine();
                    }
                }
                charsCount = 0;

            }

        } catch (FileNotFoundException e) {
            charsCount = -1; //return error status
            throw e;

        } catch (IOException e) {
            charsCount = -1; //return error status
            throw e;

        } finally {

            if (bufferedReader != null) {
                fileReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (fileReader != null) {
                fileReader.close();
            }

            if (fileWriter != null) {
                fileWriter.close();
            }
        }

        return charsCount;
    }

    //TODO More exceptions



    private static Datum datum_deserialize(String filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        Datum date = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            date = (Datum) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return date;
    }



    private static void serializace(Osoba osoba, String filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            //ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_DATUM));
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(osoba);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }

    //polymorphisms ty vole!
    private static void serializace(Datum data, String filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            //ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_DATUM));
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }




}
