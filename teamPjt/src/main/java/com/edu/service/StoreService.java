package com.edu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.vo.FundingQnaVO;
import com.edu.vo.MemberVO;
import com.edu.vo.StoreExpressVO;
import com.edu.vo.StoreOptionVO;
import com.edu.vo.StoreOrderOptionVO;
import com.edu.vo.StoreOrderPayVO;
import com.edu.vo.StoreOrderVO;
import com.edu.vo.StoreQnaVO;
import com.edu.vo.StoreReviewLikeVO;
import com.edu.vo.StoreReviewVO;
import com.edu.vo.StoreVO;
import com.edu.vo.ZzimVO;

public interface StoreService {

	// 스토어 메인
	//스토어 메인 리스트
	List<StoreVO> listMain(StoreVO vo) throws Exception;
	//스토어 메인 게시글 수
	public int listMainCount(StoreVO vo) throws Exception;
	// 스토어 뷰
	public StoreVO read(int store_idx, int store_funding) throws Exception;
	// 스토어 리뷰 리스트
	List<StoreReviewVO> storeReviewList(StoreReviewVO vo) throws Exception;
	// 스토어 리뷰 카운트
	public int countStoreReviewList(StoreReviewVO vo) throws Exception;
	// 스토어 리뷰 사진만 가져오기
	List<StoreReviewVO> storeReviewPhoto(StoreReviewVO vo) throws Exception;
	// 리뷰 수정에 리뷰 가져가기
	public StoreReviewVO getReview(int store_idx, int member_idx, int store_order_idx);
	
	// 스토어 별점 평균
	public Double starAvg(StoreReviewVO vo) throws Exception;
	// 스토어 별점 별 카운트
	public int star5(StoreReviewVO vo) throws Exception;
	public int star4(StoreReviewVO vo) throws Exception;
	public int star3(StoreReviewVO vo) throws Exception;
	public int star2(StoreReviewVO vo) throws Exception;
	public int star1(StoreReviewVO vo) throws Exception;
	// 스토어 별점 별 카운트 끝
	// 스토어 리뷰 작성
	public int storeReviewWrite(StoreReviewVO vo) throws Exception;
	// 스토어 리뷰 수정
	public int storeReviewModify(StoreReviewVO vo) throws Exception;
	// 스토어 리뷰 삭제
	public void storeReviewDelete(StoreReviewVO vo);
	// 스토어 리뷰 관리자 삭제
	public void storeReviewLikeAdminDelete(StoreReviewLikeVO vo);
	public void storeReviewAdminDelete(StoreReviewVO vo);
	//찜 select
	public ZzimVO selectZzimStore(Map<String, Integer> zzimMap);
	//찜 delete
	public int deleteZzimStore(Map<String, Object> paramMap);
	//리뷰 추천 하기
	public int doReviewLike(Map<String, Object> paramMap);
	//리뷰 추천 수 증가
	public int updateReviewLike(Map<String, Object> paramMap);
	//리뷰 추천 개수 가져오기
	public int getReviewLikeNum(Map<String, Object> paramMap);
	//리뷰 추천 select
	List<StoreReviewVO> selectThumbsUp(Map<String, Object> paramMap);
	//리뷰 추천 취소
	public int cancelLike(Map<String, Object> paramMap);
	//리뷰 추천 수 감소
	public int updateReviewLike2(Map<String, Object> paramMap);
	
	//스토어 qna 댓글 리스트
	List<StoreQnaVO> getQnaList(Map<String, Object> paramMap);
	//스토어 qna 답글 리스트
	List<StoreQnaVO> getQnaAnswer(Map<String, Object> paramMap);
	//스토어 QNA 댓글 작성
	public int qnaInsert(Map<String, Object> paramMap);
	//스토어 qna 답변 작성 완료
	public int qnaAnswerDone(StoreQnaVO vo) throws Exception;
	//스토어 qna 답변 수정
	public void qnaAnswerModify(StoreQnaVO vo) throws Exception;
	//스토어 qna 삭제
	public void deleteStoreQna(StoreQnaVO vo) throws Exception;
	//스토어 qna 수정
	public void modifyStoreQna(StoreQnaVO vo) throws Exception;
	//스토어 qna 개수
	public int countStoreQna(StoreQnaVO vo) throws Exception;
	
	
	// 스토어 옵션 리스트
	List<StoreOptionVO> storeOptionList(StoreOptionVO vo);
	//멤버조회
	MemberVO selectOne(MemberVO vo);
	
	//스토어 제품 등록 -> 상태 0(단독 등록)
	int store_reg(StoreVO vo);
	
	//스토어 제품 등록 -> 상태 1(펀딩 성공 후 스토어 등록)
	int store_reg2(StoreVO vo);
	
	//스토어 옵션 등록
	int storeOptionReg(List<StoreOptionVO> vo);
	
	//스토어 상품 찾기 이미 올려진 펀딩 제품이라면 0 리턴
	int store_Check(int funding_idx);
	
	// 결제
	// 주문 번호
	int insertOrder(StoreOrderVO ordervo);
	// 주문 옵션 정보 등록
	int insertOrderOption(StoreOrderOptionVO orderoptionvo);
	// 배송 정보 등록
	int insertExpress(StoreExpressVO expressvo);
	// 결제 정보
	int insertPay(StoreOrderPayVO payvo);
	// 주문 완료 - 주문 정보
	StoreVO store_info(int store_idx);
	
	//스토어 옵션 수량 감소 메소드
	int update_option(StoreOrderOptionVO orderoptionvo);
	
	//스토어 옵션 수량 복구 메소드 (주문 취소 시)
	int update_option_cancel(StoreOrderOptionVO orderoptionvo);
	
	//스토어 옵션 수량 증가 메소드
	int addStock(StoreOptionVO optionvo);
	
	StoreVO storeSelectOne(int store_idx);
	
	//스토어 파일 업데이트
	int store_updateFile(HashMap<String, Object> map);
}