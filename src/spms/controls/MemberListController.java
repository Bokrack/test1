package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.MySqlMemberDao;
@Component("/member/list.do")
public class MemberListController implements Controller {
	MySqlMemberDao memberDao;
	public MemberListController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		//MemberDao memberDao = (MemberDao) model.get("memberDao");
		model.put("members", memberDao.selectlList());
		return "/member/MemberList.jsp";
	}

}
