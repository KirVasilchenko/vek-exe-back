package ru.rosbank.javaschool.vekback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.vekback.dto.PostResponseDto;
import ru.rosbank.javaschool.vekback.dto.PostSaveRequestDto;
import ru.rosbank.javaschool.vekback.entity.PostEntity;
import ru.rosbank.javaschool.vekback.exception.BadRequestException;
import ru.rosbank.javaschool.vekback.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
  private final PostRepository repository;

  public List<PostResponseDto> getAll() {
    return repository.findAll().stream()
        .map(PostResponseDto::from)
        .collect(Collectors.toList());
  }

  public PostResponseDto save(PostSaveRequestDto dto) {
    return PostResponseDto.from(repository.save(PostEntity.from(dto)));
  }

  public void removeById(int id) {
    repository.deleteById(id);
  }

  public List<PostResponseDto> searchByContent(String q) {
    return repository.findAllByContentLike(q).stream()
        .map(PostResponseDto::from)
        .collect(Collectors.toList());
  }

  public PostResponseDto likeById(int id) {
    final PostEntity entity = repository.findById(id)
        .orElseThrow(BadRequestException::new);
    // FIXME: bad practice, use update methods
    entity.setLikes(entity.getLikes() + 1);
    return PostResponseDto.from(entity);
  }

  public PostResponseDto dislikeById(int id) {
    final PostEntity entity = repository.findById(id)
        .orElseThrow(BadRequestException::new);
    // FIXME: bad practice, use update methods
    entity.setLikes(entity.getLikes() - 1);
    return PostResponseDto.from(entity);
  }
}
