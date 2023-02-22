package app.dash;

import java.util.List;

import app.AppService;
import app.AppView;
import app.SubApp;
import entity.BoardVO;

public class DashBoard extends SubApp {
	private BoardDao dao = new BoardDao();
	private BoardList boardList = new BoardList(this);
	private BoardInsert boardInsert = new BoardInsert(this);
	private BoardUpdate boardUpdate = new BoardUpdate(this);
	
	public void openUpdate(BoardVO vo) {
		boardUpdate.setData(vo);
		AppService.getInstance().openView(boardUpdate);
	}
	
	public void openInsert() {
		boardInsert.initRootPanel();
		AppService.getInstance().openView(boardInsert);
	}
	
	public void openList(List<BoardVO> voList) {
		AppService.getInstance().closeViews(boardInsert, boardUpdate);
		boardList.createTable(voList);
		AppService.getInstance().openView(boardList);
	}

	public void openList() {
		openList(null);
	}
	
	public BoardDao dao() {
		return dao;
	}
	
	@Override
	public AppView requestView() {
		return boardList;
	}
}