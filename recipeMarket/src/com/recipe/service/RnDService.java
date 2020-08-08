package com.recipe.service;

import java.util.List;

import com.recipe.dao.RnDDAO;
import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.exception.RemoveException;
import com.recipe.model.PageBean;
import com.recipe.vo.Board;
import com.recipe.vo.RnD;

public class RnDService {
	private static RnDService instance;
	private RnDDAO rdDAO;
	
	private RnDService() {
		rdDAO = new RnDDAO();
	}
	
	public static RnDService getInstance() {
		if(instance == null) instance = new RnDService();
		return instance;
	}
	
	/**
	 * 로그인 절차를 수행하는 메소드
	 * 아이디와 패스워드를 가진 R&D가 존재하는지 여부를 확인하고, 존재하면 세션에 아이디를 저장한뒤 클라이언트에게 로그인 성공 메시지를 전송한다
	 * @param rdId 로그인 절차를 수행할 아이디
	 * @param rdPwd 로그인 절차를 수행할 패스워드
	 * @throws FindException
	 * @author 최종국
	 */
	public void login(String rdId, String rdPwd) throws FindException{
		RnD r;
		try {
			r = rdDAO.selectById(rdId);
		} catch (FindException e) {
			throw new FindException("로그인 실패");
		}
		
		if(!r.getRdPwd().equals(rdPwd)) throw new FindException("로그인 실패");
		
		//RDShare.addSession(rdId);
	}
	/**
	 * R&D 계정 전체 조회 절차를 수행하기 위한 메소드
	 * @return R&D 계정 리스트
	 * @throws FindException
	 * @author 최종국
	 */
	public List<RnD> findAll() throws FindException {
		return rdDAO.selectAll();
	}
	
	/**
	 * R&D 계정 추가 절차를 수행하기 위한 메소드
	 * @param r 추가할 R&D 계정의 정보를 가진 RD 객체
	 * @throws AddException
	 * @throws DuplicatedException 아이디가 중복되면 발생
	 * @author 최종국
	 */
	public void add(RnD r) throws AddException, DuplicatedException {
		rdDAO.insert(r);
	}
	
	/**
	 * R&D 계정 수정 절차를 수행하기 위한 메소드
	 * @param r 수정할 R&D 계정의 정보를 가진 RD 객체
	 * @throws ModifyException 수정하려는 R&D 계정이 존재하지 않으면 발생
	 * @author 최종국
	 */
	public void modify(RnD r) throws ModifyException {
		rdDAO.update(r);
	}
	
	/**
	 * R&D 계정 삭제(비활성화) 절차를 수행하기 위한 메소드
	 * @param rdId 삭제할 아이디
	 * @throws RemoveException 삭제하려는 R&D 계정이 존재하지 않으면 발생
	 * @author 최종국
	 */
	public void remove(String rdId) throws RemoveException {
		rdDAO.disableRD(rdId);
	}
	
	public RnD findById(String rdId) throws FindException {
		return rdDAO.selectById(rdId);
	}
	
	/***********************************************************************************/

	/**
	 * Create page bean object of requested page
	 * @param page
	 * @return PageBean
	 * @throws FindException
	 * @author yonghwan
	 */
	public PageBean findAll(int page) throws FindException {
		if (page < 1)
			throw new FindException("The page does not exist");
		
		int rowCnt = rdDAO.selectCount();
		PageBean pb = new PageBean(page, rowCnt);
		
		List<RnD> list = rdDAO.selectAll(pb.getStartRow(), pb.getEndRow());
		pb.setList(list);
		
		return pb;
	}
}
