package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //DAOインスタンス（Hibernateバージョン）である em を生成
        EntityManager em = DBUtil.createEntityManager();

        //DAOインスタンス(em)を使って、DBのmessagesテーブルから全データを取得してDTOインスタンスのリストに変換
        //getAllMessages を createNamedQuery メソッドの引数に指定することで、データベースへの問い合わせを実行できるようになる
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).getResultList();
        response.getWriter().append(Integer.valueOf(messages.size()).toString());

        em.close();
    }

}
