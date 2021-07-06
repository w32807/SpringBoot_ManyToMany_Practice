package com.zerock.mreview.service;

import com.zerock.mreview.dto.MovieDTO;
import com.zerock.mreview.dto.MovieImageDTO;
import com.zerock.mreview.entity.Movie;
import com.zerock.mreview.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    Long register(MovieDTO movieDTO);

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
                return movieImage; // map 안에서 특정한 작업 후 return 값을 준다. (단순하면 그냥 return 없이, 중괄호 없이 처리가능)
            }).collect(Collectors.toList());
            entityMap.put("imgList", movieImageList);
        }
        return entityMap;
    }
}
