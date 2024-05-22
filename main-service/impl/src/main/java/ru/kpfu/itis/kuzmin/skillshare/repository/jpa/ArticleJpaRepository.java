package ru.kpfu.itis.kuzmin.skillshare.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;

import java.sql.Date;
import java.util.List;

public interface ArticleJpaRepository {
    Page<ArticleEntity> findArticlesByFilter(Pageable pageable, String search, Long ratingThreshold,
                                             Date from, Date to, List<TagEntity> tags);
}
