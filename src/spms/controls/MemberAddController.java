package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;
@Component("/member/list.do")
public class MemberAddController implements Controller, DataBinding {

	MySqlMemberDao memberDao;
	public MemberAddController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member)model.get("member"); 
		if(member.getEmail()== null) {
			return "/member/memberAdd.jsp";
		} else {
			//MemberDao memberDao = (MemberDao) model.get("memberDao");
			memberDao.addMember(member);
			
			return "redirect:list.do";
		}
	}
	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
				"members", spms.vo.Member.class
		};
	}

}
