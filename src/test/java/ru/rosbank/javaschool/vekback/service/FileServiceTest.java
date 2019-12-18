package ru.rosbank.javaschool.vekback.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import ru.rosbank.javaschool.vekback.dto.UploadResponseDto;
import ru.rosbank.javaschool.vekback.exception.FileStorageException;
import ru.rosbank.javaschool.vekback.exception.UnsupportedFileTypeException;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FileServiceTest {

    @Test
    void saveMultipartJpeg() {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("image/jpeg");
        String uploadPath = "";

        FileService fileService = new FileService(uploadPath);
        UploadResponseDto dto = fileService.save(multipartFileMock);
        assertTrue(dto.getName().endsWith(".jpg"));

    }

    @Test
    void saveMultipartPng() {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("image/png");
        String uploadPath = "";

        FileService fileService = new FileService(uploadPath);
        UploadResponseDto dto = fileService.save(multipartFileMock);
        assertTrue(dto.getName().endsWith(".png"));

    }

    @Test
    void saveMultipartWebm() {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("video/webm");
        String uploadPath = "";

        FileService fileService = new FileService(uploadPath);
        UploadResponseDto dto = fileService.save(multipartFileMock);
        assertTrue(dto.getName().endsWith(".webm"));

    }

    @Test
    void saveMultipartMp4() {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("video/mp4");
        String uploadPath = "";

        FileService fileService = new FileService(uploadPath);
        UploadResponseDto dto = fileService.save(multipartFileMock);
        assertTrue(dto.getName().endsWith(".mp4"));

    }


    @Test
    void saveMultipartMp3() {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("audio/mp3");
        String uploadPath = "";

        FileService fileService = new FileService(uploadPath);
        UploadResponseDto dto = fileService.save(multipartFileMock);
        assertTrue(dto.getName().endsWith(".mp3"));

    }

    @Test
    void saveMultipartThrowUnsupportedFileTypeException() {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("");
        String uploadPath = "";

        FileService fileService = new FileService(uploadPath);
        assertThrows(UnsupportedFileTypeException.class, () -> fileService.save(multipartFileMock));
    }

    @Test
    void saveMultipartThrowFileStorageException() throws IOException {

        MultipartFile multipartFileMock = mock(MultipartFile.class);
        when(multipartFileMock.getContentType()).thenReturn("image/jpeg");
        String uploadPath = "";
        doThrow(IOException.class).when(multipartFileMock).transferTo((Path) any());
        FileService fileService = new FileService(uploadPath);
        assertThrows(FileStorageException.class, () -> fileService.save(multipartFileMock));
    }

}