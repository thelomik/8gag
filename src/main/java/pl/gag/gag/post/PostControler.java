package pl.gag.gag.post;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gag.gag.user.User;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostControler {

    @ModelAttribute("post")
    public Post post() {
        return new Post();
    }

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;


    @GetMapping("/add")
    public String getAddTreatmentPage(Model model) {

        model.addAttribute("post", new Post());
        return "add_post";
    }

    @PostMapping("/add")
    public String processRegistration(Post post,
                                      @RequestParam("image") MultipartFile multipartFile) throws IOException {

        Date date = new Date();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        post.setPhotos(fileName);
        post.setAddDate(date);
        Post post1 = postRepository.save(post);

        String uploadDir = "user-photos/" + post1.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/?post_add";
    }

    @GetMapping("post_view")
    public String postView(Model model,
                           @RequestParam(value="id") Integer id){
        Post post1 = postRepository.getById(id);
        model.addAttribute("post",post1);

        return "post_view";
    }

    @GetMapping("/search")
    public String search(@Param("keyword")String keyword ,Model model) {
        List<Post> searchResult = postRepository.search(keyword);
        model.addAttribute("searchResult",searchResult);
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageTitle","Search results for :" + keyword);
      return "result";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        postService.deletePostById(id);
        redirectAttributes.addFlashAttribute("message", "Post deleted successfully");
        return "redirect:/";
    }



}
