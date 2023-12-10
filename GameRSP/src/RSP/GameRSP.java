package RSP; //RSP 패키지 선언

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameRSP extends JFrame{ //JFrame을 상속하는 GameRSP 클래스
	
	//가위, 바위, 보에 해당하는 이미지 아이콘 배열
	ImageIcon[] imgIcons = {
			new ImageIcon("/Assets/kawi.jpg"),
			new ImageIcon("/Assets/bawi.jpg"),
			new ImageIcon("/Assets/bo.jpg")
			
	};
	
	SelectPanel select = new SelectPanel(); //SelectPanel 인스턴스 생성
	ResultDisplay result = new ResultDisplay(); //ResultDisplay 인스턴스 생성
	
	public GameRSP() { //GameRSP 생성자
		super("가위 바위 보 게임"); //창 제목 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE); //창 닫기 설정
		
		add(result,"Center"); //결과 표시 패널을 중앙에 추가
		add(select, "South"); //선택 패널을 아래쪽에 추가
		
		setSize(500,500); //창 크기 설정
		setVisible(true); //창 보이기 설정
	}
	
	
	class SelectPanel extends JPanel{ //사용자 선택 패널
		JButton[] btn = new JButton[3]; //버튼 배열 선언
		
		public SelectPanel() { //SelectPanel 생성자
			setBackground(Color.LIGHT_GRAY); //배경색 설정
			
			for(int i=0; i<imgIcons.length; i++) { //이미지 아이콘 개수만큼 반복
				btn[i] = new JButton(imgIcons[i]); //버튼에 이미지 아이콘 설정
				this.add(btn[i]); //패널에 버튼 추가
				
				btn[i].addActionListener(new EventHandler()); //버튼에 이벤트 핸들러 추가
			}
		}
	}
	
	class ResultDisplay extends JPanel{ //결과 표시 패널
		JLabel userJLabel = new JLabel("you"); //사용자 라벨
		JLabel comJLabel = new JLabel("computer");//컴퓨터 라벨
		JLabel resultJLabel = new JLabel("winner");//결과 라벨
		
		public ResultDisplay() { //ResultDisplay 생성자
			setBackground(Color.CYAN);//배경색 설정
			add(userJLabel);//사용자 라벨 추가
			add(resultJLabel);//결과 라벨 추가
			add(comJLabel);//컴퓨터 라벨 추가
		}
		
		public void output(Icon img,Icon comImage, String res ) { //결과 출력 메소드
			userJLabel.setIcon(img); //사용자 선택 이미지 설정
			userJLabel.setHorizontalTextPosition(JLabel.LEFT); //텍스트 위치 설정
			comJLabel.setIcon(comImage); //컴퓨터 선택 이미지 설정
			resultJLabel.setText(res); //결과 텍스트 설정
			result.setFont(new Font(Font.DIALOG, Font.BOLD, 20)); //결과 라벨 폰트 설정
			
		}
	}
	
	class EventHandler implements ActionListener{ //이벤트 핸들러 클래스

		@Override
		public void actionPerformed(ActionEvent e) { //버튼 클릭 시 실행되는 메소드
			//사용자가 클릭한 버튼을 가져와서 btnSrc에 저장
			JButton btnSrc = (JButton)e.getSource();
			
			//컴퓨터에 선택을 나타내는 랜덤한 숫자 생성
			int selCom = (int)(Math.random()*3); // 0:가위  1:바위, 2:보
			
			//결과를 저장할 변수
			String res = "";

			//사용자와 컴퓨터의 선택에 따라 승패를 판단하는 조건문
			if(btnSrc.getIcon() == imgIcons[0] && selCom == 2 ||
			   btnSrc.getIcon() == imgIcons[1] && selCom == 0 ||
			   btnSrc.getIcon() == imgIcons[2] && selCom == 1 )
				res = "당신이 이겼습니다!"; //이겼을 때
			else if(btnSrc.getIcon() == imgIcons[0] && selCom == 0 ||
				    btnSrc.getIcon() == imgIcons[1] && selCom == 1 ||
				    btnSrc.getIcon() == imgIcons[2] && selCom == 2 )
				res = "비겼습니다!"; //비겼을 때
			else 
				res = "당신이 졌습니다!"; //졌을 때
			
			//결과를 ResultDisplay 클래스의 output 메소드를 통해 화면에 표시
			result.output(btnSrc.getIcon(), imgIcons[selCom], res);
			
			
		}
		
	}

	public static void main(String[] args) { //메인 메소드
		new GameRSP(); //GameRSP 인스턴스 생성하여 게임 시작

	}

}
