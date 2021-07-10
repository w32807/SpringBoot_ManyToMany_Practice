package com.zerock.mreview.service;

import com.zerock.mreview.dto.*;
import com.zerock.mreview.entity.Member;
import com.zerock.mreview.entity.Movie;
import com.zerock.mreview.entity.MovieImage;
import com.zerock.mreview.entity.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ReviewService {
    List<ReviewDTO> getListOfMovie(Long mno);

    Long register(ReviewDTO movieReviewDTO);

    void modify(ReviewDTO movieReviewDTO);

    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO){

        Review movieReview = Review.builder().reviewnum(movieReviewDTO.getReviewnum()).movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                            .member(Member.builder().mid(movieReviewDTO.getMid()).build()).grade(movieReviewDTO.getGrade()).text(movieReviewDTO.getText()).build();

        return movieReview;
    }

    default ReviewDTO entityToDTO(Review movieReview){
        ReviewDTO movieReviewDTO = ReviewDTO.builder().reviewnum(movieReview.getReviewnum()).mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid()).nickname(movieReview.getMember().getNickname()).email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade()).text(movieReview.getText()).regDate(movieReview.getRegDate()).modDate(movieReview.getModDate()).build();
        return movieReviewDTO;
    }
}
