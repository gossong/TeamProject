package com.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.service.MypageService;
import com.edu.service.fundingMainService;
import com.edu.vo.FundingCommunityVO;
import com.edu.vo.FundingMainVO;
import com.edu.vo.FundingQnaVO;
import com.edu.vo.Funding_expressVO;
import com.edu.vo.Funding_optionVO;
import com.edu.vo.Funding_orderVO;
import com.edu.vo.Funding_order_optionVO;
import com.edu.vo.Funding_order_payVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageMaker;
import com.edu.vo.Pagination;
import com.edu.vo.ZzimVO;


// Funding에 관련된 모든 동작 수행
@Controller
@RequestMapping(value = "/funding")
public class FundingController {
	
	@Autowired
	private fundingMainService fms;

	@Autowired
	private MypageService mypageService ;
	
	// 펀딩 메인페이지 카테고리
	@RequestMapping(value = "/main.do")
	public String main() {
		return "funding/main";
	}
	
	@RequestMapping(value = "/main_cat.do")
	public String main_cat() {
		return "funding/main_cat";
	}
	
	@RequestMapping(value = "/main_other.do")
	public String main_other() {
		return "funding/main_other";
	}
	
	
	// 펀딩 메인페이지에 리스트 출력	
	@RequestMapping(value="/main.do", method=RequestMethod.GET)
	public String listDog(Model model, Pagination page) throws Exception {
		
		model.addAttribute("listDog",fms.listDog(page));
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPage(page);
		pageMaker.setTotalCount(fms.listDogCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "funding/main";
	}
	@RequestMapping(value="/main_cat.do", method=RequestMethod.GET)
	public String listCat(Model model, Pagination page) throws Exception {
		
		model.addAttribute("listCat",fms.listCat(page));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPage(page);
		pageMaker.setTotalCount(fms.listCatCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "funding/main_cat";
	}
	@RequestMapping(value="/main_other.do", method=RequestMethod.GET)
	public String listOther(Model model, Pagination page) throws Exception {
		
		model.addAttribute("listOther",fms.listOther(page));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPage(page);
		pageMaker.setTotalCount(fms.listOtherCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "funding/main_other";
	}
	
	//펀딩 뷰
	@RequestMapping(value = "/view.do", method = RequestMethod.GET)
	public String read(@RequestParam Map<String, Object> paramMap, FundingMainVO vo, Model model, HttpSession session, HttpServletRequest request) throws Exception{
		
		//funding_idx에 따른 뷰페이지 정보 가져오기
		model.addAttribute("read", fms.read(vo.getFunding_idx()));		
		
		//세션사용자정보 가져옴
		session = request.getSession();
		MemberVO login = (MemberVO)session.getAttribute("login");
		MemberVO member = mypageService.selectOne(login);
		model.addAttribute("member",member);	
		//펀딩 커뮤니티 댓글 리스트
		List<FundingCommunityVO> fundingCommunityCommentList =fms.readFundingCommunityComent(vo.getFunding_idx());
		model.addAttribute("fundingCommunityCommentList", fundingCommunityCommentList);
	
		//펀딩 qna 댓글 리스트
		model.addAttribute("qnaList", fms.getQnaList(paramMap));
		
		return "funding/view";
	}
	//오더 카운트
	@RequestMapping(value ="/read_funding_form", method= RequestMethod.POST)
	@ResponseBody
	public int orderCount(Funding_orderVO vo) throws Exception {
		return fms.orderCount(vo);
	}
	
	//펀딩 커뮤니티 댓글 작성 ajax로 불러옴
	@RequestMapping(value ="/serialize", method= RequestMethod.POST)
	@ResponseBody
	public int serialize(FundingCommunityVO vo) throws Exception {
		//return vo.getFunding_detail_community_content() + vo.getFunding_idx() + vo.getMember_idx() + vo.getFunding_detail_community_category();
		return fms.writeFundingCommunityComment(vo);
	}
	
	//펀딩 커뮤니티 댓글 수정 ajax로 불러옴
	@RequestMapping(value ="/commuModify", method= RequestMethod.POST)
	@ResponseBody
	public void commuModify(FundingCommunityVO vo) throws Exception {
		//return vo.getFunding_detail_community_content() + vo.getFunding_idx() + vo.getMember_idx() + vo.getFunding_detail_community_category();
		fms.modifyFundingCommunityComment(vo);
	}
	//펀딩 커뮤니티 댓글 삭제ajax로 불러옴
	@RequestMapping(value ="/commuDelete", method= RequestMethod.POST)
	@ResponseBody
	public void commuDelete(FundingCommunityVO vo) throws Exception {
		//return vo.getFunding_detail_community_content() + vo.getFunding_idx() + vo.getMember_idx() + vo.getFunding_detail_community_category();
		fms.deleteFundingCommunityComment(vo);
	}
	
	//펀딩 qna 작성 ajax로 불러옴
	@RequestMapping(value ="/qnaInsert", method= RequestMethod.POST)
	@ResponseBody
	public Object qnaInsert(@RequestParam Map<String, Object> paramMap){
		
		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();
		int result = fms.qnaInsert(paramMap);
		if(result>0) {
			retVal.put("code", "OK");
			retVal.put("funding_idx", paramMap.get("funding_idx"));
			retVal.put("member_idx", paramMap.get("member_idx"));
			retVal.put("message", "등록 성공!");
		}else {
			retVal.put("code", "FAIL");
			retVal.put("message", "등록 실패!");
		}
		return retVal;
	}
	
	//펀딩 qna 상태 업데이트
	@RequestMapping(value ="/qnaAnswerDone", method= RequestMethod.POST)
	@ResponseBody
	public int qnaAnswerDone(FundingQnaVO vo) throws Exception{
		return fms.qnaAnswerDone(vo);
	}
	// 펀딩 qna 삭제
	@RequestMapping(value ="/qnaDelete", method= RequestMethod.POST)
	@ResponseBody
	public void deleteFundingQna(FundingQnaVO vo) throws Exception {
		fms.deleteFundingQna(vo);
	}
	// 펀딩 qna 수정
	@RequestMapping(value ="/qnaModify", method= RequestMethod.POST)
	@ResponseBody
	public void modifyFundingQna(FundingQnaVO vo) throws Exception {
		fms.modifyFundingQna(vo);
	}
	
	// 찜 insert
	@RequestMapping(value ="/insertZzim", method= RequestMethod.POST)
	@ResponseBody
	public Object insertZzim(@RequestParam Map<String, Object> paramMap){
		
		Map<String, Object> retVal = new HashMap<String, Object>();
		int result = fms.insertZzim(paramMap);
		if(result>0) {
			retVal.put("code", "OK");
			retVal.put("message", "찜 성공!");
		}else {
			retVal.put("code", "FAIL");
			retVal.put("message", "찜 실패!");
		}
		return retVal;
	}
	
	// 찜 select
	@RequestMapping(value ="/selectZzim", method= RequestMethod.POST)
	@ResponseBody
	public Object selectZzim(@RequestParam Map<String, Object> paramMap){
		Map<String, Object> selectZzim = new HashMap<String, Object>();
		List<ZzimVO> result = fms.selectZzim(paramMap);		
		return result;		
	}
	
	// 찜 delete
	@RequestMapping(value ="/deleteZzim", method= RequestMethod.POST)
	@ResponseBody
	public Object deleteZzim(@RequestParam Map<String, Object> paramMap){
		Map<String, Object> deleteZzim = new HashMap<String, Object>();
		int result = fms.deleteZzim(paramMap);
		return result;
	}

	
	
	
	
	
	// 이동
	@RequestMapping(value = "/view.do")
	public String view() {
		return "funding/view";
	}
	
	// 옵션 페이지
	@RequestMapping(value = "/option.do", method = RequestMethod.GET)
	public String option(Model model, FundingMainVO mainvo, Funding_optionVO optionvo) throws Exception {
		
		model.addAttribute("read", fms.read(mainvo.getFunding_idx()));
		
		// 옵션 리스트 출력
		List<Funding_optionVO> optionlist = fms.list(optionvo);
		model.addAttribute("optionlist", optionlist);
		
		return "funding/option";
	}
	
	@RequestMapping(value = "/option.do", method = RequestMethod.POST)
	public String option(Model model, Funding_optionVO optionvo, HttpServletRequest request, @RequestParam("check") String check) throws Exception {
		// 옵션 리스트 출력
		List<Funding_optionVO> optionlist = fms.list(optionvo);
		model.addAttribute("optionlist", optionlist);
		
		//세션에 있는 사용자의 정보 가져옴
		HttpSession session = request.getSession();
		MemberVO login = (MemberVO)session.getAttribute("login");
		MemberVO member = fms.selectOne(login);
		model.addAttribute("member", member);
		
		return "funding/reserve";
	}
	
	// 결제 예약 페이지
	@RequestMapping(value = "/reserve.do", method = RequestMethod.POST)
	public void orderForm(Model model, Funding_orderVO ordervo, Funding_order_optionVO orderOptionvo, Funding_expressVO expressvo, Funding_order_payVO payvo, HttpServletRequest request, HttpServletResponse response, @RequestParam("inlineRadioOptions1") String radio) throws IOException {
		// 펀딩 주문 번호
		int result = fms.insertOrder(ordervo);
		
		// 펀딩 주문 옵션 저장
		String[] select_idx = request.getParameterValues("funding_order_option_select_idx");
		String[] select_count = request.getParameterValues("funding_order_option_select_count");
		for(int i=0; i<select_idx.length; i++) {
			String s_i = select_idx[i];
			String s_c = select_count[i];
			int si = Integer.parseInt(s_i);
			int sc = Integer.parseInt(s_c);
			orderOptionvo.setFunding_order_option_select_idx(si);
			orderOptionvo.setFunding_order_option_select_count(sc);
			fms.insertOrderOption(orderOptionvo);
		}
		
		// 펀딩 주문 배송지 저장
		String name = "";
		String phone = "";
		String postnum = "";
		String addr1 = "";
		String addr2 = "";
		if(radio.equals("option1")) {
			name = request.getParameter("funding_express_name1");
			phone = request.getParameter("funding_express_phone1");
			postnum = request.getParameter("funding_express_postnum1");
			addr1 = request.getParameter("funding_express_addr1_1");
			addr2 = request.getParameter("funding_express_addr2_1");
			
		}else {
			name = request.getParameter("funding_express_name2");
			phone = request.getParameter("funding_express_phone2");
			postnum = request.getParameter("funding_express_postnum2");
			addr1 = request.getParameter("funding_express_addr1_2");
			addr2 = request.getParameter("funding_express_addr2_2");
		}
		expressvo.setFunding_express_name(name);
		expressvo.setFunding_express_phone(phone);
		expressvo.setFunding_express_postnum(postnum);
		expressvo.setFunding_express_addr1(addr1);
		expressvo.setFunding_express_addr2(addr2);
		fms.insertExpress(expressvo);
		
		// 결제 정보 저장
		String[] card_number = request.getParameterValues("card_num");
		String card = "";
		for(String c : card_number) {
			card += c;
			card += " ";
		}
		card = card.substring(0, card.length() - 1);
		payvo.setFunding_order_pay_card_num(card);
		fms.insertPay(payvo);
		
		int funding_idx = Integer.parseInt(request.getParameter("funding_idx"));
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter pw = response.getWriter();
		if(result>0) {
			// 저장 완료
			pw.println("<script>alert('결제 예약이 완료되었습니다.');location.href='reserve_complete.do?funding_idx="+funding_idx+"';</script>");
		}else {
			// 저장 안됭
			pw.println("<script>alert('결제 예약이 실패하었습니다.');location.href='reserInteger.parseIntve.do';</script>");
		}
		pw.flush();
		
	}
	
	@RequestMapping(value = "/reserve_complete.do")
	public String reserveComplete(FundingMainVO mainvo, Model model) throws Exception {
		model.addAttribute("read", fms.read(mainvo.getFunding_idx()));		

		return "funding/reserve_complete";
	}
	
	
}
