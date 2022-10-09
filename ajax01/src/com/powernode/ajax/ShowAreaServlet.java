package com.powernode.ajax;

import com.alibaba.fastjson.JSON;
import com.powernode.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ShowArea")
public class ShowAreaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pcode = request.getParameter("pcode");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Area> areaList = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            if (pcode == null) {
                String sql = "select code, name from t_area where pcode is null;";
                ps = conn.prepareStatement(sql);
            } else {
                String sql = "select code, name from t_area where pcode = ?;";
                ps = conn.prepareStatement(sql);
                ps.setString(1, pcode);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                areaList.add(new Area(code, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        response.setContentType("html/text");
        PrintWriter out = response.getWriter();

        out.write(JSON.toJSONString(areaList));

    }
}
