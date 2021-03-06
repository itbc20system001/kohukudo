package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabeans.HappyLife;

public class OrderDescDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mariadb://localhost/kohukudo";//未定
	private final String DB_USER = "root";
	private final String DB_PASS = "insource.2015it";

	public OrderDescDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
		try {
			Class.forName("org.mariadb.jdbc.Driver");//仮
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public boolean create(HappyLife happylife) {
		//データベース接続
		try {
			Class.forName("org.mariadb.jdbc.Driver");//仮
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {

			//INSERT文の準備
			String sql = "insert into order_desc(po_id, p_id) values(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1, happylife.getPo_id());//po_idの取得と設定。
			pStmt.setInt(2, happylife.getProductid());//p_idの取得と設定。

			//INSERT文の実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<HappyLife> getDescOrder (ArrayList<HappyLife> ordered,int i) {
		ArrayList<Integer> ordered_List=new ArrayList<Integer>();

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}


		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			String sql = "select p_id from order_desc where po_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,ordered.get(i).getPo_id());

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int p_id = rs.getInt("p_id");
				ordered_List.add(p_id);

			}
			ordered.get(i).setOrdered_List(ordered_List);

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ordered;
	}
}
