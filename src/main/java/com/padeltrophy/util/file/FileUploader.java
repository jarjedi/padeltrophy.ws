package com.padeltrophy.util.file;

import java.io.File;

public interface FileUploader {

    String upfile(String name, File file);

    String getUniqueGeneratedName();
}
