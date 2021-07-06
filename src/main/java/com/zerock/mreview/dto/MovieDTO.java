package com.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long mno;
    private String title;

    @Builder.Default // 빌더 사용시 기본값
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
