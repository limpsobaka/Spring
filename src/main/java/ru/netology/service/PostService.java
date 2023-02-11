package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all().stream().filter(e -> !e.isRemoved()).collect(Collectors.toList());
  }

  public Post getById(long id) {
    Post post = repository.getById(id).orElseThrow(NotFoundException::new);
    if (post.isRemoved()){
      throw new NotFoundException();
    } else {
      return post;
    }
  }

  public Post save(Post post) {
    Post savedPost = repository.save(post);
    if (savedPost != null && !savedPost.isRemoved())
      return savedPost;
    else
      throw new NotFoundException();
  }

  public void removeById(long id) {
    if (!repository.removeById(id)) {
      throw new NotFoundException();
    }
  }
}

