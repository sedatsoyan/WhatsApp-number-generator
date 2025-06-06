import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void yaz(Robot robot , String metin){

        for (char c : metin.toCharArray()){
            karakteryaz(robot,c);

        }

    }
    public static void karakteryaz(Robot robot , char c){

        int keycode = KeyEvent.getExtendedKeyCodeForChar(c);

        if(keycode == KeyEvent.VK_UNDEFINED){
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_SHIFT);

        }

        if (keycode == KeyEvent.VK_1){
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
        }
        if (keycode == KeyEvent.VK_2){
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
        }
        if (keycode == KeyEvent.VK_3){
            robot.keyPress(KeyEvent.VK_3);
            robot.keyRelease(KeyEvent.VK_3);
        }
        if (keycode == KeyEvent.VK_4){
            robot.keyPress(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_4);
        }
        if (keycode == KeyEvent.VK_5){
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
        }
        if (keycode == KeyEvent.VK_6){
            robot.keyPress(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_6);
        }
        if (keycode == KeyEvent.VK_7){
            robot.keyPress(KeyEvent.VK_7);
            robot.keyRelease(KeyEvent.VK_7);
        }
        if (keycode == KeyEvent.VK_8){
            robot.keyPress(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_8);
        }
        if (keycode == KeyEvent.VK_9){
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
        }

        if (keycode == KeyEvent.VK_0){
            robot.keyPress(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_0);
        }

    }
    public static void main(String[] args) throws  Exception{

        HashMap<Integer , String> phonelist = new HashMap<>();
        Random rand = new Random();
        HashMap<Integer , String> resultlist  = new HashMap<>();

        ITesseract instance = new Tesseract();

        instance.setDatapath("C:\\Users\\sedat\\Desktop\\Tess4J");
        instance.setLanguage("tur");

        //Rectangle rectangle = new Rectangle(0,480,200,480);


        int numbers = 1;
        while (true){

            int x = rand.nextInt(300,500);
            int y = rand.nextInt(100,500);
            int z = rand.nextInt(100,700);

            String number = "+905";
            number+=x;
            number+=y;
            number+=z;

            phonelist.put(numbers,number);
            numbers++;

            if(phonelist.size() == 1000){
                break;
            }

        }
        Robot robot = new Robot();
        int sayaç = 1;
        Thread.sleep(3000);
        Rectangle rectangle = new Rectangle(0,480,200,480);
        while (sayaç<phonelist.size()){


            BufferedImage image = robot.createScreenCapture(rectangle);
            String result =instance.doOCR(image);
            yaz(robot,phonelist.get(sayaç));
            System.out.println(result);
            if (!result.contains("sonuç bulunamadı")){
                sayaç++;
                Thread.sleep(2000);
                sil(robot,phonelist.get(sayaç));
                System.out.println(phonelist.get(sayaç));
            }
            else{
                Thread.sleep(2000);
                sil(robot,phonelist.get(sayaç));
                phonelist.remove(sayaç);
                sayaç++;

            }



        }



    }

    public  static void sil(Robot robot , String metin){

        for (int i =0;i<metin.length();i++){
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        }
    }

}