package com.marvin.springbootdeveloper1.service;

import com.marvin.springbootdeveloper1.domain.Article;
import com.marvin.springbootdeveloper1.dto.AddArticleRequest;
import com.marvin.springbootdeveloper1.dto.UpdateArticleRequest;
import com.marvin.springbootdeveloper1.repository.BlogRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        var req = request.toEntity();
        System.out.println("Debug//==========================================================================");
        String title = String.format("Title: [%s]", req.getTitle());
        System.out.println(title);
        String content = String.format("Content: [%s]", req.getContent());
        System.out.println(content);
        System.out.println("==========================================================================//Debug");

        return blogRepository.save(req);
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    
    public Article findById(Long id) throws IllegalAccessException {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalAccessException("not found: " + id));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
