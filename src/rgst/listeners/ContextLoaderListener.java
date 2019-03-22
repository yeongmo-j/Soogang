package rgst.listeners;

//서버에서 제공하는 DataSource 사용하기
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import rgst.dao.Now_registerDao;
import rgst.dao.Past_registerDao;
import rgst.dao.StudentDao;
import rgst.dao.SubjectDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
@Override
public void contextInitialized(ServletContextEvent event) {
 try {
   ServletContext sc = event.getServletContext();
   
   InitialContext initialContext = new InitialContext();
   DataSource ds = (DataSource)initialContext.lookup(
       "java:comp/env/jdbc/register");
   
   StudentDao studentDao = new StudentDao();
   studentDao.setDataSource(ds);
   
   SubjectDao subjectDao = new SubjectDao();
   subjectDao.setDataSource(ds);
   
   Now_registerDao nowregisterDao = new Now_registerDao();
   nowregisterDao.setDataSource(ds);
   
   Past_registerDao pastregisterDao = new Past_registerDao();
   pastregisterDao.setDataSource(ds);
   
   sc.setAttribute("studentDao", studentDao);
   sc.setAttribute("subjectDao", subjectDao);
   sc.setAttribute("nowregisterDao", nowregisterDao);
   sc.setAttribute("pastregisterDao", pastregisterDao);


 } catch(Throwable e) {
   e.printStackTrace();
 }
}

@Override
public void contextDestroyed(ServletContextEvent event) {}
}
