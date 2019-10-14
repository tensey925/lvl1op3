import java.io.*;
import java.util.ArrayList;

public class test3 {
    public static void main(String[] args) throws IOException {
        String path = checkFile();
        String path2 = newPath(path);
        new File(path2);
        ArrayList<String> list = new ArrayList<>();
        final char newLine = '\n';
        try (FileReader reader = new FileReader(path)) {
            int c;
            String s = "";
            while ((c = reader.read()) != -1) {
                if ( newLine != (char) c) {
                    s = s + (char) c;
                } else {
                    list.add(s);
                    s = "";
                }
            }
        } catch(IOException ex) {
            System.out.println("Troubles with reading of the file: " + ex.getMessage());
        }


        int lines = numberLines(list.size());
        int listSize = list.size();

        ArrayList<Integer> randomIndex = new ArrayList<>();
        for (int i = 0; i < listSize - 1; i++) {
            randomIndex.add(i);
        }


        int shuffle = randomIndex.size() - 1;
        for (int i = 0; i < shuffle; i++) {
            int r = getRandomNumberInRange(shuffle);
            int buff = randomIndex.get(r);
            randomIndex.remove(r);
            randomIndex.add(buff);
        }


        try (FileWriter file1 = new FileWriter(path);
             FileWriter file2 = new FileWriter(path2)) {
            file1.write(list.get(0) + newLine);
            file2.write(list.get(0) + newLine);
            list.remove(0);
            for (int i = 0; i < lines; i++){
                int j = randomIndex.get(i);
                file1.write(list.get(j) + newLine);
                list.set(j, "removed");
            }

            for (String s : list) {
                if (s.equals("removed")) {}
                    else{
                    file2.write(s + newLine);
                }
            }
        }
    }



    public static String getInputData() {
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();
        } catch (IOException e2) {
            System.out.println("Input data error: " + e2.getMessage());
        } finally {
            return line;
        }
    }


    public static String checkFile() {
        System.out.println("Enter path to the file OR press Enter to leave default path to the file.");
        String path = getInputData();
        int attemptNumber = 5;
        if (path.equals(null) || path.equals("")) {
            path = "D://1.txt";

        } else {
            File file = new File(path);
            if (file.isFile())
                return path;
            else {
                System.out.println("The path to the file is wrong. Number of attempts: " + attemptNumber);
                attemptNumber--;
                if (attemptNumber > 0)
                    checkFile();
                else System.exit(1);
            }
        }
        System.out.println("Current path to the file is: " + path);
        return path;
    }

    public static int numberLines(int numberStrings) {
        System.out.println("Enter number of lines, which is less or equals to number of strings in the file - " + numberStrings);
        System.out.println("or press Enter and default number of lines will be 10:");
        String input = getInputData();
        int lines = 0;
        int defaultNumber = 10;
        if (input.equals(null) || input.equals(""))
            lines = defaultNumber;
        else {
            if (isStringInt(input)) {
            lines = Integer.parseInt(input);
                if (lines > numberStrings)
                {
                    System.out.println(lines + " is more than maximum number of strings: " + numberStrings + " Number of lines was set to default: " + defaultNumber);
                    lines = defaultNumber;
                }
            }
            else {
                System.out.println("Not a number");
                System.exit(1);
            }
        }
        return lines;
    }

    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public static String newPath(String s) {
        s = s.replaceAll(".txt", "_res.txt");
        return s;
    }

    private static int getRandomNumberInRange(int max) {
        return (int)(Math.random() * max);
    }
}
