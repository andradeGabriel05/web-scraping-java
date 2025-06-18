package com.wsj.services;


import com.wsj.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//https://www.w3schools.com/java/java_files_create.asp
public class HandleFile {
    Logger logger = LoggerFactory.getLogger(Main.class);

    public void createFile(String filename) {
        try {
            File myObj = new File("C:\\Windows\\Temp\\"+filename+".txt");
            if (myObj.createNewFile()) {
                logger.info("File created: {}", myObj.getName());
            } else {
                logger.warn("File already exists: {}", myObj.getName());
            }
        } catch (IOException e) {
            logger.error("An error occurred. {}", e);
//            e.printStackTrace();
        }
    }

    public void writeFile(String filename, String content) {
        try {
            FileWriter myWriter = new FileWriter("C:\\Windows\\Temp\\"+filename+".txt",true);
            myWriter.write(content + System.lineSeparator());
            myWriter.close();
        } catch (IOException e) {
            logger.error("An error occurred. {}", e);
//            e.printStackTrace();
        }
    }
}
