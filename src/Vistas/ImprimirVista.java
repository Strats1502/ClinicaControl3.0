package Vistas;

import ComponentesBeauty.*;
import Conexion.ConexionSQL;
import Conexion.InsertarSQL;
import Utilidades.Utilidades;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Utilidades.Utilidades.establecerIcono;

/**
 * Created by esva on 22/05/17.
 */
public class ImprimirVista extends JPanel {
    //Componentes
    BeautyTextField txtBuscar;
    BeautyImageButton btnNuevo;
    BeautyImageButton btnEditar;
    BeautyImageButton btnOpciones;
    BeautyImageButton btnFactura;
    BeautyBlackButton btnImprimir;

    BeautyTextField txtFolio;
    BeautyTextField txtFechaAlta;

    //Lista de busqueda
    public static DefaultListModel dlmBuscador = new DefaultListModel();
    public static BeautyList listaBuscador = new BeautyList();
    public static BeautyScrollPane scrollBuscador = new BeautyScrollPane(listaBuscador, dlmBuscador, 750, 60, 150, 730);

    //Lista de producto
    public static DefaultListModel dlmProducto = new DefaultListModel();
    public static BeautyList listaProducto = new BeautyList();
    public static BeautyScrollPane scrollProducto = new BeautyScrollPane(listaProducto, dlmProducto, 170,420,200, 100);

    //Lista de producto
    public static DefaultListModel dlmPresentacion = new DefaultListModel();
    public static BeautyList listaPresentacion = new BeautyList();
    public static BeautyScrollPane scrollPresentacion = new BeautyScrollPane(listaPresentacion,dlmPresentacion, 470,420,100, 70);

    public static BeautyErrorMessage errorCantidad = new BeautyErrorMessage("Ingresa una cantidad numerica");
    public static BeautyErrorMessage errorProducto= new BeautyErrorMessage("Selecciona un producto");
    public static BeautyErrorMessage errorPresentacion = new BeautyErrorMessage("Selecciona un tipo de presentacion");
    public static BeautyErrorMessage pantallaOK = new BeautyErrorMessage("Factura generada");

    //Variables
    String opcionBusqueda = null;
    int folioVenta = 0;
    String formaPago = null;
    String fechaAlta = null;
    String nombreCliente = null;
    String lugarExpedicion = null;
    String productos = null;
    String servicios = null;
    double subtotal = 0;
    double iva = 0;
    double total = 0;

    int idProducto = 0;
    double precio = 0;


    public ImprimirVista() {
        componentes();
        this.setLayout(null);
        this.setBounds(0, 0, 750, 625);
        this.setVisible(true);
    }

    private void componentes() {
        txtBuscar = new BeautyTextField("Buscar...", 600, 0, 150, 30);
        btnNuevo = new BeautyImageButton(establecerIcono("Nuevo", 20, 20), 620, 130, 20, 20);
        btnEditar = new BeautyImageButton(establecerIcono("Editar", 20, 20), 660, 130, 20, 20);
        btnOpciones = new BeautyImageButton(establecerIcono("LlegadaMercancia", 20, 20), 700, 130, 20, 20);
        btnFactura = new BeautyImageButton(establecerIcono("Factura", 20, 20), 740, 130, 20, 20);
        btnImprimir = new BeautyBlackButton("Imprimir", 620, 550, 100, 30);

        txtFolio = new BeautyTextField("Folio", 20 ,280, 200, 20);
        txtFechaAlta = new BeautyTextField("Fecha alta",20, 370, 200, 20);

        txtFolio.setEnabled(false);
        txtFechaAlta.setEnabled(false);
        btnImprimir.setEnabled(false);

        txtBuscar.addFocusListener(new ImprimirVista.TXTBuscarFocusAdapter());
        txtBuscar.addKeyListener(new ImprimirVista.TXTBuscarKeyAdapter());
        listaBuscador.addMouseListener(new ImprimirVista.ListaBuscarMouseAdapter());
        btnImprimir.addActionListener(new ImprimirVista.BotonImprimir());

        this.add(txtBuscar);
        //this.add(btnNuevo);
        //this.add(btnEditar);
        //this.add(btnOpciones);
        //this.add(btnFactura);
        this.add(btnImprimir);
        this.add(txtFolio);
        this.add(txtFechaAlta);
    }


