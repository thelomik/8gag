package pl.gag.gag.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gag.gag.post.Post;
import pl.gag.gag.post.PostRepository;
import pl.gag.gag.post.PostService;
import pl.gag.gag.user.User;
import pl.gag.gag.user.UserRepository;

import java.util.List;

@Controller
public class AppControler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;


    @GetMapping("")
    public String viewHomePage(Model model){

        List<Post> postList = postRepository.findAll();
        model.addAttribute("postList",postList);
        return "index";}


    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/register")
    public String showSingUpForm(Model model){
        model.addAttribute("user",new User());
        return "register_form";
    }


    @PostMapping("/process_register")
    public String processRegistration( User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "redirect:/login?registration_success";
    }

    @GetMapping("login")
    public String showLoginPage(){
        return "login";

    }

    @GetMapping("/addPhoto")
    public String addPhoto(){
        return "add_photo";
    }



}
