package pl.gag.gag.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gag.gag.post.Post;
import pl.gag.gag.post.PostRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PostRepository repo;


}