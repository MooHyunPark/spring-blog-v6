package com.example.blog.user;

import com.example.blog.board.BoardResponse;
import com.example.blog.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/login-form")
    public String login() {
        return "user/login-form";
    }
        
    
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO, HttpServletRequest request, Model model) {
        UserResponse.loginDTO dto = userService.login(loginDTO);
        request.getSession().setAttribute("sessionUser", dto);


        model.addAttribute("models", boardService.게시글목록보기());
        return "index";
    }

    @GetMapping("/join-form")
    public String join() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.join(joinDTO);

        return "index";
    }
}
