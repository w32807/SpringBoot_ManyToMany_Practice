package com.zerock.mreview.repository.search;

import com.zerock.mreview.entity.Member;
import com.zerock.mreview.entity.Movie;
import com.zerock.mreview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 쿼리 메서드로 처리
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    // 여기서 member를 위한 쿼리를 수행할 때만 EAGER(무조건 조인함), 나머지는 LAZY(필요에 따라 조인함)으로 처리하기위한 @EntityGraph를 선언함
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    // 바로 쿼리메소드를 사용해도 기능상으로는 문제가 없으나, delete 쿼리를 갯수만큼 반복하므로 비효율적이다.
    // 그래서 @Query 어노테이션의 쿼리로 처리하는 게 낫다.
    void deleteByMember(Member member);
}
