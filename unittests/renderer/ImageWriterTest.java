package renderer;

import org.junit.jupiter.api.Test;
import renderer.*;
import primitives.*;

import static java.awt.Color.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        int interaval = 50;
        ImageWriter imageWriter = new ImageWriter("yellow", 800,500);
        for(int i=0;i<imageWriter.nX();i++){
            for(int j=0;j<imageWriter.nY();j++ ){
                imageWriter.writePixel(i,j,new Color(YELLOW));

            }
        }
        for(int i=0;i<imageWriter.nX();i+=interaval){
            for(int j=0;j<imageWriter.nY();j++){
                imageWriter.writePixel(i,j,new Color(RED));
            }
        }
        for(int i=0;i<imageWriter.nY();i+=interaval){
            for(int j=0;j<imageWriter.nX();j++){
                imageWriter.writePixel(j,i,new Color(RED));
            }
        }
        imageWriter.writeToImage();
    }



}