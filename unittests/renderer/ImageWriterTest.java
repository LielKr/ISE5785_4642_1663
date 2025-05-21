package renderer;

import org.junit.jupiter.api.Test;
import renderer.*;
import primitives.*;
class ImageWriterTest {

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("yellow", 800,500);
        for(int i=0;i<imageWriter.nX();i++){
            for(int j=0;j<imageWriter.nY();j++ ){
                imageWriter.writePixel(i,j,new Color(java.awt.Color.YELLOW));

            }
        }
        imageWriter.writeToImage();
    }



}