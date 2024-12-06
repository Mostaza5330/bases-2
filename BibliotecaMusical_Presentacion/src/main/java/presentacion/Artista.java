package presentacion;

import java.awt.Graphics2D;
import com.bmd.entities.Usuario;
import com.bmn.dto.ArtistaVistaDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.interfaces.IObtenerArtistasFavoritosBO;
import com.bmn.negocio.AgregarFavoritoBO;
import controlador.RenderCeldas;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Sebastian Murrieta Verduzco -233463
 */
public class Artista extends javax.swing.JFrame {

    private Usuario usuarioActual;
    private List<ArtistaVistaDTO> artistas;
    private AgregarFavoritoBO agregarFavoritoBO;
    private IObtenerArtistasFavoritosBO obtenerFavoritosBO;

    public Artista() {
    try {
        this.agregarFavoritoBO = BOFactory.agregarFavoritoFactory();
        this.obtenerFavoritosBO = BOFactory.obtenerArtistasFavoritosFactory();
        initComponents();
        
        // Debug: Print out enum values
        System.out.println("Genero enum values:");
        for (Genero genero : Genero.values()) {
            System.out.println(genero);
        }
        
        configurarTabla();
        configurarTablaCanciones();
        cargarComboBox();

        generoFiltro.addItem("Todos");

        // Rest of the constructor remains the same
    } catch (Exception e) {
        e.printStackTrace(); // Print full stack trace
        JOptionPane.showMessageDialog(this, 
            "Error al inicializar: " + e.getMessage() + 
            "\nCausa: " + (e.getCause() != null ? e.getCause().getMessage() : "No cause"), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"IMAGEN", "NOMBRE DEL ARTISTA"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : Object.class;
            }
        };
        tablaArtista.setModel(modelo);
        tablaArtista.getTableHeader().setReorderingAllowed(false);

        RenderCeldas render = new RenderCeldas(tablaArtista);
        render.setColumnAlignment(0, SwingConstants.CENTER);
        render.setColumnAlignment(1, SwingConstants.CENTER);

        aplicarEstilosTabla(tablaArtista);
        aplicarEstilosTabla(ListaDeCancionesArtista);
        aplicarEstilosTabla(ListaDeIntegrantesTabla);

        configurarSeleccionTabla();
        cargarDatosDeLaBaseDeDatos(modelo);
    }

