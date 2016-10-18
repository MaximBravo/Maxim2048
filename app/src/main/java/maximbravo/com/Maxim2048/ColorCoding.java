package maximbravo.com.Maxim2048;

/**
 * Created by wendy on 10/17/2016.
 */
public class ColorCoding {

    public static String[] red = {
        "#FFFFFF",
        "#FFEBEE",
        "#FFCDD2",
        "#EF9A9A",
        "#E57373",
        "#EF5350",
        "#F44336",
        "#E53935",
        "#D32F2F",
        "#C62828",
        "#B71C1C",
            "#B71C1C",
            "#B71C1C",
            "#B71C1C",
            "#B71C1C",
            "#B71C1C",
            "#B71C1C",
            "#B71C1C",
            "#B71C1C"

    };
    public static String[] pink = {
        "#FFFFFF",
        "#FCE4EC",
        "#F8BBD0",
        "#F48FB1",
        "#F06292",
        "#EC407A",
        "#E91E63",
        "#D81B60",
        "#C2185B",
        "#AD1457",
        "#880E4F",
            "#880E4F",
            "#880E4F",
            "#880E4F",
            "#880E4F",
            "#880E4F",
            "#880E4F",
            "#880E4F"
    };
    public static String[] purple = {
        "#FFFFFF",
        "#F3E5F5",
        "#E1BEE7",
        "#CE93D8",
        "#BA68C8",
        "#AB47BC",
        "#9C27B0",
        "#8E24AA",
        "#7B1FA2",
        "#6A1B9A",
        "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C",
            "#4A148C"
    };

    public static String[] blue = {
        "#FFFFFF",
        "#E3F2FD",
        "#BBDEFB",
        "#90CAF9",
        "#64B5F6",
        "#42A5F5",
        "#2196F3",
        "#1E88E5",
        "#1976D2",
        "#1565C0",
        "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1",
            "#0D47A1"
    };

    public static String[] green = {
        "#FFFFFF",
        "#E8F5E9",
        "#C8E6C9",
        "#A5D6A7",
        "#81C784",
        "#66BB6A",
        "#4CAF50",
        "#43A047",
        "#388E3C",
        "#2E7D32",
        "#1B5E20",
            "#1B5E20",
            "#1B5E20",
            "#1B5E20",
            "#1B5E20",
            "#1B5E20",
            "#1B5E20",
            "#1B5E20"

    };

    public static String[] yellow = {
        "#FFFFFF",
        "#FFFDE7",
        "#FFF9C4",
        "#FFF59D",
        "#FFF176",
        "#FFEE58",
        "#FFEB3B",
        "#FDD835",
        "#FBC02D",
        "#F9A825",
        "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17",
            "#F57F17"
    };
    public static String[] orange = {
        "#FFFFFF",
        "#FBE9E7",
        "#FFCCBC",
        "#FFAB91",
        "#FF8A65",
        "#FF7043",
        "#FF5722",
        "#F4511E",
        "#E64A19",
        "#D84315",
        "#BF360C",
            "#BF360C",
            "#BF360C",
            "#BF360C",
            "#BF360C",
            "#BF360C",
            "#BF360C",
            "#BF360C",
            "#BF360C"
    };
    public static String[] brown = {
        "#FFFFFF",
        "#EFEBE9",
        "#D7CCC8",
        "#BCAAA4",
        "#A1887F",
        "#8D6E63",
        "#795548",
        "#6D4C41",
        "#5D4037",
        "#4E342E",
        "#3E2723",
            "#3E2723",
            "#3E2723",
            "#3E2723",
            "#3E2723",
            "#3E2723",
            "#3E2723",
            "#3E2723",
            "#3E2723"
    };
    public static String[] grey = {
        "#FFFFFF",
        "#FAFAFA",
        "#F5F5F5",
        "#EEEEEE",
        "#E0E0E0",
        "#BDBDBD",
        "#9E9E9E",
        "#757575",
        "#616161",
        "#424242",
        "#212121",
            "#212121",
            "#212121",
            "#212121",
            "#212121",
            "#212121",
            "#212121",
            "#212121",
            "#212121"
    };
    public static String getStringColorFor(int scale, int id){
        String result = "";
        int position = getPositionOfId(id);
        switch(scale){
            case 0:
                result = grey[position];
                break;
            case 1:
                result = red[position];
                break;
            case 2:
                result = orange[position];
                break;
            case 3:
                result = yellow[position];
                break;
            case 4:
                result = green[position];
                break;
            case 5:
                result = blue[position];
                break;
            case 6:
                result = purple[position];
                break;
            case 7:
                result = pink[position];
                break;
            case 8:
                result = brown[position];
                break;
        }

        return result;
    }


    public static int getPositionOfId(int fakeId){
        int result = 0;
        int theid = fakeId;
        while(theid % 2 == 0 && theid != 0){
            result++;
            theid = theid / 2;
        }
        return result;
    }
}
