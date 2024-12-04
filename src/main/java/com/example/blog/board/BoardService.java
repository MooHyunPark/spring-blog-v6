package com.example.blog.board;

import com.example.blog._core.util.error.ex.Exception403;
import com.example.blog._core.util.error.ex.Exception404;

import com.example.blog.user.User;
import com.example.blog.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록보기() {
        List<BoardResponse.DTO> dtos = new ArrayList<>();

        List<Board> boardList = boardRepository.findAll();

        for (Board board : boardList) {
            BoardResponse.DTO dto = new BoardResponse.DTO(board);
            dtos.add(dto);
        }

        return dtos;
    }


    public BoardResponse.DetailDTO 게시글상세보기(int id, UserResponse.loginDTO sessionUser) {
        Board board = boardRepository.findByIdJoinUserAndReply(id)
                .orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다 : " + id));

        return new BoardResponse.DetailDTO(board,sessionUser);
    }

    public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다 : " + id));

        return new BoardResponse.UpdateFormDTO(board);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.toEntity());
    }

    @Transactional
    public void 게시글삭제(int id, UserResponse.loginDTO sessionUser) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다 : " + id));
        if (sessionUser == null || !board.getUser().getId().equals(sessionUser.getId())) {
            throw new Exception403("권한이 없습니다.");
        }
        boardRepository.delete(id);
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO updateDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다 : " + id));
        board.update(updateDTO.getTitle(), updateDTO.getContent());
    } // 영속화된 객체 상태 변경 - update + commit => 더티 체킹
}
