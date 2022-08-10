import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class javaCrud {
    private JTextField nombreDelProductoTF;
    private JTextField CiudadTF;
    private JTextField precioTF;
    private JButton borrarButton;
    private JButton actualizarBTN;
    private JButton grabarBTN;
    private JTextField idTF;
    private JPanel JavaCrud;
    private JTextField cantidadTF;
    private JLabel mensajeTF;
    private JButton buscarBTN;

    public static void main(String[] args) {
        JFrame frame = new JFrame("javaCrud");
        frame.setContentPane(new javaCrud().JavaCrud);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public javaCrud() {
        Connect();
        grabarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Create();
                Limpiar();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actualizarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar();
            }
        });
        actualizarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete();
            }
        });
    }
    Connection con;
    PreparedStatement pst;
    public void Connect(){
        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME= "csjurado";
        final String PASSWORD= "12345";
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();

            System.out.println("Conexion exitosa");

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }
    public void Create(){
        String nombre,precio,ciudad,id,cantidad;
        nombre=nombreDelProductoTF.getText();
        precio=precioTF.getText();
        ciudad=CiudadTF.getText();
        id=idTF.getText();
        cantidad=cantidadTF.getText();
        System.out.println(nombre);
        System.out.println(precio);

        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="csjurado";
        final String PASSWORD ="12345";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql= "insert into productos(pnombre,pciudad,pprecio,pcantidad)values(?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,ciudad);
            pst.setString(3,precio);
            pst.setString(4,cantidad);
            //ResultSet resultSet=pst.executeQuery();
            pst.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto ");
        }

    }
    public void Limpiar(){
        nombreDelProductoTF.setText("");
        precioTF.setText("");
        CiudadTF.setText("");
        idTF.setText("");
        cantidadTF.setText("");
    }
    public void Buscar (){
        String id="0";
        id=idTF.getText();

        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="csjurado";
        final String PASSWORD="12345";


        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="select * from productos where pid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,id);
            //System.out.println(sql);


            ResultSet rs=pst.executeQuery();


            if(rs.next()==true){
                String nombre, ciudad, precio, cantidad;
                nombre=rs.getString(2);
                ciudad=rs.getString(3);
                precio=rs.getString(4);
                cantidad=rs.getString(5);

                System.out.println();
                nombreDelProductoTF.setText(nombre);
                CiudadTF.setText(ciudad);
                precioTF.setText(precio);
                cantidadTF.setText(cantidad);

            }
            else {
                //textMensaje.setText("no se encuentra el producto");
                JOptionPane.showMessageDialog(null,"no se encuentra el producto");
                Limpiar();
            }
            stmt.close();
            conn.close();

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }
    public void Update(){
        String id,nombre, ciudad,precio,cantidad;
        id=idTF.getText();
        nombre=nombreDelProductoTF.getText();
        ciudad=CiudadTF.getText();
        precio=precioTF.getText();
        cantidad=cantidadTF.getText();

        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="csjurado";
        final String PASSWORD ="12345";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql= "update productos set pnombre=?,pciudad=?,pprecio=?,pcantidad=? where pid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,ciudad);
            pst.setString(3,precio);
            pst.setString(4,cantidad);
            pst.setString(5,id);
            //ResultSet resultSet=pst.executeQuery();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro actualizado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto ");
        }

    }
    public void Delete(){
        String borrarid=idTF.getText();
        /*
        String id,nombre, ciudad,precio,cantidad;
        id=idTF.getText();
        nombre=nombreDelProductoTF.getText();
        ciudad=CiudadTF.getText();
        precio=precioTF.getText();
        cantidad=cantidadTF.getText();
*/
        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="csjurado";
        final String PASSWORD ="12345";


        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql= "delete from productos where pid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,borrarid);
            //ResultSet resultSet=pst.executeQuery();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro borrado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto ");
        }
    }
}







