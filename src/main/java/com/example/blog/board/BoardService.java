package com.example.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public BoardResponse.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id);

        return new BoardResponse.DetailDTO(board);
    }

    public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id);

        return new BoardResponse.UpdateFormDTO(board);
    }

    @Transactional // 테스트 코드에서는 롤백을 해주고, 여기서는 커밋을 해준다.
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getTitle(), saveDTO.getContent());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO updateDTO) {
        boardRepository.update(id, updateDTO.getTitle(), updateDTO.getContent());
    }
}
