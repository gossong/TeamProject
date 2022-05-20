package com.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.vo.FundingMainVO;
import com.edu.vo.StoreOptionVO;
import com.edu.vo.StoreReviewVO;
import com.edu.vo.StoreVO;
import com.edu.vo.ZzimVO;

@Repository
public class storeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 스토어 메인 리스트
	public List<StoreVO> listMain(StoreVO vo) throws Exception{
		return sqlSession.selectList("StoreMapper.listMain", vo);
	}
	// 스토어 총 갯수
	public int listMainCount(StoreVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.listMainCount", vo);
	}
	//스토어 조회수
	public void storeViews(int store_idx) throws Exception{
		sqlSession.update("StoreMapper.storeViews", store_idx);
	}
	// 스토어 뷰
	public StoreVO read(HashMap<String, Integer> hm) throws Exception{
		return sqlSession.selectOne("StoreMapper.read", hm);
	}
	// 스토어 리뷰 리스트
	public List<StoreReviewVO> storeReviewList(StoreReviewVO vo) throws Exception{
		return sqlSession.selectList("StoreMapper.storeReviewList", vo);
	}
	// 스토어 리뷰 카운트
	public int countStoreReviewList(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.countStoreReviewList", vo);
	}
	// 스토어 리뷰 사진만 가져오기
	public List<StoreReviewVO> storeReviewPhoto(StoreReviewVO vo) throws Exception{
		return sqlSession.selectList("StoreMapper.storeReviewPhoto", vo);
	}
	// 스토어 리뷰 평균
	public Double starAvg(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.starAvg", vo);
	}
	//<!-- 스토어 별점 별 카운트 -->
	public int star5(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.star5", vo);
	}
	public int star4(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.star4", vo);
	}
	public int star3(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.star3", vo);
	}
	public int star2(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.star2", vo);
	}
	public int star1(StoreReviewVO vo) throws Exception{
		return sqlSession.selectOne("StoreMapper.star1", vo);
	}
	//<!-- 스토어 별점 별 카운트 끝 -->
	//스토어 리뷰 작성
	public int storeReviewWrite(StoreReviewVO vo) throws Exception{
		return sqlSession.insert("StoreMapper.storeReviewWrite", vo);
	}
	//zzim select
	public List<ZzimVO> selectZzimStore(Map<String, Object> paramMap) {
		return sqlSession.selectList("StoreMapper.selectZzimStore", paramMap);
	}
	//zzim 삭제
	public int deleteZzimStore(Map<String, Object> paramMap) {
		return sqlSession.delete("StoreMapper.deleteZzimStore", paramMap);
	}
	//리뷰 추천 insert
	public int doReviewLike(Map<String, Object> paramMap) {
		return sqlSession.insert("StoreMapper.doReviewLike", paramMap);
	}
	//리뷰 추천 증가 update
	public int updateReviewLike(Map<String, Object> paramMap) {
		return sqlSession.update("StoreMapper.updateReviewLike", paramMap);
	}
	//리뷰 추천 개수 가져오기
	public int getReviewLikeNum(Map<String, Object> paramMap) {
		return sqlSession.selectOne("StoreMapper.getReviewLikeNum", paramMap);
	}
	//리뷰 추천 select
	public List<StoreReviewVO> selectThumbsUp(Map<String, Object> paramMap) {
		return sqlSession.selectList("StoreMapper.selectThumbsUp", paramMap);
	}
	//리뷰 추천 취소 delete
	public int cancelLike(Map<String, Object> paramMap) {
		return sqlSession.delete("StoreMapper.cancelLike", paramMap);
	}
	//리뷰 추천 수 감소
	public int updateReviewLike2(Map<String, Object> paramMap) {
		return sqlSession.update("StoreMapper.updateReviewLike2", paramMap);
	}
	
	
	// 스토어 옵션 리스트
	public List<StoreOptionVO> storeOptionList(StoreOptionVO vo) {
		return sqlSession.selectList("StoreMapper.storeOptionList", vo);
	}

}
