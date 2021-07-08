package com.zerock.mreview.service;

import com.zerock.mreview.dto.MovieDTO;
import com.zerock.mreview.dto.PageRequestDto;
import com.zerock.mreview.dto.PageResultDto;
import com.zerock.mreview.entity.Movie;
import com.zerock.mreview.entity.MovieImage;
import com.zerock.mreview.repository.MovieImageRepository;
import com.zerock.mreview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImageRepository imageRepository;

    @Override
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });
        return movie.getMno();
    }

    @Override
    public PageResultDto<MovieDTO, Object[]> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("mno").descending());
        Page<Object[]> result = movieRepository.getListPage(pageable);
        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO((Movie) arr[0], (List<MovieImage>) (Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2], (Long) arr[3]));

        return new PageResultDto<>(result, fn);
    }

    @Override
    // Movie가 1개일 때 이미지가 여러 개면, 중복된 Movie 정보를 가진 행이 이미지 갯수만큼 조회된다.
    public MovieDTO getMovie(Long mno) {
        // 일단 DB에서 조회한 리스트를 가져온다
        List<Object[]> result = movieRepository.getMovieWithAll(mno);
        List<MovieImage> movieImageList = new ArrayList<>();
        result.forEach(arr -> {
            movieImageList.add((MovieImage) arr[1]);
        });
        // 1개의 Movie, avg, reviewCnt 데이터만 가져온다.(쿼리에서 엔티티 단위로 조회하므로 배열에 각 엔티티(값)들이 담긴다
        return entitiesToDTO((Movie) result.get(0)[0], movieImageList, (Double) result.get(0)[2],(Long) result.get(0)[3]);
    }
}
