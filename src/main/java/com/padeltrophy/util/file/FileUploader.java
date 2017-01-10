package com.padeltrophy.util.file;

import java.io.File;

/**
 * Created by JLRB002 on 25/09/2015.
 */
public interface FileUploader {

    String upfile(String name, File file);

    String getUniqueGeneratedName();
}
