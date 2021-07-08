package com.zerock.mreview.controller;

import com.zerock.mreview.dto.MovieDTO;
import com.zerock.mreview.dto.PageRequestDto;
import com.zerock.mreview.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
@Log4j2
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes rttr){
        rttr.addFlashAttribute("msg", movieService.register(movieDTO));
        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model){
        model.addAttribute("result", movieService.getList(pageRequestDto));
    }

    @GetMapping({"/read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDto requestDto, Model model){
        model.addAttribute("dto", movieService.getMovie(mno));
    }
}
