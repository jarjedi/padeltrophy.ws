package com.padeltrophy.controller;

import com.padeltrophy.controller.exception.ModelException;
import com.padeltrophy.util.file.FileUploader;
import com.padeltrophy.util.json.JsonTransformer;
import com.padeltrophy.util.log.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;

@RestController
public class UploadFileController {

    private Tracer tracer = new Tracer(UploadFileController.class.getName());

    @Autowired
    private JsonTransformer jsonTransformer;

    @Autowired
    private FileUploader fileUploader;

    @RequestMapping(value="/file/{type}/", method = RequestMethod.POST ,produces = "application/json")
    public void uploadFile(
            MultipartHttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable("type") String type) {
        try {
            tracer.trace("uploadFile :> POST METHOD");

            Iterator<String> itr = httpServletRequest.getFileNames();
            MultipartFile file = httpServletRequest.getFile(itr.next());
            File convFile = new File(file.getOriginalFilename());
            file.transferTo(convFile);

            String id = fileUploader.getUniqueGeneratedName();

            String fileName = file.getOriginalFilename();
            String fileURL = fileUploader.upfile(type+"/"+id, convFile);
            tracer.trace("uploadFile :> filename: " + fileName + " / size: " + file.getSize() / 1024);

            String jsonResponse = "{\"url\":\""+fileURL+"\"}";

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonResponse);

        }catch (Exception e){
            e.printStackTrace();
            ModelException mEx = new ModelException();
            mEx.setOperation("list");
            mEx.setService("player");
            mEx.setType(e.getMessage());
            mEx.setServerError(e.getStackTrace()[0].toString());
            String jsonEx = jsonTransformer.toJson(mEx);

            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEx);
            }catch (Exception ex){
                //
            }
        }
    }

}
