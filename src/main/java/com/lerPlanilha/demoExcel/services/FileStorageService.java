package com.lerPlanilha.demoExcel.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final String SAVE_DIRECTORY = "C:\\teste";

    public void saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Path.of(SAVE_DIRECTORY, fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

}

// TREXO SE QUISER SALVAR COM DATA FILE

// private static final String SAVE_DIRECTORY = "C:\\teste";
// private static final SimpleDateFormat dateFormat = new
// SimpleDateFormat("yyyyMMdd_HHmmss");

// public void saveFile(MultipartFile file) throws IOException {
// String originalFileName = file.getOriginalFilename();
// String uniqueFileName = generateUniqueFileName(originalFileName);

// Path filePath = Path.of(SAVE_DIRECTORY, uniqueFileName);

// Files.copy(file.getInputStream(), filePath,
// StandardCopyOption.REPLACE_EXISTING);
// }

// private String generateUniqueFileName(String originalFileName) {
// String timestamp = dateFormat.format(new Date(0));
// return timestamp + "_" + originalFileName;
// }
