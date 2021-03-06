
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tom
 */
public class UserAccount {

    private String username;
    private String password;
    private String fName;
    private String sName;
    private String email;
    private boolean type;          //True for admin, false for general. 

    /**
     *
     * @return
     */
    public String getUsername() {

        return username;
    }
    
    /**
     *
     * @param enteredUname
     * @param enteredPass
     * @return
     */
    public boolean LogInService(String enteredUname, String enteredPass) {
        //Checks entered username and password against ones stored in database.
        Connection con = DatabaseConnection.establishConnection();
        String dbUname, dbPassword;

        try {
            Statement stmt = (Statement) con.createStatement();
            String query = ("SELECT ID, Password, accountType FROM Account WHERE ID='" + enteredUname+"'");

            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                dbUname = rs.getString("ID");
                dbPassword = rs.getString("Password");
                boolean dbType = rs.getBoolean("accountType");
                if (dbUname.equals(enteredUname) && dbPassword.equals(enteredPass)) { //Comparison check
                    setType(dbType); //Sets type of user.
                    con.close();
                    return true;

                }

            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());   //Prints out SQL error 
        }

        return false;

    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param fname
     */
    public void setFname(String fname) {
        this.fName = fname;
    }

    /**
     *
     * @param sname
     */
    public void setSname(String sname) {
        this.sName = sname;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param type
     */
    public void setType(boolean type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public boolean getType() {
        return type;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LogIn frm = new LogIn();
        frm.setVisible(true);

    }

    /**
     *
     * @param username
     * @return
     */
    public boolean usernameAvailability(String username) {
        Connection con = DatabaseConnection.establishConnection();

        try {
            int availability;
            Statement stmt = (Statement) con.createStatement();
            String query = ("SELECT COUNT(*) FROM Account WHERE ID = '" + username + "'");

            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
               availability = rs.getInt("COUNT(*)");
               if(availability == 1){
                   return false;
               }else{
                return true;
               }
            }
            
        } catch (SQLException err) {

            System.out.println(err.getMessage());
            return false;
        }
        return false;
    }    
    
    /**
     *
     */
    public void saveUser(){
    
    Connection con = DatabaseConnection.establishConnection();
    
    try {
       String query = "INSERT INTO Account values (?,?,?,?,?,?)"; 
          
       PreparedStatement statement = con.prepareStatement(query);
    
       statement.setString(1,username);
       statement.setString(2, password);
       statement.setString(3, fName);
       statement.setString(4, sName);
       statement.setString(5,email);
       statement.setBoolean(6, type);
       statement.execute();
    }
    catch(SQLException err){
        
    
    }
    
    }
    
    
}

