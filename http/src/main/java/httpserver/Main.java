package httpserver;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String defaultPath = "static";
        String path = "";

        if (null != args && args.length > 0) {
            path = args [0];
            File file = new File(args[0]);

            if (!file.exists())
                file.mkdirs();

        }
        else {
            path = defaultPath;
            File file = new File(path);

            if (!file.exists())
                file.mkdirs();
        }
    }
    
}
