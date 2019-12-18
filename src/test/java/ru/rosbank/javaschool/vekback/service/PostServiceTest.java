package ru.rosbank.javaschool.vekback.service;

import ru.rosbank.javaschool.vekback.exception.BadRequestException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.rosbank.javaschool.vekback.dto.PostResponseDto;
import ru.rosbank.javaschool.vekback.dto.PostSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.PostEntity;
import ru.rosbank.javaschool.vekback.repository.PostRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceTest {

    @Test
    void save() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        PostSaveRequestDto dto = new PostSaveRequestDto(1, "content", "");
        PostEntity post = new PostEntity(1, "content", "", false, 0);
        when(repoMock.save(post)).thenReturn(post);
        PostResponseDto expected = new PostResponseDto(1, "content", "", 0);
        PostResponseDto actual = service.save(dto);
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        PostEntity post = new PostEntity(1, "content", "", false, 0);
        List<PostEntity> list = new ArrayList<>();
        list.add(post);
        when(repoMock.findAll()).thenReturn(list);
        PostResponseDto dto = new PostResponseDto(1, "content", "", 0);
        List<PostResponseDto> listDto = new ArrayList<>();
        listDto.add(dto);
        List<PostResponseDto> actual = service.getAll();
        assertIterableEquals(actual, listDto);
    }


    @Test
    void removeById() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        PostEntity post = new PostEntity(1,"content",null,false,0);
        List<PostEntity> list = new ArrayList<>();
        list.add(post);
        when(repoMock.save(post)).thenReturn(post);
        when(repoMock.findAll()).thenReturn(Collections.emptyList());
        PostSaveRequestDto dto = new PostSaveRequestDto(1, "content", null);
        PostResponseDto dtoReturn = service.save(dto);
        PostResponseDto dtoMustReturn = new PostResponseDto(1, "content", null, 0);
        service.removeById(1);
        List<PostResponseDto> result=service.getAll();
        assertEquals(dtoMustReturn, dtoReturn);
        assertEquals(Collections.emptyList(), result);
    }


    @Test
    void searchByContent() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        PostEntity post = new PostEntity(1, "content", "", false, 0);
        List<PostEntity> list = new ArrayList<>();
        list.add(post);
        String q = "content";
        when(repoMock.findAllByContentLike(q)).thenReturn(list);
        PostResponseDto dto = new PostResponseDto(1, "content", "", 0);
        List<PostResponseDto> listDto = new ArrayList<>();
        listDto.add(dto);
        List<PostResponseDto> actual = service.searchByContent(q);
        assertIterableEquals(actual, listDto);
    }

    @Test
    void likeByIdCorrect() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        PostEntity post = new PostEntity(1,"content",null,false,0);
        PostResponseDto expected = new PostResponseDto(1,"content",null,1);
        when(repoMock.findById(1)).thenReturn(Optional.of(post));
        PostResponseDto result = service.likeById(1);
        assertEquals(result, expected);
    }

    @Test
    void likeByIdThrowsException() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        when(repoMock.findById(1)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()->service.likeById(1));
    }

    @Test
    void dislikeByIdCorrect() {
        PostRepository repoMock = mock(PostRepository.class);
        PostService service = new PostService(repoMock);
        PostEntity post = new PostEntity(1,"content",null,false,1);
        when(repoMock.findById(1)).thenReturn(Optional.of(post));
        PostResponseDto expected = new PostResponseDto(1,"content",null,0);
        PostResponseDto result = service.dislikeById(1);
        assertEquals(result, expected);
    }

    @Test
    void dislikeByIdThrowsException() {
        PostRepository repoMock=mock(PostRepository.class);
        PostService service=new PostService(repoMock);
        when(repoMock.findById(1)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()->service.dislikeById(1));
    }
}