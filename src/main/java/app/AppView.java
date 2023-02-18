package app;

import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class AppView {
	protected JPanel rootPanel = new JPanel();
	private ImageIcon icon;
	
	public final JPanel getPanel() {
		return rootPanel;
	}
	
	//--------------선택적 Override Methods -----------------
	public String getTitle() {
		return getClass().getSimpleName();
	}
	
	public void setImageIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	public ImageIcon getImageIcon() {
		return icon;
	}
	
	public void update(LocalDateTime time) {
		//실행 중인 앱은 update() 1초마다 호출됨
		//시간흐름 관련 기능을 추가 하고싶으면 오버라이드해서 사용
	}
	
	/**
	 * SubApp 이 닫힐때 해야할 작업이 있다면 작성하세요 (예: 정보 저장)
	 * false 를 리턴할 경우 AppContainer 는 해당 앱을 닫지 않습니다. 
	 */
	public boolean close() {
		return true;
	}
	//-------------------------------------------------------
}
