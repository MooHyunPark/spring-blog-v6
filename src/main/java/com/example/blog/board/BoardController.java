package com.example.blog.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@RequiredArgsConstructor// final이 붙어있는 변수의 생성자를 만들어준다
@Controller
public class BoardController {

    private final BoardService boardService;

    // 컨트롤러는 view resolver 클래스를 가지고 있고, return 문자열과 동일한 파일을 찾아서 실행한다.
    @GetMapping("/")
    public String list(Model model) { // DS(request 객체를 model이라는 객체로 랩핑해서 전달해준다.

        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);
        return "index";
    }


    /**
     * 쿼리스트림(where절) : /board?title=바다
     * 패스변수(where절) : /board/1
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);
        return "detail";
    }

    @GetMapping("/board/save-form")
    public String save() {
        return "save-form";
    }

    @PostMapping("/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {

        boardService.게시글쓰기(saveDTO);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        BoardResponse.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
        model.addAttribute("model", updateFormDTO);
        return "update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@Valid BoardRequest.UpdateDTO updateDTO, Errors errors, @PathVariable("id") Integer id) {

        boardService.게시글수정(id, updateDTO);
        return "redirect:/board/" + id;
    }
}


