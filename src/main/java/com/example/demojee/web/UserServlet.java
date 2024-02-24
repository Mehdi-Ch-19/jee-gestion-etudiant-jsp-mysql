package com.example.demojee.web;

import com.example.demojee.dao.UserDao;
import com.example.demojee.model.User;
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
    private UserDao userDao;

        public UserServlet() {
            this.userDao = new UserDao();
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
        List<User> listUser = userDao.getAllEtudiants();
        request.setAttribute("listUsers", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-form.jsp");
            requestDispatcher.forward(request,response);
    }

    private void insertEtudiant(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, SQLException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String adresse = request.getParameter("adresse");
        User user = new User(name,email,adresse);
        userDao.insertUser(user);
        response.sendRedirect("list");

    }

        private void deleteEtudiant(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

            int id = Integer.parseInt(request.getParameter("id"));
            userDao.deleteEtudiant(id);
            response.sendRedirect("list");
        }
        private void showEditForm(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            User existUser = userDao.selectUser(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            request.setAttribute("user", existUser);
            dispatcher.forward(request, response);
        }
    private void updateEtudiant(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String adresse = request.getParameter("adresse");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id,name,email,adresse);
        userDao.updateRtudiant(user);
        response.sendRedirect("list");

    }

    }
