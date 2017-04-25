package com.padeltrophy.util.file;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.padeltrophy.util.log.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class S3FileUploader implements FileUploader{

    private Tracer tracer = new Tracer(S3FileUploader.class.getName());

    /**
     * Codificacion de los objetos que se suben
     */
    private static final String FILE_ENCODING_PROP = "ENCODING";
    @Value("${padeltrophy.s3.encoding}")
    private String encoding=null;

    /**
     * Configuracion de cacheo de objetos
     */
    @Value("${padeltrophy.s3.cache.maxage}")
    private String cacheControl=null;

    @Value("${padeltrophy.s3.bucket}")
    private String bucketName=null;

    @Value("${padeltrophy.s3.url}")
    private String URL;

    @Value("${padeltrophy.s3.accesskey}")
    private String accessKey;

    @Value("${padeltrophy.s3.passKey}")
    private String secretKey;

    public String getUniqueGeneratedName(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMsSSSSSSS");
        String datetime = ft.format(dNow);
        tracer.trace("getUniqueGeneratedName(): name:>"+datetime);
        return datetime;
    }

    public String upfile(String name, File file) {
        try{
            AmazonS3 s3 = this.getS3Conection();
            FileUtil util = new FileUtil();

            return this.upFileInBucket(s3,this.bucketName,name,util.scaleImage(file),this.encoding);
        }catch (Exception e){
            tracer.trace("upfile(): error:>"+e);
            return null;
        }
    }

    /**
     * Method that gets S3 connection
     * @return AmazonS3
     * @throws Exception
     */
    public AmazonS3 getS3Conection() throws Exception{
        try{
            tracer.trace("getConectionS3(): Intendando recuperar una conexion con Amazon");
            ClientConfiguration cl=new ClientConfiguration();
            cl.setConnectionTimeout(5000);
            cl.setProtocol(Protocol.HTTP);
            tracer.trace(".getConectionS3(): Cliente configurado, procedemos a conectar...");
            try{
                tracer.trace(".getConectionS3(): AWS bucket:"+bucketName);
                BasicAWSCredentials awsCreds = new BasicAWSCredentials(
                        accessKey,
                        secretKey);
                AmazonS3 s3 = new AmazonS3Client(awsCreds,cl);
                tracer.trace(".getConectionS3(): Conexion establecida");
                return s3;
            }catch(Exception e){
                tracer.trace(".getConectionS3(): Error al conectar:> " + e);
                throw e;
            }
        }catch(Exception e){
            tracer.trace(".getConectionS3(): Error al conectar:> " + e);
            throw e;
        }
    }



    public String upFileInBucket(AmazonS3 s3, String bucketName, String fileKey, File file, String customEncoding) throws Exception{
        try{
            tracer.trace("upFileInBucket(s3, bucketName = "+bucketName+", fileKey = "+fileKey+", file)");
            if(file!=null){

                String contentType = getMimeTypeException(file);
                tracer.trace(".upFileInBucket(s3, bucketName="+bucketName+", fileKey="+fileKey+", file): contentType: "+contentType);
                tracer.trace(".upFileInBucket(s3, bucketName="+bucketName+", fileKey="+fileKey+", file): fileName: "+file.getName());

                ObjectMetadata objMeta=new ObjectMetadata();
                if (customEncoding == null) objMeta.setContentEncoding(this.encoding);
                else objMeta.setContentEncoding(customEncoding);
                objMeta.setCacheControl(this.cacheControl);
                objMeta.setContentType(contentType);

                String fileExtension = (this.getFileExtension(file)!=null)?"."+this.getFileExtension(file):"";
                String fileCompletePath = fileKey+fileExtension;
                PutObjectRequest object=new PutObjectRequest(bucketName, fileCompletePath, file);
                object.setMetadata(objMeta);
                CannedAccessControlList cannedAcl=CannedAccessControlList.PublicRead;
                object.setCannedAcl(cannedAcl);

                s3.putObject(object);
                tracer.trace(".upFileInBucket(s3, bucketName="+bucketName+", fileKey="+fileKey+", file): file uploaded");
                tracer.trace(".upFileInBucket(s3, bucketName=" + bucketName + ", fileKey=" + fileKey + ", file): tmp file deleted: " + file.delete());
                return this.URL+"/"+this.bucketName+"/"+fileCompletePath;
            }else{
                tracer.trace(".upFileInBucket(s3, bucketName=" + bucketName + ", fileKey=" + fileKey + ", file): file==NULL");
            }
            return "";

        }catch(Exception e){
            tracer.trace(".upFileInBucket(): Error:> "+e);
            throw e;
        }finally {
            tracer.trace(".upFileInBucket(s3, bucketName=" + bucketName + ", fileKey=" + fileKey + ", file): tmp file deleted: " + file.delete());
        }
    }

    public String getMimeTypeException(File file){
        String fileKey=file.getAbsolutePath();
        int index = fileKey.lastIndexOf(".")+1;
        if(index>=0){
            String extension = fileKey.substring(index,fileKey.length());
            tracer.trace("getMimeTypeException(): extension:" + extension);
            if ("png".equals(extension))
                return "image/png";
            else if ("css".equals(extension))
                return "text/css";
            else if ("jpg".equals(extension))
                return "image/jpg";
            else
                return new MimetypesFileTypeMap().getContentType(file);
        }else{
            return new MimetypesFileTypeMap().getContentType(file);
        }
    }

    public String getFileExtension(File file){
        String fileKey=file.getAbsolutePath();
        int index = fileKey.lastIndexOf(".")+1;
        if(index>=0){
            String extension = fileKey.substring(index,fileKey.length());
            tracer.trace("getFileExtension(): extension:" + extension);
            return extension;
        }
        tracer.trace("getFileExtension(): extension: none");
        return null;
    }
}
