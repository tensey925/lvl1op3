import java.io.*;
import java.util.ArrayList;

public class test3 {
    public static void main(String[] args) {
        String path = checkFile();
        int lines = numberLines();
        ArrayList<String> list = new ArrayList<>();
        int indexList = 0;
        char newLine = '\n';
        try (FileReader reader = new FileReader(path)) {
            int c;
            String s = null;
            while ((c = reader.read()) != -1) {
                if ( newLine != (char) c) {
                    s = s + (char) c;
                } else {
                    list.add(s);
                    s = null;
                }

            }
        } catch(IOException ex) {
            System.out.println("Troubles with writing of the file: " + ex.getMessage());
        }
        for (String s : list)
        System.out.println(s);
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
            path = "C://Users//i.meleshko//1//1.txt";

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

    public static int numberLines(){
        System.out.println("Enter number of lines, or press Enter and default number of lines will be 10:");
        String input = getInputData();
        int lines;
        if (input == null)
            lines = 10;
        else
            lines = Integer.parseInt(input);
        return lines;
    }
}
