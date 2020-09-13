/**
 * ���ܣ����ݿ����ӣ���ɾ��ĳ�������Ա
 * ���ߣ��캣��
 */
package com.ie.dao;

import java.sql.*;
import java.util.*;
import com.ie.bean.*;

public class DBconnect {
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	private int result;
	public Connection getConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("������������!");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?useUnicode=true&characterEncoding=utf8","root","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return conn;
	} 
	public void releaseDB(ResultSet rs,PreparedStatement pstm,Connection conn)
			{
				if(rs!=null)
				{
					try{
						rs.close();
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
				if(pstm!=null)
				{
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(conn!=null)
				{
					try{
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	public void allUser()
	{
		conn = getConnection();
		try {
			pstm = conn.prepareStatement("select * from manager");
			rs = pstm.executeQuery();
			while(rs.next())
			{
				String mid = rs.getString("mid");
				String mname= rs.getString("mname");
				String mpwd= rs.getString("mpwd");
				System.out.println("id��"+mid+"������"+mid+"���룺"+mpwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			releaseDB(rs,pstm,conn);
		}
	}
	public ArrayList<Manager> queryAllUserAL()
	{
		conn = getConnection();
		ArrayList<Manager> userlist=new ArrayList<Manager>();
		try {
			pstm = conn.prepareStatement("select * from manager");
			rs=pstm.executeQuery();
			while(rs.next())
			{
				Manager user=new Manager();
				user.setmid(rs.getString("mid"));
				user.setmname(rs.getString("mname"));
				user.setmpwd(rs.getString("mpwd"));
				user.setmroot(rs.getString("mroot"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userlist;
	}
	//��ӳ�������Ա
	public int addUser(String mid,String mname,String mpwd,String mroot)
	{
		conn=getConnection();
		try
		{
			pstm=conn.prepareStatement("insert into manager set mid=?,mname=?,mpwd=?");
			pstm.setString(1, mid);
			pstm.setString(2, mname);
			pstm.setString(3, mpwd);
			pstm.setString(4, mroot);
			result=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			releaseDB(rs,pstm,conn);
			}
			return result;
	}
	//��ӳ�������Ա����װ��
	public int addUser(Manager user)
	{
		conn=getConnection();
		try {
				pstm=conn.prepareStatement("insert into manager	 set mid=?,mname=?,mpwd=?,mroot=?");
				 pstm.setString(1, user.getmid());
				 pstm.setString(2, user.getmname());
				 pstm.setString(3, user.getmpwd());
				 pstm.setString(4, user.getmroot());
				 result=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		} finally{
				releaseDB(rs,pstm,conn);
				}
			return result;
	}
	//�޸ĳ�������Ա
	public int updateUser(Manager user,String mid)
	{
		conn=getConnection();
		try {
				pstm=conn.prepareStatement("update manager set mid=?,mname=?,mpwd=?��mroot=?");
				pstm.setString(1, mid);
				pstm.setString(2, user.getmname());
				pstm.setString(3, user.getmpwd());
				pstm.setString(4, user.getmroot());
				result=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
				releaseDB(rs,pstm,conn);
				}
			return result;
	}
	//��ѯ��������Ա�Ƿ����
	public boolean queryUserbyName(String mid)
	{
		conn=getConnection();
		boolean flag=false;
		
		String sql="select * from manager where mid=?";
		try
		{
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, mid);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			releaseDB(rs,pstm,conn);
		}
		return flag;
	}
	//ɾ����������Ա
	public int deleteUser(String mid)
	{
		conn=getConnection();
		try {
				pstm=conn.prepareStatement("delete from manager where mid=?");
				pstm.setString(1, mid);
				result=pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			} finally{
					releaseDB(rs,pstm,conn);
			}
				return result;
	}
	
	//
	
	//��ѯ�ܼ�¼��
	public int findCount(){
		// �ܼ�¼��
		int count = 0;
		// ��ȡ���ݿ�����
		Connection conn = getConnection();
		// ��ѯ�ܼ�¼��SQL���
		String sql = "select count(*) from book";
		try {
			// ����Statement
			Statement stmt = conn.createStatement();
			// ��ѯ����ȡResultSet
			ResultSet rs = stmt.executeQuery(sql);
			// �������ƶ������ж��Ƿ���Ч
			if(rs.next()){
				// ���ܼ�¼����ֵ
				count = rs.getInt(1);
			}
			// �ر�ResultSet
			rs.close();
			// �ر�Connection
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �����ܼ�¼��
		return count;
	}

	
	
	public static void main(String[] args) {
		  DBconnect t=new DBconnect();
		  	Connection conn = t.getConnection();
		  	if(!conn.equals(null))
		  	{
		  		System.out.println("���ݿ����ӳɹ�");
		  	}
		  	ArrayList<Manager> list = t.queryAllUserAL();//---ģ�⿪����jsp�������ݣ���forѭ��������ѯ����
		  	for(int i=0;i<list.size();i++)
		  	{
		  		Manager user=new Manager();
		  		user=list.get(i);
		  		System.out.print(user.getmid()+"---");
		  		System.out.print(user.getmname()+"---");
		  		System.out.print(user.getmpwd());
		  		System.out.println(user.getmroot());
		  		System.out.println();
		  	}
		  	System.out.println();
		  	for(int i=0;i<list.size();i++)
		  	{
		  		Manager user=new Manager();
		  		user=list.get(i);
		  		System.out.print(user.getmid()+"---");
		  		System.out.print(user.getmname()+"---");
		  		System.out.print(user.getmpwd());
		  		System.out.println();
		  	}
		}
}
