package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.util.FileUtils;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {
    private static volatile DataSource INSTANCE;
    private final static String titleFile = "/news.txt";
    private final static String authorFile = "/authors.txt";
    private final static String contentFile = "/content.txt";
    private final static int newsAmount = 20;
    private final static int authorsAmount = 20;
    @Getter
    private final List<News> newsStorage = new ArrayList<>();
    @Getter
    private final List<Author> authorStorage = new ArrayList<>();

    private DataSource() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> authors = fileUtils.readFileContent(authorFile);
        List<String> titles = fileUtils.readFileContent(titleFile);
        List<String> contents = fileUtils.readFileContent(contentFile);

        fillNews(titles, contents);
        fillAuthors(authors);
    }

    public static DataSource getInstance() throws IOException {
        DataSource result = INSTANCE;
        if (result == null) {
            synchronized (DataSource.class) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new DataSource();
            }
        }
        return result;
    }

    private void fillAuthors(List<String> authors) {
        for (int i = 0; i < authorsAmount; i++) {
            authorStorage.add(new Author(i, authors.get(i)));
        }
    }

    private void fillNews(List<String> titles, List<String> contents) {
        for (int i = 0; i < newsAmount; i++) {
            Random random = new Random();
            LocalDateTime now = LocalDateTime.now();

            String title = titles.get(i);
            String content = contents.get(i);
            LocalDateTime created = now.minusDays(30 + random.nextInt(30));
            LocalDateTime updated = now.minusDays(random.nextInt(30));

            News news = new News(i, title, content, created, updated, i);
            newsStorage.add(news);
        }
    }

}
