package com.marvin.springbootdeveloper1.controller;

import com.marvin.springbootdeveloper1.domain.Article;
import com.marvin.springbootdeveloper1.dto.AddArticleRequest;
import com.marvin.springbootdeveloper1.dto.ArticleListViewResponse;
import com.marvin.springbootdeveloper1.dto.ArticleResponse;
import com.marvin.springbootdeveloper1.dto.UpdateArticleRequest;
import com.marvin.springbootdeveloper1.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController
{
    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article saveArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) throws IllegalAccessException {
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) throws IllegalAccessException {
        blogService.delete(id);

        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity
                .ok()
                .body(updateArticle);
    }
}
