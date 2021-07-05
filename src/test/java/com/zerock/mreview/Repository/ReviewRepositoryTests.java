package com.zerock.mreview.Repository;

import com.zerock.mreview.entity.Member;
import com.zerock.mreview.entity.Movie;
import com.zerock.mreview.entity.Review;
import com.zerock.mreview.repository.search.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReviews(){
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long)(Math.random() * 100) + 1;
            // 리뷰어 번호
            Long mid = ((long) (Math.random()*100) + 1);
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder().member(member).movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1).text("이 영화에 대한 소감" + i).build();

            reviewRepository.save(review);
        });
    }

    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder().mno(5L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {
            System.out.println(movieReview);
        });
    }
}
