package app;

import java.time.LocalDateTime;

import javax.swing.JPanel;

public abstract class SubApp implements Comparable<SubApp>{
	protected JPanel rootPanel = new JPanel();
	private int index;
	
	public final JPanel getPanel() {
		return rootPanel;
	}
	//--------------선택적 Override Methods -----------------
	public String getTitle() {
		return this.getClass().getSimpleName(); //기본값은 클래스 이름입니다. 쉽게 알아볼수 있는 이름으로 Override 하기
	}
	
	public void update(LocalDateTime time) {
		//1초마다 호출됨
	}
	
	/**
	 * SubApp 이 닫힐때 해야할 작업이 있다면 작성하세요 (예: 정보 저장)
	 * false 를 리턴할 경우 AppContainer 는 해당 엡을 닫지 않습니다. 
	 */
	public boolean close() {
		return true;
	}
	
	/**
	 * 100x100 권장<br>
	 * 폴더는 적지말것<br>
	 * @return "imageFile.png"
	 */
	public String getIconName() {
		return "heart.png";
	} 
	
	@Override
	public int compareTo(SubApp another) {
		int c = another.index - this.index;
		if(c == 0) return this.getTitle().compareTo(another.getTitle());
		else return c;
	}
}
