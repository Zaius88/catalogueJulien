package org.mypackage.CJServlet;

import org.mypackage.CatalogueJulien.Produit;
import java.sql.*;
import javax.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;


/**
 * @author Julien
 */
public class CJServlet extends HttpServlet {
    
    //Pour éviter le scope du try non accessible ailleurs
    Produit[] catalogueFinal = null;
    
    //init Servlet + Connection sql
    private ServletContext context;
    InitialContext ctx = null;
    DataSource ds = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Produits"; 
  
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            this.context = config.getServletContext();
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CJdatasource");
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);  
        }
        catch (SQLException se) {
            System.out.println("SQLException: "+se.getMessage());
        }
        catch (NamingException ne) {
            System.out.println("NamingException: "+ne.getMessage());
        }    
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String id = request.getParameter("leID");
        
        try{
            rs = ps.executeQuery(sql);
            rs.next();
            Produit prod1 = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
            rs.next();
            Produit prod2 = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
            rs.next();
            Produit prod3 = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
            rs.next();
            Produit prod4 = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
            Produit[] catalogue  = {prod1, prod2, prod3, prod4};
            catalogueFinal = catalogue;
        }
        catch (SQLException se) {
            System.out.println("SQLException: "+se.getMessage());
        }
  
        if ("affiche".equals(name)){
            int idMod = Integer.parseInt(id);
            idMod--;
            String description = catalogueFinal[idMod].getDescription();
            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(description);
        }
        
        if ("ajout".equals(name)){
            int idMod = Integer.parseInt(id);
            idMod = idMod - 1;
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(catalogueFinal[idMod].getId()+
                    "#"+catalogueFinal[idMod].getNom()+
                    "#"+catalogueFinal[idMod].getDescription());               
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
