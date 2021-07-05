package com.zerock.mreview.repository;

import com.zerock.mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m" +
            " left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m group by m ")
    // 데이터를 가져오는 쿼리, count를 가져오는 쿼리 총 2번 실행된다.
    Page<Object[]> getListPage(Pageable pageable);

    @Query("SELECT m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m" +
            " left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m " +
            " where m.mno = :mno group by mi ")
    List<Object[]> getMovieWithAll(Long mno);
}
