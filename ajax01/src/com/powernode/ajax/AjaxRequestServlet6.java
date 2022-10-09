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

@WebServlet("/ajaxrequest6")
public class AjaxRequestServlet6 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();

        StringBuilder sb = new StringBuilder();
        sb.append("<students>");
        sb.append("<student>");
        sb.append("<name>张三</name>");
        sb.append("<age>10</age>");
        sb.append("</student>");
        sb.append("<student>");
        sb.append("<name>李四</name>");
        sb.append("<age>12</age>");
        sb.append("</student>");
        sb.append("</students>");

        out.write(sb.toString());

    }
}
