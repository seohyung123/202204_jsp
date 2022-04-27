package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MemberDAO;
import dto.Member;

public class MemberService {
	private MemberDAO memberDAO = new MemberDAO();
	
	public int insert(Member member) {
		//비밀번호 암호화
		String salt = saltmake(); //솔트를 구하기
		String secretpw = sha256(member.getPasswd(), salt);
		member.setPasswd(secretpw);
		member.setSalt(salt);
		return memberDAO.insert(member);
	}
	
	//sha256암호화방식으로 평문을 암호문으로 변경 반환
	String sha256(String passwd,String salt) {
		//StringBuffer : String대신 사용, 속도(메모리) 효율적
		StringBuffer sb = new StringBuffer(); //암호저장 변수
		try {
			//SHA-256 : 단방향 암호기법, 복호화 불가능, 256bit(16진수 64byte필요)
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(passwd.getBytes()); //문자열을 바이트배열로 변경해서 전달
			md.update(salt.getBytes()); //솔트를 추가
			
			byte[] data =  md.digest(); //암호화된 바이트 배열(32byte)
			System.out.println("암호화된 바이트 배열:" + Arrays.toString(data));
			System.out.println("배열길이:" + data.length);
			//10진수를 16진수로 변환해서 sb변수에 추가
			for(byte b:data) {
				sb.append(String.format("%02x", b));
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	//salt를 램덤하게 만들기
	String saltmake() {
		String salt = null;
		
		try {
			//난수생성 알고리즘
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			sr.nextBytes(bytes); //빈배열을 넣어주면 램덤한 값을 bytes에 만든다
			System.out.println(Arrays.toString(bytes));
			//정수byte를 String으로 변경
			//Base64인코딩 : 아스키중 제어문자,일부 특수문자를 제외한 64개의 안전한 문자만 사용
			//16byte -> 24byte
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return salt;
	}

	public Map<String, Object> loginCheck(String userid, String passwd) {
		// 로그인체크
		//리턴값: 한개만 리턴(map생성)
		//code : 0(성공), 1(회원미존재), 2(비밀번호불일치)
		//msg : 성공, 회원미존재, 비밀번호불일치
		Map<String, Object> rmap  =  new HashMap<>();
		int code;
		String msg;
		//1)dao호출 : userid를 기준으로 한건조회(selectOne)
		Member member = memberDAO.selectOne(userid);
		System.out.println("passwd :" + passwd);
		System.out.println(member);
		//2)만약에 리턴값이 null이면 회원이 존재하지 않는다
		if (member == null) {
			code = 1; 
			msg = "회원 미존재";
		}else {
			//평문을 암호문으로 변경해서 비교
			String secretpw  = sha256(passwd, member.getSalt());
			
			if (!secretpw.equals(member.getPasswd())) {
				//3)passwd가 일치하지 않는다면
				code = 2; 
				msg = "비밀번호 불일치";
			}else {
				//  패스워드가 일치하면 로그인 성공
				code = 0;
				msg = "로그인 성공";
			}

		}
		rmap.put("code", code);
		rmap.put("msg", msg);
		
		return rmap;
	}

	public Member selectOne(String userid) {
		//한건조회
		return memberDAO.selectOne(userid);
	}

	public List<Member> selectList(Map<String, String> findmap) {
		//리스트
		return memberDAO.selectList(findmap);
	}

	public Map<String, Object> update(Member member) {
		// 수정
		//code : 0 정상, 1 : 비밀번호 불일치
		
		Map<String, Object> rmap  =  new HashMap<>();
		
		//입력 비밀번호암호화해서 db비밀번호와 일치여부 판단
		//기존비밀번호 읽어오기
		Member dbmember = memberDAO.selectOne(member.getUserid());
		//평문과 salt를 이용해서 암호문 리턴
		String secretpw = sha256(member.getPasswd(), dbmember.getSalt());
		if (!secretpw.equals(dbmember.getPasswd())) {
			rmap.put("code", 1);
			rmap.put("msg", "비밀번호가 일치하지 않습니다.");
			return rmap;
		}
		member.setSalt(dbmember.getSalt());//솔트 
		member.setPasswd(secretpw); //패스워드
		//변경비밀번호가 있다면 비밀번호 세팅
		if (!member.getNewpasswd().equals("")) {
			//비밀번호 암호화
			//기존솔트 그대로 사용
			secretpw = sha256(member.getNewpasswd(), dbmember.getSalt());
			member.setPasswd(secretpw);
		}
		
		memberDAO.update(member);
		
		rmap.put("code", 0);
		rmap.put("msg", "수정 완료");
		
		return rmap;
	}

	public int delete(String userid) {
		return memberDAO.delete(userid);
	}
	
	
	
	
	
	
	
	
	
	

}
