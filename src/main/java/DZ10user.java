import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;


public class DZ10user {
     static class User {
        private String name;
        private int age;
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }

    public static void writeFileWithOutputStream(String nameFile, String text) {
               try (FileOutputStream fileOutputStream = new FileOutputStream(nameFile)){
            byte[] buffer = text.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readListUser(String nameFile) throws IOException{
        ArrayList <User> users  = new ArrayList<>();
         //HashMap <String,Integer> users = new HashMap<>();
        try (FileReader read = new FileReader(nameFile)){
            BufferedReader reader = new BufferedReader(read);
            String coma = ",";
           // String name = "\"name\"";
            String nameName = "";
            //String age = "\"age\"";
            String ageAge = "";

            reader.readLine();

            String line = reader.readLine();

            while ( line != null){

                line = line.strip();
                line = line.replaceAll("\\s+"," ");
                //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                int j = line.indexOf(' ');
                for (int i = 0; i < j; i++) {
                    nameName = nameName + line.charAt(i);
                }
               // System.out.println(line);
               // System.out.println(nameName);

                Integer ageInt = 0;
                ageAge = line.substring(j+1,line.length());
                //System.out.println(ageAge);
                try {
                    ageInt = Integer.parseInt(ageAge);
                }
                catch (NumberFormatException e) {
                    ageInt = 0;
                }
               // System.out.println(ageInt);
              users.add(new User(nameName,ageInt));

                //System.out.println(users);
               // print(users);

                //System.out.println(line);
                //System.out.println(nameName);
                //System.out.println(ageAge);
                line = reader.readLine();
                nameName = "";
                ageAge = "";
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        writeFileWithOutputStream("user.json", gson.toJson(users));

    }


    public static void main(String[] args) throws IOException {
        writeFileWithOutputStream("file.txt","name age\njon     25\nryan     30\n   alice    21\nmykola 33\n");
       readListUser("file.txt");

    }
}
