package com.bjpowernode.autocom;

import com.alibaba.fastjson.JSON;
import com.bjpowernode.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.geom.Area;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/autocom")
public class AutoComServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Content> areaList = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();

            String sql = "select content from t_autocom where content like ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                areaList.add(new Content(content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        response.setContentType("html/text");
        PrintWriter out = response.getWriter();
        String ret = JSON.toJSONString(areaList);
        out.write(ret);
    }
}
