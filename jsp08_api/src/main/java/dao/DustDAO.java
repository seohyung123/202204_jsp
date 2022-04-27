package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Dust;

public class DustDAO {

		public int insert(List<Map<String, Object>> dlist) {
			int cnt =0;
			try(SqlSession session = MBConn.getSession()){
				for(Map<String, Object> map:dlist) {
					Dust dust = session.selectOne("DustMapper.selectOne", map.get("sn"));
					if(dust!=null) {
						cnt +=session.update("DustMapper.update",map);
					}else {
						cnt +=session.insert("DustMapper.insert",map);
					}
				}
				session.commit();
				return cnt;
			}
		}
		
		public List<Dust> selectList(Map<String, Object> map){
			try(SqlSession session = MBConn.getSession()){
				return session.selectList("DustMapper.selectList", map);
			}
		}
		
		public Dust selectOne(String sn) {
			try(SqlSession session = MBConn.getSession()){
				return session.selectOne("DustMapper.selectOne", sn);
			}
		}
}
