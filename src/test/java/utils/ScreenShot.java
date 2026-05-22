package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    public static String capture(WebDriver driver, String name) {

        if (driver == null) {
            System.out.println("Driver is null");
            return null;
        }

        try {
            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            File src =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            String dir =
                    System.getProperty("user.dir")
                            + "/test-output/screenshots/";

            File folder = new File(dir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fullPath = dir + name + timeStamp + ".png";

            File dest = new File(fullPath);
            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());

            // 🔥 IMPORTANT: return ABSOLUTE PATH
            return dest.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}