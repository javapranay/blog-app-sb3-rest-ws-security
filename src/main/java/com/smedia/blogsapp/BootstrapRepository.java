package com.smedia.blogsapp;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.smedia.blogsapp.entity.Comment;
import com.smedia.blogsapp.entity.Post;
import com.smedia.blogsapp.entity.Role;
import com.smedia.blogsapp.entity.User;
import com.smedia.blogsapp.repository.CommentRepository;
import com.smedia.blogsapp.repository.PostRepository;
import com.smedia.blogsapp.repository.RoleRepository;
import com.smedia.blogsapp.repository.UserRepository;

@Component
public class BootstrapRepository implements CommandLineRunner {

	private PostRepository repository;
	private CommentRepository commentRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	// As we have one attribute in class, we can skip writing @Autowired on constructor.
	public BootstrapRepository(PostRepository repository, CommentRepository commentRepository, 
			UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.repository = repository;
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		Post post1 = new Post(1L, "First post", "description-1", "content-1");
		Post post2 = new Post(2L, "Second post", "simple text post", "post.txt");
		Post post3 = new Post(3L, "Third post", "post with video", "tour.mp4");
		Post post4 = new Post(4L, "Forth post", "post with image", "intro.gif");
		Post post5 = new Post(5L, "Fifth post", "post with PDF", "Notification.pdf");
//		Post post6 = new Post(6L, "Sixth post", "post with Animation", "Advertisement.gif");
//		Post post7 = new Post(7L, "Seventh post", "post with Picture", "selfie.jpeg");
//		Post post8 = new Post(8L, "Eighth post", "post with Game", "pingpong.game");
//		Post post9 = new Post(9L, "Ninth post", "post with music", "song.mp3");
//		Post post10 = new Post(10L, "Tenth post", "post with Http link", "Hyperlink");
//		Post post11 = new Post(11L, "Eleventh post", "post with Form", "Http Form to fill");
		// A-Z = 65-90 & a-z = 97-122
		repository.save(post1);
		repository.save(post2);
		repository.save(post3);
		repository.save(post4);
		repository.save(post5);
//		repository.save(post6);
//		repository.save(post7);
//		repository.save(post8);
//		repository.save(post9);
//		repository.save(post10);
//		repository.save(post11);
		
		Comment comment1 = new Comment(1L, "pranay", "pr@gmail.com", "Good", post1);
		Comment comment2 = new Comment(2L, "Vinay", "vn@gmail.com", "Nice", post1);
		Comment comment3 = new Comment(3L, "Manoj", "mg@gmail.com", "worst", post2);
		Comment comment4 = new Comment(4L, "Veera", "vr@gmail.com", "not bad", post2);
		commentRepository.save(comment1);
		commentRepository.save(comment2);
		commentRepository.save(comment3);
		commentRepository.save(comment4);
		
		User user1 = new User(1L, "admin", "admin", "admin@gmail.com", passwordEncoder.encode("admin"));
		User user2 = new User(2L, "pranay", "pranay", "pranay@gmail.com", passwordEncoder.encode("pranay"));
		userRepository.save(user1);
		userRepository.save(user2);
		
		Role role1 = new Role(1L, "ROLE_ADMIN");
		Role role2 = new Role(2L, "ROLE_USER");
		roleRepository.save(role1);
		roleRepository.save(role2);
		
		// add data to USERS_ROLES table manually
	}

}
