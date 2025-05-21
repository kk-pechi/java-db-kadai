package kadai_004;

//JDBCを取り扱うためのクラス
import java.sql.DriverManager;
//データベースとの接続を管理するクラス
import java.sql.Connection;
import java.sql.Statement;
//データベースの取り扱いで発生した例外を処理するクラス
import java.sql.SQLException; 

public class Employees_Chapter04 {

	public static void main(String[] args) {
		
//		データベースに接続　DriverManagerクラスのgetConnection()メソッド
		
        Connection con = null;
        Statement statement = null;
		
	try {
		con = DriverManager.getConnection(
			"jdbc:mysql://localhost/challenge_java",
			"root",
			"Krck2101db123a"
		);
		
		System.out.println("データベース接続成功" + con);
		
//		SQLクエリの準備
		statement = con.createStatement();
		String sql = """
					CREATE TABLE employees (
						id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
						name VARCHAR(60) NOT NULL,
						email VARCHAR(255) NOT NULL,
						age INT(11),
						address VARCHAR(255)
					);
					""";

		
//		SQLクエリの実行　StatementクラスのexecuteUpdate()メソッド
		int rowCnt = statement.executeUpdate(sql);
		System.out.println("社員テーブルを作成しました：更新レコード数=" + rowCnt);
		
		}catch(SQLException e) {
			System.out.println("エラー発生:" + e.getMessage());
			
		} finally {		
//			実行後の処理close()メソッド
			if( statement != null ) {
				try { statement.close(); } catch(SQLException ignore) {}
			}
			if( con != null ) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}
	}
}
