
package guijava;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sv.org.arrupe.util.CheckPassword; // IMPORTANTE: El import de tu clase

/**
 *
 * @author josue
 */
public class MantenimientoEmpleados extends javax.swing.JInternalFrame {
ResultSet empleados;
    ResultSet llenarcombo; // Corregido: "ResulSet" estaba mal escrito
    static int bandera = 0;
    
    
    public MantenimientoEmpleados() throws SQLException {
        initComponents();
        // Hacemos que la ventana sea movible y cerrable
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Mantenimiento de Empleados");
        
        iniciarValores();
    }

    public void iniciarValores() throws SQLException {
        Conexion con = new Conexion();
        con.setRs("SELECT tipo_usuario_id, nombres, apellidos, edad, nombre_usuario, password FROM usuarios;");

        empleados = con.getRs(); // Casting innecesario quitado si getRs devuelve ResultSet
        
        if (empleados != null) { // Seguridad extra
             // Mover cursor
             if(empleados.last()){
                 empleados.beforeFirst();
                 empleados.next();
             }
        }

        Conexion con2 = new Conexion();
        con2.setRs("select tipo_usuario_id from usuarios");
        
        cmbTipoUsuario.removeAllItems();
        llenarcombo = con2.getRs();
        while (llenarcombo.next()) {
            cmbTipoUsuario.addItem(llenarcombo.getString(1));
        }
        con2.cerrarConexion();
        
        // Verificamos si hay empleados antes de intentar llenar
        if(empleados != null && !empleados.isAfterLast() && !empleados.isBeforeFirst()) {
            llenarTxtbox();
        }
        
        btnAnterior.setEnabled(false);
        btnSiguiente.setEnabled(true);
    }
    
    public void siguiente() throws SQLException {
        if (!empleados.isLast()) {
            btnAnterior.setEnabled(true);
            empleados.next();
            llenarTxtbox();
        } else {
            JOptionPane.showMessageDialog(this, "Ya no existen más registros para recorrer");
            empleados.previous(); // Mantenerse en el último valido
            btnSiguiente.setEnabled(false);
        }
    }
    
    private void anterior() throws SQLException {
        if (!empleados.isFirst()) {
            btnAnterior.setEnabled(true);
            empleados.previous();
            llenarTxtbox();
        } else {
            JOptionPane.showMessageDialog(this, "Ya no existen más registros para recorrer");
            empleados.next(); // Mantenerse en el primero valido
            btnAnterior.setEnabled(false);
        }
    }
    
    private void limpiarText() {
        txtID.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtNombreUsuario.setText("");
        // cmbTipoUsuario.setSelectedIndex(0); // Comentado por si acaso está vacío
        txtPassword.setText("");
    }
    
    private void llenarTxtbox() throws SQLException {
        txtID.setText(String.valueOf(empleados.getInt("tipo_usuario_id")));
        txtNombres.setText(empleados.getString("nombres"));
        txtApellidos.setText(empleados.getString("apellidos"));
        txtEdad.setText(String.valueOf(empleados.getInt("edad")));
        txtNombreUsuario.setText(empleados.getString("nombre_usuario"));
        txtPassword.setText(empleados.getString("password"));
        
        String tipoUsuarioVal = String.valueOf(empleados.getInt("tipo_usuario_id"));
        cmbTipoUsuario.setSelectedItem(tipoUsuarioVal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        txtNombreUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblEdad = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        lblPasswordUsuario = new javax.swing.JLabel();
        lblTipoUsuario = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnSiguiente = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información del empleado"));

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        lblId.setText("ID Empleado:");

        lblNombres.setText("Ingrese los nombres:");

        lblApellidos.setText("Ingrese los apellidos:");

        txtPassword.setText("jPasswordField1");

        lblEdad.setText("Ingrese la edad:");

        lblNombreUsuario.setText("Nombre de usuario:");

        lblPasswordUsuario.setText("Password:");

        lblTipoUsuario.setText("Tipo de Usuario:");

        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTipoUsuario)
                        .addGap(110, 110, 110)
                        .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEdad)
                                    .addComponent(lblPasswordUsuario)
                                    .addComponent(lblNombreUsuario))
                                .addGap(91, 91, 91)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtEdad)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApellidos)
                                    .addComponent(lblNombres)
                                    .addComponent(lblId))
                                .addGap(87, 87, 87)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombres)
                                    .addComponent(txtApellidos)
                                    .addComponent(txtID))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblId)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombres)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEdad)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPasswordUsuario)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoUsuario))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Siguiente(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar(evt);
            }
        });

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ingresar(evt);
            }
        });

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Anterior(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(btnIngresar)
                .addGap(35, 35, 35)
                .addComponent(btnAnterior)
                .addGap(46, 46, 46)
                .addComponent(btnSiguiente)
                .addGap(27, 27, 27)
                .addComponent(btnLimpiar)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente)
                    .addComponent(btnLimpiar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Ingresar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ingresar
            
        try {
            if (btnIngresar.getText().equals("Ingresar")) {
                btnIngresar.setText("Guardar");
                btnAnterior.setEnabled(false);
                btnSiguiente.setEnabled(false);
                limpiarText();
            } else {
                Conexion con2 = new Conexion();
                CheckPassword verificar = new CheckPassword();
                char passArray[] = txtPassword.getPassword();
                
                if (verificar.verificarPassword(passArray)) {
                    String password = new String(txtPassword.getPassword());
                    String tipoUsuario = (String) cmbTipoUsuario.getSelectedItem();

                    ResultSet rs = con2.getRs("SELECT tipo_usuario_id FROM tipo_usuario WHERE tipo_usuario_id = '" + tipoUsuario + "'"); // AJUSTA ESTE QUERY según tus datos reales

                    if (rs.next()) {
                        int tipoUsuarioID = rs.getInt("tipo_usuario_id");
                        
                        // CORRECCIÓN DEL STRING DEL QUERY (Estaba mal cortado)
                        String queryInsert = "INSERT INTO usuarios (usuario_id, tipo_usuario_id, nombres, apellidos, edad, nombre_usuario, password) VALUES ("
                                + txtID.getText() + ", " + tipoUsuarioID
                                + ", '" + txtNombres.getText() + "', '" + txtApellidos.getText() + "', "
                                + Integer.parseInt(txtEdad.getText()) + ", '" + txtNombreUsuario.getText() + "', '" + password + "')";
                        
                        con2.setQuery(queryInsert);

                        con2.cerrarConexion();
                        btnIngresar.setText("Ingresar");
                        JOptionPane.showMessageDialog(this, "Usuario Ingresado Exitosamente");

                        limpiarText();
                        iniciarValores();
                    } else {
                        JOptionPane.showMessageDialog(this, "El tipo de usuario seleccionado no es válido");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El password contiene caracteres inválidos");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException nfe) {
             JOptionPane.showMessageDialog(this, "La edad o el ID deben ser números");
        }
        
    }//GEN-LAST:event_Ingresar

    private void Anterior(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Anterior

    }//GEN-LAST:event_Anterior

    private void Siguiente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Siguiente

    }//GEN-LAST:event_Siguiente

    private void Limpiar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar
        limpiarText();
        
    }//GEN-LAST:event_Limpiar

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPasswordUsuario;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
