package com.padeltrophy.util.file;


import com.padeltrophy.util.log.Tracer;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class FileUtil {

    private Tracer tracer = new Tracer(FileUtil.class.getName());

    private String getTmpFileName(){
        Date now = new Date();
        return "tmp_"+now.getTime();
    }

    public File scaleImage(File originalImageFile) {
        tracer.trace("scaleImage :> originalImageFile: "+originalImageFile.getPath()+" / "+originalImageFile.getAbsolutePath());
        FileOutputStream fos = null;
        Integer targetWidth = 300;
        Integer targetHeight = 300;
        String imageFormat = "jpg";

        try {
            BufferedImage image = ImageIO.read(originalImageFile);
            BufferedImage scaledImg = Scalr.resize(image, Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_WIDTH,
                    targetWidth, targetHeight, Scalr.OP_BRIGHTER);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImg, imageFormat, baos);
            baos.flush();
            byte[] scaledImageInByte = baos.toByteArray();
            baos.close();
            String fileName = this.getTmpFileName();
            File outputFile = new File("/tmp/"+fileName+"."+imageFormat);
            tracer.trace("scaleImage :> file name: "+fileName);
            fos = new FileOutputStream(outputFile);
            fos.write(scaledImageInByte);
            tracer.trace("scaleImage :> originalFile deleted: "+originalImageFile.delete());
            tracer.trace("scaleImage :> outputfile: "+outputFile.getPath());
            return outputFile;
        }catch (Exception e){
            tracer.trace("scaleImage :> error:"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
