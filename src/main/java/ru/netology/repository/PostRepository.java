package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> postList;
  private final AtomicLong postIdCounter = new AtomicLong();

  public PostRepository() {
    this.postList = new ConcurrentHashMap<>();
  }
  public List<Post> all() {
    return new ArrayList<>(postList.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postList.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = postIdCounter.incrementAndGet();
      post.setId(id);
      postList.put(id, post);
      return post;
    }
    if (postList.containsKey(post.getId())) {
      postList.get(post.getId()).setContent(post.getContent());
      return postList.get(post.getId());
    } else
      return null;
  }

  public void removeById(long id) {
    postList.remove(id);
  }
}
