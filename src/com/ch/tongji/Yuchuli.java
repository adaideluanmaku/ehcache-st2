package com.ch.tongji;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//预处理数据对比
public class Yuchuli {
	public static int Sa_screenresults() throws SQLException, ClassNotFoundException{
		Jdbcconnection jdbc=new Jdbcconnection();
		Connection conn=jdbc.getConn();
		Mysqlconnection jdbc1=new Mysqlconnection();
		Connection conn1=jdbc1.getConn();
		
		//JAVA-WIN找出所有病人名称
		String sqlpat="select patname,usedate from sa_screenresults group by patname,usedate";
		Statement stpat=conn.createStatement();
		ResultSet rspat=stpat.executeQuery(sqlpat);
		List rslistpat=jdbc.getlist(rspat);
		stpat.close();
		rspat.close();
		int rslistpatnum=rslistpat.size();
		
		//循环找PASS-WIN和PASS-JAVA病人的数据
		for(int i=0;i<rslistpat.size();i++){
			Map mappat=(Map)rslistpat.get(i);
			String patname=mappat.get("patname").toString();
//			String patname="不良反应013-tj";
			String usedate=mappat.get("usedate").toString();
//			System.out.println("开始处理"+mappat.get("patname").toString());
			String sql="select * from sa_screenresults where patname='"+patname+"' order by orderindex asc";
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			List rslist=jdbc.getlist(rs);
			st.close();
			rs.close();
			
			String sql1="select * from sa_screenresults where patname='"+patname+"' order by orderindex asc";
			Statement st1=conn1.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			List rslist1=jdbc1.getlist(rs1);
			st1.close();
			rs1.close();
			if(rslist.size()!=rslist1.size()){
				System.out.println("---------------------sa_screenresults总数不同,patname=\""+patname+"\"");
			}else if(rslist.size()==rslist1.size()){
				for(int i1=0;i1<rslist.size();i1++){
//					System.out.println("对比第"+(i1+1)+"条数据");
					Map map=(Map)rslist.get(i1);
					Map map1=(Map)rslist1.get(i1);
					Set zdname=map.keySet();
					for (Iterator iter = zdname.iterator(); iter.hasNext();) {
						String str = (String)iter.next();
//						System.out.println(str);
						if("chkresid".equals(str)){
							continue;
						}
						if("caseid".equals(str)){
							continue;
						}
						if("hiscode".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("---------------sa_screenresults-----patname=\""+patname+"\"的"+str+"数据不同");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
						if("hisname".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("--------------sa_screenresults------patname=\""+patname+"\"的"+str+"数据不同");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
//						System.out.println(map.get(str));
//						System.out.println(map1.get(str));
						if(!map.get(str).equals(map1.get(str))){
							System.out.println("--------------sa_screenresults------patname=\""+patname+"\"的"+str+"数据不同");
							System.out.println("PASS-WIN:"+map.get(str));
							System.out.println("PASS-JAVA:"+map1.get(str));
						}
					}
				}
			}
			
			//------------------------------------------------------
			
//			System.out.println("开始处理sa_pat_allergens表");
			String sql2="select * from sa_pat_allergens where caseid in(select caseid from sa_screenresults where patname='"+patname+"')";
			Statement st2=conn.createStatement();
			ResultSet rs2=st2.executeQuery(sql2);
			List rslist2=jdbc.getlist(rs2);
			st2.close();
			rs2.close();
			
			String sql3="select * from sa_pat_allergens where logid in(select logid from sa_screenresults where patname='"+patname+"')";
			Statement st3=conn1.createStatement();
			ResultSet rs3=st3.executeQuery(sql3);
			List rslist3=jdbc1.getlist(rs3);
			st3.close();
			rs3.close();
			if(rslist2.size()!=rslist3.size()){
				System.out.println("---------------------sa_pat_allergens总数不同,patname=\""+patname+"\"");
			}else if(rslist2.size()==rslist3.size()){
				for(int i1=0;i1<rslist2.size();i1++){
//					System.out.println("对比第"+(i1+1)+"条数据");
					Map map=(Map)rslist2.get(i1);
					Map map1=(Map)rslist3.get(i1);
					Set zdname=map.keySet();
					for (Iterator iter = zdname.iterator(); iter.hasNext();) {
						String str = (String)iter.next();
//						System.out.println(str);
						if("allerid".equals(str)){
							continue;
						}
						if("caseid".equals(str)){
							continue;
						}
						if("hiscode".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("---------------sa_pat_allergens-----patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
						if("hisname".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("--------------sa_pat_allergens------patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
//						System.out.println(map.get(str));
//						System.out.println(map1.get(str));
						if(!map.get(str).equals(map1.get(str))){
							System.out.println("--------------sa_pat_allergens------patname=\""+patname+"\"的"+str+"数据不同");
							System.out.println("PASS-WIN:"+map.get(str));
							System.out.println("PASS-JAVA:"+map1.get(str));
						}
					}
				}
			}
			
			//------------------------------------------------------
			
//			System.out.println("开始处理sa_pat_disease表");
			String sql4="select * from sa_pat_disease where caseid in(select caseid from sa_screenresults where patname='"+patname+"')";
			Statement st4=conn.createStatement();
			ResultSet rs4=st4.executeQuery(sql4);
			List rslist4=jdbc.getlist(rs4);
			st4.close();
			rs4.close();
			
			String sql5="select * from sa_pat_disease where logid in(select logid from sa_screenresults where patname='"+patname+"')";
			Statement st5=conn1.createStatement();
			ResultSet rs5=st5.executeQuery(sql5);
			List rslist5=jdbc1.getlist(rs5);
			st5.close();
			rs5.close();
			if(rslist4.size()!=rslist5.size()){
				System.out.println("---------------------sa_pat_disease总数不同,patname=\""+patname+"\"");
			}else if(rslist4.size()==rslist5.size()){
				for(int i1=0;i1<rslist4.size();i1++){
//					System.out.println("对比第"+(i1+1)+"条数据");
					Map map=(Map)rslist4.get(i1);
					Map map1=(Map)rslist5.get(i1);
					Set zdname=map.keySet();
					for (Iterator iter = zdname.iterator(); iter.hasNext();) {
						String str = (String)iter.next();
//						System.out.println(str);
						if("disid".equals(str)){
							continue;
						}
						if("caseid".equals(str)){
							continue;
						}
						if("hiscode".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("---------------sa_pat_disease-----patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
						if("hisname".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("--------------sa_pat_disease------patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
//						System.out.println(map.get(str));
//						System.out.println(map1.get(str));
						if(!map.get(str).equals(map1.get(str))){
							System.out.println("--------------sa_pat_disease------patname=\""+patname+"\"的"+str+"数据不同");
							System.out.println("PASS-WIN:"+map.get(str));
							System.out.println("PASS-JAVA:"+map1.get(str));
						}
					}
				}
			}
			
//------------------------------------------------------
			
//			System.out.println("开始处理sa_pat_info表");
			String sql6="select * from sa_pat_info where caseid in(select caseid from sa_screenresults where patname='"+patname+"')";
			Statement st6=conn.createStatement();
			ResultSet rs6=st6.executeQuery(sql6);
			List rslist6=jdbc.getlist(rs6);
			st6.close();
			rs6.close();
			
			String sql7="select * from sa_pat_info where logid in(select logid from sa_screenresults where patname='"+patname+"')";
			Statement st7=conn1.createStatement();
			ResultSet rs7=st7.executeQuery(sql7);
			List rslist7=jdbc1.getlist(rs7);
			st7.close();
			rs7.close();
			if(rslist6.size()!=rslist7.size()){
				System.out.println("---------------------sa_pat_info总数不同,patname=\""+patname+"\"");
			}else if(rslist6.size()==rslist7.size()){
				for(int i1=0;i1<rslist6.size();i1++){
//					System.out.println("对比第"+(i1+1)+"条数据");
					Map map=(Map)rslist6.get(i1);
					Map map1=(Map)rslist7.get(i1);
					Set zdname=map.keySet();
					for (Iterator iter = zdname.iterator(); iter.hasNext();) {
						String str = (String)iter.next();
//						System.out.println(str);
						if("inserttime".equals(str)){
							continue;
						}
						if("patid".equals(str)){
							continue;
						}
						if("caseid".equals(str)){
							continue;
						}
						if("hiscode".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("---------------sa_pat_info-----patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
						if("hisname".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("--------------sa_pat_info------patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
//						System.out.println(map.get(str));
//						System.out.println(map1.get(str));
						if(!map.get(str).equals(map1.get(str))){
							System.out.println("--------------sa_pat_info------patname=\""+patname+"\"的"+str+"数据不同");
							System.out.println("PASS-WIN:"+map.get(str));
							System.out.println("PASS-JAVA:"+map1.get(str));
						}
					}
				}
			}
			
//------------------------------------------------------
			
//			System.out.println("开始处理sa_pat_operation表");
			String sql8="select * from sa_pat_operation where caseid in(select caseid from sa_screenresults where patname='"+patname+"')";
			Statement st8=conn.createStatement();
			ResultSet rs8=st8.executeQuery(sql8);
			List rslist8=jdbc.getlist(rs8);
			st8.close();
			rs8.close();
			
			String sql9="select * from sa_pat_operation where logid in(select logid from sa_screenresults where patname='"+patname+"')";
			Statement st9=conn1.createStatement();
			ResultSet rs9=st9.executeQuery(sql9);
			List rslist9=jdbc1.getlist(rs9);
			st9.close();
			rs9.close();
			if(rslist8.size()!=rslist9.size()){
				System.out.println("---------------------sa_pat_operation总数不同,patname=\""+patname+"\"");
			}else if(rslist8.size()==rslist9.size()){
				for(int i1=0;i1<rslist8.size();i1++){
//					System.out.println("对比第"+(i1+1)+"条数据");
					Map map=(Map)rslist8.get(i1);
					Map map1=(Map)rslist9.get(i1);
					Set zdname=map.keySet();
					for (Iterator iter = zdname.iterator(); iter.hasNext();) {
						String str = (String)iter.next();
//						System.out.println(str);
						if("oprid".equals(str)){
							continue;
						}
						if("caseid".equals(str)){
							continue;
						}
						if("hiscode".equals(str)){
							if("".equals(map1.get(str))  || map1.get(str)==null){
								System.out.println("---------------sa_pat_operation-----patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
						if("hisname".equals(str)){
							if("".equals(map1.get(str)) || map1.get(str)==null){
								System.out.println("--------------sa_pat_operation------patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
//						System.out.println(map.get(str));
//						System.out.println(map1.get(str));
						if(!map.get(str).equals(map1.get(str))){
							System.out.println("--------------sa_pat_operation------patname=\""+patname+"\"的"+str+"数据不同");
							System.out.println("PASS-WIN:"+map.get(str));
							System.out.println("PASS-JAVA:"+map1.get(str));
						}
					}
				}
			}
			
//------------------------------------------------------
			
//			System.out.println("开始处理sa_pat_orders表");
			String sql10="select * from sa_pat_orders where caseid in(select caseid from sa_screenresults where patname='"+patname+"')";
			Statement st10=conn.createStatement();
			ResultSet rs10=st10.executeQuery(sql10);
			List rslist10=jdbc.getlist(rs10);
			st10.close();
			rs10.close();
			
			String sql11="select * from sa_pat_orders where logid in(select logid from sa_screenresults where patname='"+patname+"')";
			Statement st11=conn1.createStatement();
			ResultSet rs11=st11.executeQuery(sql11);
			List rslist11=jdbc1.getlist(rs11);
			st11.close();
			rs11.close();
			if(rslist10.size()!=rslist11.size()){
				System.out.println("---------------------sa_pat_orders总数不同,patname=\""+patname+"\"");
			}else if(rslist10.size()==rslist11.size()){
				for(int i1=0;i1<rslist10.size();i1++){
//					System.out.println("对比第"+(i1+1)+"条数据");
					Map map=(Map)rslist10.get(i1);
					Map map1=(Map)rslist11.get(i1);
					Set zdname=map.keySet();
					for (Iterator iter = zdname.iterator(); iter.hasNext();) {
						String str = (String)iter.next();
//						System.out.println(str);
						if("cid".equals(str)){
							continue;
						}
						if("caseid".equals(str)){
							continue;
						}
						if("hiscode".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("---------------sa_pat_orders-----patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
						if("hisname".equals(str)){
							if("".equals(map1.get(str))){
								System.out.println("--------------sa_pat_orders------patname=\""+patname+"\"的"+str+"数据为空");
								System.out.println("PASS-JAVA:"+map1.get(str));
							}
							continue;
						}
//						System.out.println(map.get(str));
//						System.out.println(map1.get(str));
						if(!map.get(str).equals(map1.get(str))){
							System.out.println("--------------sa_pat_orders------patname=\""+patname+"\"的"+str+"数据不同");
							System.out.println("PASS-WIN:"+map.get(str));
							System.out.println("PASS-JAVA:"+map1.get(str));
						}
					}
				}
			}
			
			
		}
		conn.close();
		conn1.close();
		return rslistpatnum;
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		int sum=Sa_screenresults();
		System.out.println("对比结束,病人数"+sum);
	}
}
