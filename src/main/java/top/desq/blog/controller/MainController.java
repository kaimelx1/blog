package top.desq.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.desq.blog.model.Post;
import top.desq.blog.repository.PostRepository;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private PostRepository repository;

    @GetMapping("/")
    public String list(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("title", "Блог");
        model.addAttribute("content", "list");
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        model.addAttribute("content", "about");
        return "main";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("title", "Добавить статью");
        model.addAttribute("content", "add");
        return "main";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable(value = "id") long id, Model model) {

        if (!repository.existsById(id)) {
            return "redirect:/";
        }

        model.addAttribute("title", "Статья");
        model.addAttribute("content", "post");
        Post post = repository.findById(id).get();
        incrementViews(post);
        model.addAttribute("post", post);

        return "main";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") long id, Model model) {

        if (!repository.existsById(id)) {
            return "redirect:/";
        }

        model.addAttribute("title", "Редактирование");
        model.addAttribute("content", "edit");
        model.addAttribute("post", repository.findById(id).get());
        return "main";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String title, @RequestParam String description, @RequestParam String text, Model model) {
        Post post = new Post(title, description, text);
        repository.save(post);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, @RequestParam String text, Model model) {
        Post post = repository.findById(id).get();
        post.setTitle(title);
        post.setDescription(description);
        post.setText(text);
        post.setDate(new Date());
        repository.save(post);
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removePost(@PathVariable(value = "id") long id, Model model) {
        Post post = repository.findById(id).get();
        repository.delete(post);
        return "redirect:/";
    }

    /**
     * Increment views
     *
     * @param post
     */
    private void incrementViews(Post post) {
        post.setViews(post.getViews() + 1);
        repository.save(post);
    }
}
