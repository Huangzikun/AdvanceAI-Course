package Tool;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Tool {


    public static <T> void array2CSV(ArrayList<T> list, String path) throws IOException {

        FileOutputStream fos = new FileOutputStream (path);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

        //创建字符串缓存
        BufferedWriter bw = new BufferedWriter(osw);

        StringBuffer str = new StringBuffer();

        for (T i: list) {
            str.append(i).append("\n");
        }

        bw.write(str.toString());

        bw.flush();
        osw.flush();
        fos.flush();
    }
}
