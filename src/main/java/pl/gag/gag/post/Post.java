package pl.gag.gag.post;

import javax.persistence.Entity;

import lombok.*;
import pl.gag.gag.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true,length = 45)
    private String title;


    @Column(nullable = false, length = 64)
    private String description;

    @Column(nullable = true, length = 64)
    private String photos;

    @Column(nullable = false)
    private Date addDate;


    @ElementCollection()
    private List<String> tags = new ArrayList<>();


    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/" + id + "/" + photos;
    }

    @Transient
    public String getPostView(){
        if (photos == null || id == null) return null;
        return "post/post_view?id=" + id ;
    }

    @Transient
    public String getPostView2(){
        if (photos == null || id == null) return null;
        return "post_view?id=" + id ;
    }
}
