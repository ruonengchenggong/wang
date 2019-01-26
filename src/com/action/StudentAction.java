package com.action;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.beans.Book;
import com.beans.Student;
import com.beans.Teacher;
import com.beans.Ts;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.util.HibUtil;

public class StudentAction extends ActionSupport {
	private String userName;
	private String password;
	private int select;
	private int tid;
    private int sid;
    private int status;
    private int bid;
	private String bname;
	private int number;

    
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String login() {
		List<Ts> tsList = null;
		if (select == 1) {
			Session s = HibUtil.getSession();
			Transaction tx = s.beginTransaction();
			Query q1 = s.createQuery("select s from Student s where s.userName=? and s.password=?");
			q1.setParameter(0, userName);
			q1.setParameter(1, password);
			Student student = (Student) q1.uniqueResult();
			Query q2 = s.createQuery("select ts from Ts ts where ts.sid=?");
			q2.setParameter(0, student.getSid());
			tsList = q2.list();

			tx.commit();
			if (student != null) {
				ActionContext context = ActionContext.getContext();
				context.getSession().put("user", student);
				context.getSession().put("ts", tsList);
				return "successStu";
			}

		} else if (select == 2) {
			Session s = HibUtil.getSession();
			Transaction tx = s.beginTransaction();
			Query q1 = s.createQuery("select t from Teacher t where t.tuserName=? and t.password=?");
			q1.setParameter(0, userName);
			q1.setParameter(1, password);
			Teacher teacher = (Teacher) q1.uniqueResult();
			Query q2 = s.createQuery("select ts from Ts ts where ts.tid=?");
			q2.setParameter(0, teacher.getTid());
			tsList = q2.list();
			tx.commit();
			if (teacher != null) {
				ActionContext context = ActionContext.getContext();
				context.getSession().put("user", teacher);
				context.getSession().put("ts", tsList);
				return "successTea";
			}

		}

		return "error";
	}
	public String queryBook(){
		List<Book> bList=HibUtil.selectBook();
		ActionContext.getContext().put("bList", bList);

		return "book";
		
	}
	public String update(){
		Book book=HibUtil.queryBid(bid);
		ActionContext.getContext().put("book", book);
		ActionContext.getContext().getSession().put("book", book);
		return "bookUpdate";
		
	}
	public String update1(){
		
		Book b=(Book)ActionContext.getContext().getSession().get("book");

		b.setBname(bname);
		b.setNumber(number);
		
		if(HibUtil.update(b)){
			
			ActionContext.getContext().put("msg", "修改成功");
			return this.update();
		}
		return this.update();
		
	}

	/*public String queryTea() {
		Session s = null;
		List<Teacher> teaList = null;

		try {
			s = HibUtil.getSession();
			String hql = "from Teacher";
			Query q = s.createQuery(hql);
			teaList = q.list();
			ActionContext context = ActionContext.getContext();
			
			List<Ts> tsList = (List<Ts>) context.getSession().get("ts");
			if (tsList != null) {
				for (int i = 0; i < tsList.size(); i++) {
					for (int j = 0; j < teaList.size(); j++) {
						if (tsList.get(i).getTid() == teaList.get(j).getTid()) {
							teaList.get(j).setTs(tsList.get(i));
						}
					}
				}
			}
			ActionContext.getContext().put("teaList", teaList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibUtil.close(s);
		}
		

		return "teaAll";
	}

	public String queryStu() {
		Session s=null;
		List<Student> stuList=null;
		try{
			s=HibUtil.getSession();
			String hql="from Student";
			Query q=s.createQuery(hql);
			stuList=q.list();				
			ActionContext.getContext().put("stuList", stuList);
			ActionContext context = ActionContext.getContext();
			
			List<Ts> tsList = (List<Ts>) context.getSession().get("ts");
			if (tsList != null) {
				for (int i = 0; i < tsList.size(); i++) {
					for (int j = 0; j < stuList.size(); j++) {
						if (tsList.get(i).getTid() == stuList.get(j).getSid()) {
							stuList.get(j).setTs(tsList.get(i));
						}
					}
				}
			}
			ActionContext.getContext().put("stuList", stuList);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibUtil.close(s);
		}
		
		return "stuAll";

	}
   
	public String agree() {
		ActionContext context = ActionContext.getContext();
		Teacher tea = (Teacher) context.getSession().get("user");
		Session s=null;
		Transaction tx=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
			String hql="update Ts t set t.status=? where t.sid=? and t.tid=?";
			
			Query q=s.createQuery(hql);			
			q.setParameter(0, status);
			q.setParameter(1, sid);
			q.setParameter(2, tea.getTid());
			int ret=q.executeUpdate();
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibUtil.close(s);
		}
		return "agree";
	}
	
	public String add() {
		Ts ts = new Ts();
		ActionContext context = ActionContext.getContext();
		Student stu = (Student) context.getSession().get("user");
		ts.setSid(stu.getSid());
		ts.setTid(tid);
		ts.setStatus(0);
		Session s=null;
		Transaction tx=null;
		Serializable i=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
			i=s.save(ts);  //返回值为当前数据库id
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibUtil.close(s);
		}
		return "apply";
	}*/

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

}
