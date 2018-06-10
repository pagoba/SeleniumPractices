package testing.test;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class ScreenMethod {
	public static File captureElementPic(WebElement element) throws Exception {

        WrapsDriver wrapsDriver = (WrapsDriver) element;

        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).
                getScreenshotAs(OutputType.FILE);

        BufferedImage img = ImageIO.read(screen);

        int width = element.getSize().getWidth();
        int height= element.getSize().getHeight();
        System.out.println(width);
        System.out.println(height);



        Point p = element.getLocation();
        System.out.println(p.getX());
        System.out.println(p.getY());

        BufferedImage dest = img.getSubimage(p.getX()*2, p.getY()*2,width*2,height*2);

        ImageIO.write(dest,"png",screen);

        return screen;


    }

}
