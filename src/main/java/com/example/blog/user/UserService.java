package com.example.blog.user;


import com.example.blog._core.util.error.ex.Exception400;
import com.example.blog._core.util.error.ex.Exception401;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse.loginDTO login(UserRequest.LoginDTO loginDTO) {
        User userPS = userRepository.findByUsername(loginDTO.getUsername());

        if (!userPS.getPassword().equals(loginDTO.getPassword())) {
            throw new Exception401("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return new UserResponse.loginDTO(userPS);
    }

    // DB에 저장할 때 비밀번호를 hash로 변경하여 넣을 수 있도록 인코더를 매개 변수로 전달.
    @Transactional
    public void join(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity());

    }
}