    private void configurarTablaCanciones() {
        DefaultTableModel modeloCanciones = new DefaultTableModel(
                new String[]{"Canción", "Duración"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ListaDeCancionesArtista.setModel(modeloCanciones);

        DefaultTableModel modeloIntegrantes = new DefaultTableModel(
                new String[]{"Integrante", "Rol"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ListaDeIntegrantesTabla.setModel(modeloIntegrantes);
    }

    private void aplicarEstilosTabla(JTable tabla) {
        tabla.setBackground(new Color(35, 58, 68));
        tabla.setForeground(Color.WHITE);
        tabla.setRowHeight(50);
        tabla.setSelectionBackground(new Color(58, 107, 128));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.getTableHeader().setBackground(new Color(35, 58, 68));
        tabla.getTableHeader().setForeground(Color.WHITE);
    }

    private void configurarSeleccionTabla() {
        tablaArtista.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaArtista.getSelectedRow();
                if (selectedRow >= 0) {
                    try {
                        int modelRow = tablaArtista.convertRowIndexToModel(selectedRow);
                        ArtistaVistaDTO artistaVista = this.artistas.get(modelRow);
                        actualizarPanelInformacion(artistaVista);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                "Error al cargar detalles del artista: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void actualizarPanelInformacion(ArtistaVistaDTO artista) {
        if (artista == null) {
            imagenArtista.setIcon(null);
            nombreDelArtistaTxt.setText("");
            generoTxt.setText("Sin género");
            tipoArtistaTxt.setText("");

            DefaultTableModel modeloCanciones = (DefaultTableModel) ListaDeCancionesArtista.getModel();
            modeloCanciones.setRowCount(0);

            DefaultTableModel modeloIntegrantes = (DefaultTableModel) ListaDeIntegrantesTabla.getModel();
            modeloIntegrantes.setRowCount(0);
            return;
        }

        // Rest of the existing implementation remains the same
        ImageIcon artistaIcon = cargarImagen(artista.getImagen());
        if (artistaIcon != null) {
            Image scaled = artistaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imagenArtista.setIcon(new ImageIcon(scaled));
        } else {
            imagenArtista.setIcon(null);
        }

        nombreDelArtistaTxt.setText(artista.getNombre() != null ? artista.getNombre() : "");

        // Aquí se pueden agregar más detalles del artista cuando estén disponibles
        generoTxt.setText(""); // Género del artista si está disponible
        tipoArtistaTxt.setText(""); // Tipo de artista si está disponible

        // Actualizar tabla de canciones del artista cuando esté disponible
        DefaultTableModel modeloCanciones = (DefaultTableModel) ListaDeCancionesArtista.getModel();
        modeloCanciones.setRowCount(0);

        // Actualizar tabla de integrantes cuando esté disponible
        DefaultTableModel modeloIntegrantes = (DefaultTableModel) ListaDeIntegrantesTabla.getModel();
        modeloIntegrantes.setRowCount(0);
    }

    private void cargarComboBox() {
        // Always add "Todos" first
        generoFiltro.addItem("Todos");

        // Safely add non-null Genero values
        for (Genero genero : Genero.values()) {
            generoFiltro.addItem(genero.name());
        }
    }

    private ImageIcon cargarImagen(String nombreImagen) {
        try {
            if (nombreImagen == null || nombreImagen.trim().isEmpty()) {
                return crearImagenPorDefecto();
            }

            String rutaCompleta = "src/fotosArtista/" + nombreImagen;
            File archivoImagen = new File(rutaCompleta);

            if (!archivoImagen.exists()) {
                return crearImagenPorDefecto();
            }

            ImageIcon originalIcon = new ImageIcon(rutaCompleta);
            if (originalIcon.getIconWidth() <= 0) {
                return crearImagenPorDefecto();
            }

            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            return crearImagenPorDefecto();
        }
    }

    private ImageIcon crearImagenPorDefecto() {
        BufferedImage placeholderImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = placeholderImage.createGraphics();

        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, 49, 49);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString("No Image", 5, 30);
        g2d.dispose();

        return new ImageIcon(placeholderImage);
    }

    private void cargarDatosDeLaBaseDeDatos(DefaultTableModel modelo) {
        try {
            List<ArtistaVistaDTO> artistas = obtenerFavoritosBO.obtenerArtistasFavoritos(null , null);
            this.artistas = artistas;
            actualizarTabla(artistas);
        } catch (BOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar artistas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarArtistas() {
        try {
            String generoSeleccionado = (String) generoFiltro.getSelectedItem();
            LocalDate fechaSeleccionada = fechaFiltro.getDate() != null
                    ? fechaFiltro.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    : null;

            Genero genero = "Todos".equals(generoSeleccionado) ? null : Genero.valueOf(generoSeleccionado);
            List<ArtistaVistaDTO> artistasFiltrados = obtenerFavoritosBO.obtenerArtistasFavoritos(genero, fechaSeleccionada);

            actualizarTabla(artistasFiltrados);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al filtrar artistas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla(List<ArtistaVistaDTO> artistasFiltrados) {
        this.artistas = artistasFiltrados;
        DefaultTableModel modelo = (DefaultTableModel) tablaArtista.getModel();
        modelo.setRowCount(0);

        for (ArtistaVistaDTO artista : artistasFiltrados) {
            ImageIcon imagen = cargarImagen(artista.getImagen());
            modelo.addRow(new Object[]{imagen, artista.getNombre()});
        }
    }

    private void limpiarFiltros() {
        busqueda.setText("");
        generoFiltro.setSelectedItem("Todos");
        fechaFiltro.setDate(null);
        filtrarArtistas();
    }

    private void filterTable(String searchTerm) {
        // Filtra la tabla utilizando el término de búsqueda ingresado
        DefaultTableModel model = (DefaultTableModel) tablaArtista.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tablaArtista.setRowSorter(sorter);

        // Crea un filtro para buscar en las columnas "Nombre del álbum" y "Artista"
        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(
                "(?i)" + searchTerm, 1, 2
        );
        sorter.setRowFilter(filter); // Aplica el filtro
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        menu = new controlador.PanelRound();
        albumFavLb = new javax.swing.JLabel();
        artistaLb = new javax.swing.JLabel();
        artistasFavLb = new javax.swing.JLabel();
        perfilLb = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        salir = new javax.swing.JLabel();
        albumLb = new javax.swing.JLabel();
        artistaLb1 = new javax.swing.JLabel();
        baneadoLb = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        panelInformacionAlbum = new controlador.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ImagenArtistaPanel = new javax.swing.JPanel();
        imagenArtista = new javax.swing.JLabel();
        nombreDelArtistaTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Canciones = new javax.swing.JScrollPane();
        ListaDeIntegrantesTabla = new javax.swing.JTable();
        tipoArtistaTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        generoTxt = new javax.swing.JTextField();
        agregarFavoritoAlbumBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Canciones1 = new javax.swing.JScrollPane();
        ListaDeCancionesArtista = new javax.swing.JTable();
        panelRound5 = new controlador.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArtista = new javax.swing.JTable();
        generoFiltro = new javax.swing.JComboBox<>();
        panelRound3 = new controlador.PanelRound();
        busqueda = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        fechaFiltro = new com.toedter.calendar.JDateChooser();
        limpiarTodosFiltrosBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(24, 40, 54));
        Fondo.setMinimumSize(new java.awt.Dimension(1280, 720));
        Fondo.setPreferredSize(new java.awt.Dimension(1280, 720));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setBackground(new java.awt.Color(58, 107, 128));

        albumFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        albumFavLb.setForeground(new java.awt.Color(255, 255, 255));
        albumFavLb.setText("Album Favoritos");
        albumFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumFavLbMouseClicked(evt);
            }
        });

        artistaLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistaLb.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb.setText("Artista");
        artistaLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb.setEnabled(false);
        artistaLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLbMouseClicked(evt);
            }
        });

        artistasFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistasFavLb.setForeground(new java.awt.Color(255, 255, 255));
        artistasFavLb.setText("Artistas Favoritos");
        artistasFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistasFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistasFavLbMouseClicked(evt);
            }
        });

        perfilLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        perfilLb.setForeground(new java.awt.Color(255, 255, 255));
        perfilLb.setText("Mi perfil");
        perfilLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perfilLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfilLbMouseClicked(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator8.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        salir.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("SALIR");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
            }
        });

        albumLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        albumLb.setForeground(new java.awt.Color(255, 255, 255));
        albumLb.setText("      Album");
        albumLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumLbMouseClicked(evt);
            }
        });

        artistaLb1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistaLb1.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb1.setText("Canciones Favoritas");
        artistaLb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLb1MouseClicked(evt);
            }
        });

        baneadoLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        baneadoLb.setForeground(new java.awt.Color(255, 255, 255));
        baneadoLb.setText("Baneados");
        baneadoLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        baneadoLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baneadoLbMouseClicked(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(albumLb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(albumFavLb, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistasFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baneadoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perfilLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(salir)))
                .addGap(111, 111, 111))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(jSeparator7)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(baneadoLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(artistaLb1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(menuLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(perfilLb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salir)))
                        .addGap(84, 84, 84))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(artistaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumFavLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(artistasFavLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        Fondo.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, -5, 1300, 70));

        panelInformacionAlbum.setBackground(new java.awt.Color(35, 58, 68));
        panelInformacionAlbum.setRoundBottomLeft(30);
        panelInformacionAlbum.setRoundBottomRight(30);
        panelInformacionAlbum.setRoundTopLeft(30);
        panelInformacionAlbum.setRoundTopRight(30);

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo:");

        jPanel1.setBackground(new java.awt.Color(26, 26, 26));

        ImagenArtistaPanel.setBackground(new java.awt.Color(153, 255, 255));
        ImagenArtistaPanel.setPreferredSize(new java.awt.Dimension(119, 119));

        javax.swing.GroupLayout ImagenArtistaPanelLayout = new javax.swing.GroupLayout(ImagenArtistaPanel);
        ImagenArtistaPanel.setLayout(ImagenArtistaPanelLayout);
        ImagenArtistaPanelLayout.setHorizontalGroup(
            ImagenArtistaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagenArtistaPanelLayout.createSequentialGroup()
                .addComponent(imagenArtista)
                .addGap(0, 152, Short.MAX_VALUE))
        );
        ImagenArtistaPanelLayout.setVerticalGroup(
            ImagenArtistaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagenArtistaPanelLayout.createSequentialGroup()
                .addComponent(imagenArtista)
                .addGap(0, 152, Short.MAX_VALUE))
        );

        nombreDelArtistaTxt.setBackground(new java.awt.Color(26, 26, 26));
        nombreDelArtistaTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nombreDelArtistaTxt.setForeground(new java.awt.Color(255, 255, 255));
        nombreDelArtistaTxt.setText("Nombre Del Album");
        nombreDelArtistaTxt.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(nombreDelArtistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ImagenArtistaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(ImagenArtistaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreDelArtistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Informacion");

        jPanel3.setBackground(new java.awt.Color(35, 58, 68));

        Canciones.setBackground(new java.awt.Color(35, 58, 68));

        ListaDeIntegrantesTabla.setBackground(new java.awt.Color(35, 58, 68));
        ListaDeIntegrantesTabla.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        ListaDeIntegrantesTabla.setForeground(new java.awt.Color(255, 255, 255));
        ListaDeIntegrantesTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Canciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Canciones.setViewportView(ListaDeIntegrantesTabla);
        if (ListaDeIntegrantesTabla.getColumnModel().getColumnCount() > 0) {
            ListaDeIntegrantesTabla.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Canciones, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Canciones, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tipoArtistaTxt.setEditable(false);
        tipoArtistaTxt.setBackground(new java.awt.Color(35, 58, 68));
        tipoArtistaTxt.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        tipoArtistaTxt.setForeground(new java.awt.Color(255, 255, 255));
        tipoArtistaTxt.setBorder(null);

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genero:");

        generoTxt.setEditable(false);
        generoTxt.setBackground(new java.awt.Color(35, 58, 68));
        generoTxt.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        generoTxt.setForeground(new java.awt.Color(255, 255, 255));
        generoTxt.setBorder(null);

        agregarFavoritoAlbumBtn.setBackground(new java.awt.Color(58, 107, 128));
        agregarFavoritoAlbumBtn.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        agregarFavoritoAlbumBtn.setToolTipText("");

        jPanel4.setBackground(new java.awt.Color(35, 58, 68));

        Canciones1.setBackground(new java.awt.Color(35, 58, 68));

        ListaDeCancionesArtista.setBackground(new java.awt.Color(35, 58, 68));
        ListaDeCancionesArtista.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        ListaDeCancionesArtista.setForeground(new java.awt.Color(255, 255, 255));
        ListaDeCancionesArtista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Canciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Canciones1.setViewportView(ListaDeCancionesArtista);
        if (ListaDeCancionesArtista.getColumnModel().getColumnCount() > 0) {
            ListaDeCancionesArtista.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Canciones1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(Canciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelInformacionAlbumLayout = new javax.swing.GroupLayout(panelInformacionAlbum);
        panelInformacionAlbum.setLayout(panelInformacionAlbumLayout);
        panelInformacionAlbumLayout.setHorizontalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agregarFavoritoAlbumBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoArtistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(generoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionAlbumLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(149, 149, 149)))
        );
        panelInformacionAlbumLayout.setVerticalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(agregarFavoritoAlbumBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addComponent(tipoArtistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
            .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(410, Short.MAX_VALUE)))
        );

        Fondo.add(panelInformacionAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 590, 490));

        panelRound5.setBackground(new java.awt.Color(35, 58, 68));
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);

        tablaArtista.setBackground(new java.awt.Color(35, 58, 68));
        tablaArtista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Imagen", "Nombre", "Artista"
            }
        ));
        tablaArtista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaArtista.setGridColor(new java.awt.Color(35, 58, 68));
        tablaArtista.setSelectionBackground(new java.awt.Color(35, 58, 68));
        jScrollPane1.setViewportView(tablaArtista);
        if (tablaArtista.getColumnModel().getColumnCount() > 0) {
            tablaArtista.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );

        Fondo.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 510, 610));

        generoFiltro.setBackground(new java.awt.Color(35, 58, 68));
        generoFiltro.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        generoFiltro.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(generoFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 136, 34));

        panelRound3.setBackground(new java.awt.Color(35, 58, 68));
        panelRound3.setCursorHandEnabled(true);
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        busqueda.setBackground(new java.awt.Color(35, 58, 68));
        busqueda.setForeground(new java.awt.Color(255, 255, 255));
        busqueda.setBorder(null);
        busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaActionPerformed(evt);
            }
        });

        buscarBtn.setBackground(new java.awt.Color(35, 58, 68));
        buscarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/lupa.png"))); // NOI18N
        buscarBtn.setBorder(null);
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(busqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        Fondo.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, -1, -1));
        Fondo.add(fechaFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 140, 30));

        limpiarTodosFiltrosBtn.setBackground(new java.awt.Color(58, 107, 128));
        limpiarTodosFiltrosBtn.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        limpiarTodosFiltrosBtn.setText("Limpiar Filtros");
        limpiarTodosFiltrosBtn.setToolTipText("");
        Fondo.add(limpiarTodosFiltrosBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, -1, 30));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaActionPerformed
        String searchTerm = busqueda.getText().trim();
        filterTable(searchTerm);
    }//GEN-LAST:event_busquedaActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        busquedaActionPerformed(evt);
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void perfilLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfilLbMouseClicked
        try {
            ActualizarUsuario actualizarUsuario = new ActualizarUsuario();
            actualizarUsuario.setLocationRelativeTo(this); // Center the new window
            actualizarUsuario.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al abrir la ventana de actualización: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_perfilLbMouseClicked

    private void artistasFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistasFavLbMouseClicked
        dispose();
        new ArtistaFavorito().setVisible(true);
    }//GEN-LAST:event_artistasFavLbMouseClicked

    private void artistaLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLbMouseClicked
        dispose();
        new Artista().setVisible(true);
    }//GEN-LAST:event_artistaLbMouseClicked

    private void albumFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumFavLbMouseClicked
        dispose();
        new AlbumFavorito().setVisible(true);
    }//GEN-LAST:event_albumFavLbMouseClicked

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new Inicio().setVisible(true);
        }
    }//GEN-LAST:event_salirMouseClicked

    private void artistaLb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLb1MouseClicked
        CancionesFavoritas favoritas = new CancionesFavoritas();
        favoritas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_artistaLb1MouseClicked

    private void baneadoLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baneadoLbMouseClicked
        dispose();
        new Baneado().setVisible(true);
    }//GEN-LAST:event_baneadoLbMouseClicked

    private void albumLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumLbMouseClicked
        dispose();
        new Artista().setVisible(true);
    }//GEN-LAST:event_albumLbMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Canciones;
    private javax.swing.JScrollPane Canciones1;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel ImagenArtistaPanel;
    private javax.swing.JTable ListaDeCancionesArtista;
    private javax.swing.JTable ListaDeIntegrantesTabla;
    private javax.swing.JButton agregarFavoritoAlbumBtn;
    private javax.swing.JLabel albumFavLb;
    private javax.swing.JLabel albumLb;
    private javax.swing.JLabel artistaLb;
    private javax.swing.JLabel artistaLb1;
    private javax.swing.JLabel artistasFavLb;
    private javax.swing.JLabel baneadoLb;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField busqueda;
    private com.toedter.calendar.JDateChooser fechaFiltro;
    private javax.swing.JComboBox<String> generoFiltro;
    private javax.swing.JTextField generoTxt;
    private javax.swing.JLabel imagenArtista;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton limpiarTodosFiltrosBtn;
    private controlador.PanelRound menu;
    private javax.swing.JTextField nombreDelArtistaTxt;
    private controlador.PanelRound panelInformacionAlbum;
    private controlador.PanelRound panelRound3;
    private controlador.PanelRound panelRound5;
    private javax.swing.JLabel perfilLb;
    private javax.swing.JLabel salir;
    private javax.swing.JTable tablaArtista;
    private javax.swing.JTextField tipoArtistaTxt;
    // End of variables declaration//GEN-END:variables

}
