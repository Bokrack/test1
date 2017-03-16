package spms.dao;

// MemberDao 인터페이스 정의 
import java.util.List;

import spms.vo.Member;

public interface MemberDao {
  List<Member> selectlList() throws Exception;
  void addMember(Member member) throws Exception;
  Member selectOne(int no) throws Exception;
  int update(Member member) throws Exception;
  Member exist(String email, String password) throws Exception;
}
