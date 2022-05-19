package json_faker_get;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.URL;


/**
 * This class uses fastjson to catch information of java-faker.
 * Based on the REST API of github.
 *
 * The json-dealing function should not be here.(Like store data into database, or read information form json)
 */
public class json_faker_get_main {

    /**
     * This main() function only for test.
     * It should be changed to a comment after test.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(json_faker_get_page_main("curl -i https://api.github.com/repos/DiUS/java-faker"));
    }

    /**
     * To get raw report of website.
     * @param command Like "curl -i https://api.github.com/repos/DiUS/java-faker"
     * @return report, containing web-report, json and things like this, depended on the command.
     */
    private static String json_faker_work_before_get(String command){
        Process process;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = bufferedReader.readLine())!=null){
                System.out.println("> "+line);
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        /* stop if stringBuilder built wrong things. */
        System.out.println("Wrong!");
        return null;
    }

    /**
     * Get information based on the main page of java-faker.
     * @return json file containing basic information of java-faker.
     */
    public static JSON json_faker_get_page_main(String command){
        String raw_information = json_faker_work_before_get(command);
        File file_raw_head = new File("raw_head_faker_main.txt");
        File file_raw_json = new File("raw_json_faker_main.json");
        StringBuilder builder = new StringBuilder();
        String[] raw_data = raw_information.split("\n");
        int flag = 0;
        int length = raw_data.length;
        try{
            for (; flag < length; flag++) {
                if(raw_data[flag].equals("{")){
                    break;
                }
                builder.append(raw_data[flag]).append("\n");
            }
            file_raw_head.createNewFile();
            FileWriter fileWriter = new FileWriter(file_raw_head);
            fileWriter.write(builder.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            System.out.println("Can't creat file_raw_head.");
        }

        builder.setLength(0);
        try{
            for (; flag < length; flag++) {
                builder.append(raw_data[flag]).append("\n");
            }
            file_raw_json.createNewFile();
            FileWriter fileWriter = new FileWriter(file_raw_json);
            fileWriter.write(builder.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            System.out.println("Can't creat file_raw_json.");
        }
        return null;
    }

    /**
     * Get information of java-faker's issues based on the page of java-faker's issue-page.
     * @return json file containing issue's information like number, closed/opened, bugs/other request, and so on.
     */
    public JSON json_faker_get_page_issues(){
        return null;
    }
}
