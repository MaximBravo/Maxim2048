package maximbravo.com.Chilled2048;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wendy on 8/20/2016.
 */
public class FileManipulator {

    public static File dataBaseFile;
    public static String dataBaseFilePath;
    public static String dataBaseFileName;

    public Context context;
    public FileManipulator(Context c){
        context = c;
        dataBaseFile = new File(context.getFilesDir(), "dataBase");
        dataBaseFilePath = dataBaseFile.getAbsolutePath();
        dataBaseFileName = dataBaseFile.getName();
    }


    public String getStringInFile(){

        String str = "";
        try {
            str = getStringFromFile(dataBaseFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    


    public void updateStringInFile(String s){
        String string = s;
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(dataBaseFileName, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}
