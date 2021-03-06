package com.zerock.mreview.service;

import com.zerock.mreview.dto.MovieDTO;
import com.zerock.mreview.dto.MovieImageDTO;
import com.zerock.mreview.dto.PageRequestDto;
import com.zerock.mreview.dto.PageResultDto;
import com.zerock.mreview.entity.Movie;
import com.zerock.mreview.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    Long register(MovieDTO movieDTO);

    PageResultDto<MovieDTO, Object[]> getList(PageRequestDto requestDto);

    MovieDTO getMovie(Long mno);

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt){
        MovieDTO movieDTO = MovieDTO.builder().mno(movie.getMno()).title(movie.getTitle()).regDate(movie.getRegDate())
                            .modDate(movie.getModDate()).build();
        List<MovieImageDTO> movieImageList = movieImages.stream().map(
            movieImage -> {
                return MovieImageDTO.builder().imgName(movieImage.getImgName()).path(movieImage.getPath()).uuid(movieImage.getUuid()).build();
            }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO){
        Map<String, Object> entityMap = new HashMap<>();
        Movie movie = Movie.builder().mno(movieDTO.getMno()).title(movieDTO.getTitle()).build();
        entityMap.put("movie",movie);
        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0){
            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {
                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage; // map ????????? ????????? ?????? ??? return ?????? ??????. (???????????? ?????? return ??????, ????????? ?????? ????????????)
            }).collect(Collectors.toList());
            entityMap.put("imgList", movieImageList);
        }
        return entityMap;
    }
}
