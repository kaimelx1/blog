package top.desq.blog.repository;

import org.springframework.data.repository.CrudRepository;
import top.desq.blog.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
