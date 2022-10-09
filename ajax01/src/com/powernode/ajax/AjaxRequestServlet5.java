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

@WebServlet("/ajaxrequest5")
public class AjaxRequestServlet5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Student> stuList = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            String sql = "select stuname, age, address from t_stu;";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("stuname");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                stuList.add(new Student(name, age, address));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.write(JSON.toJSONString(stuList));

    }
}
