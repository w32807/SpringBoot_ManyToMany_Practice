package com.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 화면에 필요한 모든 내용을 가지고 있어야 하기 때문에 호원의 아이디와 닉네임, 이메일도 같이 처리할 수 있도록 설계
// 꼭 엔티티와 필드가 완전히 동일할 필요는 없음
public class ReviewDTO {
    private Long reviewnum;
    private Long mno;
    private Long mid;
    private String nickname;
    private String email;
    private int grade;
    private String text;
    private LocalDateTime regDate, modDate;
}
