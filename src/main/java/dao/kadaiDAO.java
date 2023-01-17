package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.kadaiDTO;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class kadaiDAO {
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	
	}
	public static int registerKadaiAccount(kadaiDTO kadai) {
		String sql = "INSERT INTO kadai_account VALUES(default, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		
		// ランダムなソルトの取得(今回は32桁で実装)
		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(kadai.getPassword(), salt);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, kadai.getName());
			pstmt.setString(2, kadai.getGender());
			pstmt.setInt(3, kadai.getAge());
			pstmt.setString(4, kadai.getTell());
			pstmt.setString(5, kadai.getMail());
			pstmt.setString(6, salt);
			pstmt.setString(7, hashedPw);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	public static List<kadaiDTO> selectAllAccount() {
		
		// 返却用変数
		List<kadaiDTO> result = new ArrayList<>();

		String sql = "SELECT * FROM kadai_account";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			try (ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String gender = rs.getString("gender");
					int age = rs.getInt("age");
					String tell = rs.getString("tell");
					String mail = rs.getString("mail");

					kadaiDTO acc = new kadaiDTO(id, name, gender, age, tell, mail, null, null, null);
					
					result.add(acc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// Listを返却する。0件の場合は空のListが返却される。
		return result;
	}
	public static int deleteAccount(String name) {
		String sql = "DELETE FROM kadai_account WHERE name = ?";
		int result = 0;

		try (
				Connection con = getConnection();	// DB接続
				PreparedStatement pstmt = con.prepareStatement(sql);			// 構文解析
				){
			
			pstmt.setString(1, name);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace(); 
		}finally {
			System.out.println(result + "件削除しました。");
		}
		return result;
	}
	
	// メールアドレスを元にソルトを取得
	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM kadai_account WHERE mail = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ログイン処理
	public static kadaiDTO login(String mail, String hashedPw) {
		String sql = "SELECT * FROM kadai_account WHERE mail = ? AND password = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, hashedPw);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String gender = rs.getString("gender");
					int age = rs.getInt("age");
					String tell = rs.getString("tell");
					String salt = rs.getString("salt");
					String createdAt = rs.getString("created_at");
					
					return new kadaiDTO(id, name, gender, age, tell, mail, salt, null, null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}