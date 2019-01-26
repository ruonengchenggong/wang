package com.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.beans.AdminInfo;
import com.beans.Book;
import com.opensymphony.xwork2.ActionContext;

public class HibUtil {
	
	private static SessionFactory sessionFactory;
	
	static{
		//������ȡ�����ļ��Ķ���
		Configuration cfx=new Configuration();
		//��ȡ�����ļ�
		cfx.configure();  //��Ĭ�϶�ȡhibernate�������ļ�,������Ҳ����д������
		//����session����
		sessionFactory=cfx.buildSessionFactory();
	}
	
	//�õ�session
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	//�õ�����
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//�ر���Դ
	public static void close(Session s){
		if(s!=null){
			s.close();
		}
	}
	
	public static List<Book> selectBook(){
		Session s=null;
		List<Book> bList=null;
		try{
			s=HibUtil.getSession();
			String hql="from Book";
			Query q=s.createQuery(hql);
			
			bList=q.list();	
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibUtil.close(s);
		}
		return bList;
	}
	
	public static boolean update(Object obj){
		Session s=null;
		Transaction tx=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
			s.update(obj); //����ֵΪ��ǰ���ݿ�id
			tx.commit();
			return true;
		}catch (Exception e) {
			ActionContext.getContext().put("err", "�ü�¼�ѱ��޸ģ������·����޸�");
		}finally{
			HibUtil.close(s);
		}
		return false;
	}
	
	public static Book queryBid(int bid){
		Session s=null;
		Transaction tx=null;
		Book book=null;
		try{
			s=HibUtil.getSession();
			String hql="from Book where bid=? ";
			Query q=s.createQuery(hql);
			q.setInteger(0, bid);
			
			book=(Book)q.uniqueResult();	
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibUtil.close(s);
		}
		return book;
	}


	//��׼����ӷ���
	/*public static int add(Object obj){
		Session s=null;
		Transaction tx=null;
		Serializable i=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
			i=s.save(obj);  //����ֵΪ��ǰ���ݿ�id
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibUtil.close(s);
		}
		return (Integer)i;
	}
	
	public static void update(Object obj){
		Session s=null;
		Transaction tx=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
			s.update(obj);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibUtil.close(s);
		}
	}
	
	public static void delete(Object obj){
		Session s=null;
		Transaction tx=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
			s.delete(obj);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibUtil.close(s);
		}
	}
	
	public static Object select(int id){
		Session s=null;
		Transaction tx=null;
		Object o=null;
		try{
			s=HibUtil.getSession();
			tx=s.beginTransaction();
		    o=s.get(AdminInfo.class, id);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibUtil.close(s);
		}
		return o;
	}*/
	
}