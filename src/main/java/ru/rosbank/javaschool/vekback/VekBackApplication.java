package ru.rosbank.javaschool.vekback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.rosbank.javaschool.vekback.entity.ItemEntity;
import ru.rosbank.javaschool.vekback.entity.PostEntity;
import ru.rosbank.javaschool.vekback.repository.ItemRepository;
import ru.rosbank.javaschool.vekback.repository.PostRepository;

import java.util.List;

@SpringBootApplication
public class VekBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(VekBackApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(PostRepository postRepository) {
    return args -> postRepository.saveAll(List.of(
        new PostEntity(0, "Поздравляем с началом зимы!", "https://psv4.userapi.com/c856332/u47270812/docs/d13/1bee4329fc6d/Little_Snowflake_-_Kids_Songs_-_Super_Simple_Songs.webm", false, 0),
            new PostEntity(0, "В нашем зале 14.12 в 15:00 в честь открытия будет показ сериала \"Доктор Хаус\".", "https://psv4.userapi.com/c848128/u12185853/docs/d3/798ba14413fa/House.mp4", false, 0),
            new PostEntity(0, "Добро пожаловать на презентацию самого лучшего проекта уходящего года.", null, false, 100)
    ));
  }

  @Bean
  public CommandLineRunner runner2(ItemRepository itemRepository) {
    return args -> itemRepository.saveAll(List.of(
            new ItemEntity(0, "Disc", 120, 1, "https://sun9-61.userapi.com/c200720/v200720303/1433d/elJvQPg_9XI.jpg", "Compact disc"),
            new ItemEntity(0, "Mouse", 350, 2, "https://sun9-31.userapi.com/c855736/v855736303/1a3c34/wHYkBNqiRBM.jpg", "Office mouse"),
            new ItemEntity(0, "Mouse pad", 280, 3, "https://sun9-61.userapi.com/c855736/v855736303/1a3c3c/AI-ZnwdjSK0.jpg", "Office mouse pad")
    ));
  }

}
