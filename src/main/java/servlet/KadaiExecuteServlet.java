package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.kadaiDAO;
import dto.kadaiDTO;

/**
 * Servlet implementation class KadaiExecuteServlet
 */
@WebServlet("/KadaiExecuteServlet")
public class KadaiExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープのインスタンス取得
		HttpSession session = request.getSession();

		// 入力データの取得
		kadaiDTO account = (kadaiDTO)session.getAttribute("data");
		
		// 登録処理
		int result = kadaiDAO.registerKadaiAccount(account);
		
		String path = "";
		if(result == 1) {
			// 登録に成功したので、sessionのデータを削除
			session.removeAttribute("data");
			
			path = "WEB-INF/view/kadaisuccess.jsp";
		} else {
			// 失敗した場合はパラメータ付きで登録画面に戻す
			path = "WEB-INF/view/kadaiform.jsp?error=1";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
