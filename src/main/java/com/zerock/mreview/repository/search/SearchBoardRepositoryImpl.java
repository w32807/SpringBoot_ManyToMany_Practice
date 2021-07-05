package com.zerock.mreview.repository.search;

import com.zerock.mreview.entity.Movie;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

    public SearchBoardRepositoryImpl(){
        super(Movie.class);
    }
}
