package com.app.serverImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.app.entities.superType;
import com.app.server.jdbcutil;
import com.app.server.superTypeServer;

public class superTypeServerImpl implements superTypeServer {
	@Override
	public void add(superType supertype) {
		        Connection conn=null;
		        Statement stmt=null;
		        conn=jdbcutil.getConection();
		        String addSql= "insert into super_type(type_id,type_name) values("+
		        		supertype.getTypeId()+",'"+supertype.getTypeName()+"')";
	
		        try {
		            stmt=conn.createStatement();
		            stmt.execute(addSql);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        jdbcutil.close(conn);
	}

	@Override
	public List<superType> getAll() {
		List<superType> supertypelist = new LinkedList<superType>();
		
			       Connection conn=null;//����Ϊ��ֵ
			       Statement stmt=null;
			       ResultSet rs=null;
			       String sql="select * from super_type";//sql���
			       conn=jdbcutil.getConection();
			       try {
			        stmt=conn.createStatement();//����һ��Statement������
			        rs=stmt.executeQuery(sql);//ִ��sql���
			        while(rs.next()){
			        	superType supertype = new superType();
			        	supertype.setTypeId(rs.getInt(1));
			            supertype.setTypeName(rs.getString(2));
			            supertypelist.add(supertype);
			            }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }finally{
			        jdbcutil.close(conn);//�ر����ݿ�
			    }
			       return supertypelist;
	}

	@Override
	public superType getById(Integer id) {
		superType supertype = new superType();
		 Connection conn=null;//����Ϊ��ֵ
	       Statement stmt=null;
	       ResultSet rs=null;
	       String sql="select * from super_type where type_id="+id;//sql���
	       conn=jdbcutil.getConection();
	       try {
	        stmt=conn.createStatement();//����һ��Statement������
	        rs=stmt.executeQuery(sql);//ִ��sql��� 
	        while(rs.next()){
	        	supertype.setTypeId(rs.getInt(1));
	            supertype.setTypeName(rs.getString(2));
	            }
	     
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
	        jdbcutil.close(conn);//�ر����ݿ�
	    }
	       return supertype;
	}

	@Override
	public String deleteTypeById(Integer id) {
		 Connection conn=null;//����Ϊ��ֵ
	       Statement stmt=null;
	       ResultSet rs=null;
	       String sql1="select * from supermarket where super_type_id="+id;//sql���
	       
	       String sql="delete  from super_type where type_id="+id;//sql���
	       conn=jdbcutil.getConection();
	       
	       try {
	        stmt=conn.createStatement();//����һ��Statement������
	        rs=stmt.executeQuery(sql1);//ִ��sql��� 
	        while(rs.next()){
	        	return "�й�������Ʒ����ɾ��";
	            }
	        stmt.executeUpdate(sql);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
	        jdbcutil.close(conn);//�ر����ݿ�
	    }
	       return "ɾ���ɹ�";
	}

	@Override
	public String updateTypeById(Integer id, superType supertype) {
		superType supertype1 = new superType();
		Connection conn=null;//����Ϊ��ֵ
		 PreparedStatement stmt=null;	       
	       String sql="update super_type set type_name=? where type_id="+id;//sql���
	       conn=jdbcutil.getConection();
	       
	       try {
	    	   stmt = conn.prepareStatement(sql);
	    	   stmt.setString(1, supertype.getTypeName());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
	        jdbcutil.close(conn);//�ر����ݿ�
	    }
		return "���³ɹ�";
	}
}
