package com.example.blog.user;

import com.example.blog.board.BoardResponse;
import com.example.blog.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/login-form")
    public String login() {
        return "user/login-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
        
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        UserResponse.loginDTO dto = userService.login(loginDTO);
        session.setAttribute("sessionUser", dto);

        return "redirect:/";
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
