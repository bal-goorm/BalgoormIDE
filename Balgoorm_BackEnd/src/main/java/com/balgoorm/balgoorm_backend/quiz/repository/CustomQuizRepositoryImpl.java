package com.balgoorm.balgoorm_backend.quiz.repository;

import com.balgoorm.balgoorm_backend.quiz.model.entity.QQuiz;
import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomQuizRepositoryImpl implements CustomQuizRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Quiz> getQuizListSorted(Pageable pageable, List<Integer> levels, QuizSortType sortType) {

        QQuiz quiz = QQuiz.quiz;
        BooleanExpression predicate = createPredicate(levels);
        OrderSpecifier<?> sortOrder = getSortOrder(sortType, quiz);

        JPAQuery<Quiz> query = queryFactory.selectFrom(quiz)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        if(sortOrder != null) // null 이라면 orderBy 구문 추가 X;
            query.orderBy(sortOrder);

        List<Quiz> quizList = query.fetch();

        return quizList;
    }

    /**
     * 정렬 방식에 따른 Order By 구문을 생성하기 위한 메소드
     * @param sortType
     * @param quiz
     * @return
     */
    private OrderSpecifier<?> getSortOrder(QuizSortType sortType, QQuiz quiz) {
        switch(sortType){
            case DEFAULT :
                return null;
            case DATE_ASC:
                return quiz.quizRegDate.asc();
            case DATE_DESC:
                return quiz.quizRegDate.desc();
            case REC_ASC:
                return quiz.quizRecCnt.asc();
            case REC_DESC:
                return quiz.quizRecCnt.desc();
            case CORRECT_RATE_ASC:
                return quiz.correctCnt.multiply(100).divide(quiz.submitCnt).asc();
            case CORRECT_RATE_DESC:
                return quiz.correctCnt.multiply(100).divide(quiz.submitCnt).desc();
            default:
                throw new IllegalArgumentException("Unsupported sort type: " + sortType);
        }
    }


    /**
     * level 에 따른 where 조건문을 만들기 위한 메소드
     * @param levels
     * @return
     */
    private BooleanExpression createPredicate(List<Integer> levels) {
        QQuiz quiz = QQuiz.quiz;
        if(levels == null || levels.isEmpty()) {
            return null;
        }
        return quiz.quizLevel.in(levels);
    }
}