    private class TXTBuscarFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollBuscador.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollBuscador.setVisible(false);
        }
    }

    private class TXTBuscarKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtBuscar.getText() + tecla;
            buscarVenta(cadena);
        }
    }

    private class ListaBuscarMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            opcionBusqueda = listaBuscador.getSelectedValue().toString();
            establecerDatos(opcionBusqueda);
            scrollBuscador.setVisible(false);
            txtBuscar.setText("");
            btnImprimir.setEnabled(true);
        }
    }

    private class TXTNombreProductoFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollProducto.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollProducto.setVisible(false);
        }
    }




    private class ComboPresentacionFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollPresentacion.setVisible(true);
        }

        @Override
        public  void focusLost(FocusEvent e) {
            scrollPresentacion.setVisible(false);
        }
    }


    private class BotonImprimir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Document document = new Document(PageSize.A4, 36, 36, 10, 10);
                PdfWriter.getInstance(document, new FileOutputStream("src/PDF/folio_" + folioVenta + ".pdf"));
                document.open();
                //icono
                com.itextpdf.text.Image image = Image.getInstance("src/Iconos/icn_logo.png");
                image.scaleAbsolute(40, 100);
                image.setAlignment(Element.ALIGN_CENTER);
                document.add(image);
                //titulo
                Paragraph header = new Paragraph();
                Chunk chunkHeader = new Chunk();
                header.setAlignment(Element.ALIGN_CENTER);
                chunkHeader.append("Clinica control  \n  \n  \n  \n  \n  ");
                header.add(chunkHeader);
                document.add(header);

                //Informacion
                Paragraph informacion = new Paragraph();
                Chunk chunkInformacion = new Chunk();
                informacion.setAlignment(Element.ALIGN_LEFT);
                chunkInformacion.append("\nFolio#" + folioVenta + "\n\n\nForma pago: " + formaPago + "\n\n\nFecha alta: " + fechaAlta + "\n\n\nCliente: " + nombreCliente + "\n\n\nLugar expedición: " + lugarExpedicion + "\n\n\nProductos comprados: " + productos + "\n\n\nServicios: " + servicios + "\n\n\nSubtotal: " + subtotal + "\n\n\nIVA: " + iva + "\n\n\nTotal: " + total);
                informacion.add(chunkInformacion);
                document.add(informacion);

                //Footer
                Paragraph footer = new Paragraph();
                Chunk chunkFooteer = new Chunk();
                footer.setAlignment(Element.ALIGN_CENTER);
                chunkFooteer.append("\n\n\nClinica Control ® 2017");
                footer.add(chunkFooteer);
                document.add(footer);

                document.close();

                pantallaOK.setVisible(true);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     *
     * Métodos
     */


    private  void buscarVenta (String cadena) {
        dlmBuscador.removeAllElements();
        listaBuscador.setVisible(true);
        String sql = "SELECT folio FROM Venta WHERE folio LIKE ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, cadena + "%");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                resultados++;
                String folio = rs.getObject("folio").toString();
                //boolean activo = rs.getBoolean("activo");
                dlmBuscador.addElement(folio);
            }
        } catch (SQLException sqlException) {

        }
    }

    private  void establecerDatos (String opcionBusqueda) {
        String sql = "SELECT * FROM Venta WHERE folio = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                this.folioVenta = Integer.parseInt(rs.getObject("folio").toString());
                this.formaPago = rs.getString("forma_pago");
                this.fechaAlta = rs.getString("fecha_alta");
                this.nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno");
                this.lugarExpedicion = rs.getString("municipio") + ", " + rs.getString("colonia") + ", " + rs.getString("calle_numero");

                String folio = rs.getObject("folio").toString();
                buscarProductos(folio);
                buscarServicios(folio);
                buscarInfo(folio);

                String fechaAlta = rs.getString("fecha_alta");
                txtFolio.setText(folio);
                txtFolio.setEnabled(false);
                txtFechaAlta.setText(fechaAlta);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    private void buscarProductos (String folio) {
        String sql = "SELECT * FROM VentaProducto WHERE folio_venta = ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, folio);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                this.productos = this.productos + "\n" + "Producto#" + rs.getString("id_producto") + " Cantidad comprada: " + rs.getInt("cantidad");
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    private void buscarServicios(String folio) {
        String sql = "SELECT * FROM VentaServicio WHERE folio_venta = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, folio);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                this.servicios = this.servicios + "\n" + "Servicio: " + rs.getString("servicio") + "  Costo: " + rs.getDouble("precio");
            }
        } catch (SQLException sqlException) {

        }
    }

    private void buscarInfo(String folio) {
        String sql = "SELECT * FROM VentaInfo WHERE folio_venta = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, folio);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                this.subtotal = rs.getDouble("subtotal");
                this.iva = rs.getDouble("iva");
                this.total = rs.getDouble("total");
            }
        } catch (SQLException sqlException) {

        }
    }

    private void obtenerIDProducto(String nombreProducto, String presentacionProducto) {
        String sql = "SELECT id, precio FROM Producto WHERE nombre = ? and presentacion = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, nombreProducto);
            ps.setString(2, presentacionProducto);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                idProducto = rs.getInt("id");
                precio = rs.getDouble("precio");
            }
        } catch (SQLException sqlException) {

        }
    }

    private boolean datosCompletos(String folio, String nombreProducto, String presentacion) {
        if (!folio.equals("Folio")) {
            if (!nombreProducto.equals("Producto")) {
                if (!presentacion.equals("Presentación")) {
                    return true;
                } else {
                    errorPresentacion.setVisible(true);
                    return false;
                }
            } else {
                errorProducto.setVisible(true);
                return false;
            }
        } else {
            System.err.println("2345234");
            return false;
        }
    }


    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        f.add(scrollBuscador);
        f.add(scrollProducto);
        f.add(scrollPresentacion);
        f.add(new ImprimirVista());
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
