package com.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long mno;
    private String title;
    private double avg; // 영화의 평균 평점
    private int reviewCnt; // 리뷰 수
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder.Default // 빌더 사용시 기본값
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

}
