package com.example.demojee.web;

import com.example.demojee.dao.EtudiantDao;
import com.example.demojee.model.Etudiant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "etudiantservlet", value = "/")

public class UserServlet extends HttpServlet {
    String destination = "user.jsp";
    private EtudiantDao etudiantDao;

        public UserServlet() {
            this.etudiantDao = new EtudiantDao();
        }

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getServletPath();
            System.out.println(action);
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    try {
                        insertEtudiant(request, response);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    break;
                case "/delete":
                    deleteEtudiant(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    try {
                        updateEtudiant(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try {
                        listEtudiant(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
    private void listEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Etudiant> listUser = etudiantDao.getAllEtudiants();
        request.setAttribute("listEtidiant", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("etudiant-form.jsp");
            requestDispatcher.forward(request,response);
    }

    private void insertEtudiant(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, SQLException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        double note = Double.parseDouble(request.getParameter("note"));
        Etudiant etudiant = new Etudiant(name,email,note);
        etudiantDao.insertEtudiant(etudiant);
        response.sendRedirect("list");

    }

        private void deleteEtudiant(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("etudiant-form.jsp");
            requestDispatcher.forward(request,response);
            int id = Integer.parseInt(request.getParameter("id"));
            etudiantDao.deleteEtudiant(id);
            response.sendRedirect("list");
        }
        private void showEditForm(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Etudiant existEtudiant = etudiantDao.selectEtudaint(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant-form.jsp");
            request.setAttribute("etudiant", existEtudiant);
            dispatcher.forward(request, response);
        }
    private void updateEtudiant(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        double note = Double.parseDouble(request.getParameter("note"));
        Etudiant etudiant = new Etudiant(name,email,note);
        etudiantDao.updateRtudiant(etudiant);
        response.sendRedirect("list");

    }

    }
