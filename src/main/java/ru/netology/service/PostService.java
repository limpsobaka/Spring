package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    Post savedPost = repository.save(post);
    if (savedPost != null)
      return savedPost;
    else
      throw new NotFoundException("Post #" + post.getId() + " not found");
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

