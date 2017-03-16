package spms.listeners;

// 페이지 컨트롤러 객체 준비
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import spms.context.ApplicationContext;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.dao.MySqlMemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  static ApplicationContext applicationContext;
  public static ApplicationContext getApplicationContext() {
	  return applicationContext;
  }
	@Override
  public void contextInitialized(ServletContextEvent event) {
    try {
      ServletContext sc = event.getServletContext();

      String propertiesPath = sc.getRealPath(
    		  sc.getInitParameter("contextConfigLocation"));
      applicationContext = new ApplicationContext(propertiesPath);
      
/*      
      InitialContext initialContext = new InitialContext();
      DataSource ds = (DataSource)initialContext.lookup(
          "java:comp/env/jdbc/studydb");
      
      MySqlMemberDao memberDao = new MySqlMemberDao();
      memberDao.setDataSource(ds);
      
      sc.setAttribute("/auth/login.do", 
          new LogInController().setMemberDao(memberDao));
      sc.setAttribute("/auth/logout.do", new LogOutController());
      sc.setAttribute("/member/list.do", 
          new MemberListController().setMemberDao(memberDao));
      sc.setAttribute("/member/add.do", 
          new MemberAddController().setMemberDao(memberDao));
      sc.setAttribute("/member/update.do", 
          new MemberUpdateController().setMemberDao(memberDao));
*/      
    } catch(Throwable e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {}
}
