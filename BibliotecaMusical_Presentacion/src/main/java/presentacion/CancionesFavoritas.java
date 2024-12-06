package presentacion;

import com.bmn.dto.FavoritoDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerCancionesFavoritasBO;
import com.bmn.negocio.ObtenerRestringidosBO;
import controlador.RenderCeldas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Sebastian Murrieta Verduzco -233463
 */
public class CancionesFavoritas extends javax.swing.JFrame {

// Variables de instancia
    private boolean isMenuVisible = false;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;
    private javax.swing.JPanel glassPanel;
    private List<Genero> generosRestringidos;
    private List<FavoritoDTO> favoritos;

    public CancionesFavoritas() {
        try {
            
            ObtenerCancionesFavoritasBO favrourite = BOFactory.obtenerCancionesFavoritosFactory();
            
            this.favoritos = favrourite.obtenerCancionesFavoritas(null, null);
            
            // Inicializar componentes gráficos
            initComponents();

            // Cargar tabla
            cargarTablaloadTable();
            
            // Configurar tabla para mostrar géneros restringidos
            configurarTabla();

            // Cargar los géneros restringidos
            cargarGenerosRestringidos();
            
            //cargamos el comboBox
            cargarComboBox();
            
        } catch (Exception e) {
            // Manejo de excepciones generales
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar la ventana de géneros restringidos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTablaloadTable(){
        String[] columns = {"Nombre", "Genero", "Fecha"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        
        try{
            List<FavoritoDTO> cancionesFavoritas = this.favoritos;
            
            for (FavoritoDTO favorito : cancionesFavoritas) {
                Object[] object = {
                    favorito.getNombreCancion(),
                    favorito.getGenero(),
                    favorito.getFechaAgregacion()
                };
                tableModel.addRow(object);
            }
            
            this.tablaBaneado.setModel(tableModel);
            
        }catch(Exception de){
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar las canciones: " + 
                    de.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"GÉNERO RESTRINGIDO"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        tablaBaneado.setModel(modeloTabla);
        // Configurar render personalizado
        RenderCeldas render = new RenderCeldas(tablaBaneado);
        render.setColumnAlignment(0, SwingConstants.CENTER);
        // Configurar colores y estilos
        tablaBaneado.setBackground(new Color(35, 58, 68));
        tablaBaneado.setForeground(Color.WHITE);
        tablaBaneado.setRowHeight(50);
        tablaBaneado.setSelectionBackground(new Color(58, 107, 128));
        tablaBaneado.setSelectionForeground(Color.WHITE);
        tablaBaneado.getTableHeader().setBackground(new Color(35, 58, 68));
        tablaBaneado.getTableHeader().setForeground(Color.WHITE);
    }

    private void cargarGenerosRestringidos() {
        try {
            // Usar ObtenerRestringidosBO para obtener los géneros restringidos
            ObtenerRestringidosBO obtenerRestringidos = BOFactory.obtenerRestringidosFactory();

            generosRestringidos = obtenerRestringidos.obtenerRestringidos();

            // Limpiar modelo de tabla existente
            modeloTabla.setRowCount(0);

            // Iterar sobre los géneros restringidos y agregarlos a la tabla
            for (Genero genero : generosRestringidos) {
                modeloTabla.addRow(new Object[]{genero.name()});
            }

            // Mensaje de depuración
            System.out.println("Géneros restringidos encontrados: " + generosRestringidos.size());
        } catch (BOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar géneros restringidos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método getter para obtener los géneros restringidos
    public List<Genero> getGenerosRestringidos() {
        return generosRestringidos;
    }
    
    private void cargarComboBox() {
        for (Genero genero : Genero.values()) {
            generoSinBan.addItem(genero.name());
        }
    }

    private void buscarGenero(String texto) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tablaBaneado.setRowSorter(sorter);

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(
                "(?i)" + texto, 0
        ); // Buscar en la columna de géneros
        sorter.setRowFilter(filter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuDesplegablePanel = new javax.swing.JPanel();
        albumLb = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        albumFavLb = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        artistaLb = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        artistasFavLb = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        salir = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        perfilLb = new javax.swing.JLabel();
        artistaLb1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        artistaLb2 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        Fondo = new javax.swing.JPanel();
        panelRound1 = new controlador.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        menuBtn = new javax.swing.JButton();
        panelRound5 = new controlador.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBaneado = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        panelInformacionAlbum = new controlador.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        generoSinBan = new javax.swing.JComboBox<>();
        quitarBaneoBtn = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnLimpiar = new javax.swing.JButton();

        menuDesplegablePanel.setBackground(new java.awt.Color(58, 107, 128));
        menuDesplegablePanel.setPreferredSize(new java.awt.Dimension(290, 660));

        albumLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        albumLb.setForeground(new java.awt.Color(255, 255, 255));
        albumLb.setText("Album");
        albumLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumLbMouseClicked(evt);
            }
        });

        albumFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        albumFavLb.setForeground(new java.awt.Color(255, 255, 255));
        albumFavLb.setText("Album Favoritos");
        albumFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumFavLbMouseClicked(evt);
            }
        });

        artistaLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        artistaLb.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb.setText("Artista");
        artistaLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLbMouseClicked(evt);
            }
        });

        artistasFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        artistasFavLb.setForeground(new java.awt.Color(255, 255, 255));
        artistasFavLb.setText("Artistas Favoritos");
        artistasFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistasFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistasFavLbMouseClicked(evt);
            }
        });

        salir.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("SALIR");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
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

        artistaLb1.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        artistaLb1.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb1.setText("Canciones Favoritas");
        artistaLb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb1.setEnabled(false);
        artistaLb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLb1MouseClicked(evt);
            }
        });

        artistaLb2.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        artistaLb2.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb2.setText("Baneados");
        artistaLb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLb2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuDesplegablePanelLayout = new javax.swing.GroupLayout(menuDesplegablePanel);
        menuDesplegablePanel.setLayout(menuDesplegablePanelLayout);
        menuDesplegablePanelLayout.setHorizontalGroup(
            menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(albumLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(albumFavLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(artistaLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(artistasFavLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                        .addComponent(salir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(artistaLb1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(jSeparator6)
                    .addComponent(artistaLb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator7))
                .addContainerGap())
            .addGroup(menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(perfilLb, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        menuDesplegablePanelLayout.setVerticalGroup(
            menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(albumLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(albumFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistasFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir)
                .addGap(8, 8, 8))
            .addGroup(menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDesplegablePanelLayout.createSequentialGroup()
                    .addContainerGap(582, Short.MAX_VALUE)
                    .addComponent(perfilLb)
                    .addGap(52, 52, 52)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(24, 40, 54));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(58, 107, 128));

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Canciones Favoritas");

        menuBtn.setBackground(new java.awt.Color(102, 102, 255));
        menuBtn.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        menuBtn.setText("<");
        menuBtn.setBorder(null);
        menuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jLabel1)
                .addContainerGap(698, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Fondo.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, -5, 1290, 80));

        panelRound5.setBackground(new java.awt.Color(35, 58, 68));
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);

        tablaBaneado.setBackground(new java.awt.Color(35, 58, 68));
        tablaBaneado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Genero", "Fecha", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBaneado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaBaneado.setGridColor(new java.awt.Color(35, 58, 68));
        tablaBaneado.setSelectionBackground(new java.awt.Color(35, 58, 68));
        jScrollPane1.setViewportView(tablaBaneado);
        if (tablaBaneado.getColumnModel().getColumnCount() > 0) {
            tablaBaneado.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Canciones Favoritas");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(76, 76, 76))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Fondo.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 580, 330));

        panelInformacionAlbum.setBackground(new java.awt.Color(35, 58, 68));
        panelInformacionAlbum.setRoundBottomLeft(30);
        panelInformacionAlbum.setRoundBottomRight(30);
        panelInformacionAlbum.setRoundTopLeft(30);
        panelInformacionAlbum.setRoundTopRight(30);

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Filtros");

        generoSinBan.setBackground(new java.awt.Color(58, 107, 128));
        generoSinBan.setEditable(true);
        generoSinBan.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        generoSinBan.setForeground(new java.awt.Color(255, 255, 255));
        generoSinBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generoSinBanActionPerformed(evt);
            }
        });

        quitarBaneoBtn.setBackground(new java.awt.Color(58, 107, 128));
        quitarBaneoBtn.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        quitarBaneoBtn.setForeground(new java.awt.Color(255, 255, 255));
        quitarBaneoBtn.setText("Buscar");

        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout panelInformacionAlbumLayout = new javax.swing.GroupLayout(panelInformacionAlbum);
        panelInformacionAlbum.setLayout(panelInformacionAlbumLayout);
        panelInformacionAlbumLayout.setHorizontalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(generoSinBan, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionAlbumLayout.createSequentialGroup()
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quitarBaneoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(213, 213, 213))
        );
        panelInformacionAlbumLayout.setVerticalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnLimpiar)))
                .addGap(35, 35, 35)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generoSinBan, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addComponent(quitarBaneoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        Fondo.add(panelInformacionAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 580, -1));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void perfilLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfilLbMouseClicked

        dispose();
        new ActualizarUsuario().setVisible(true);
    }//GEN-LAST:event_perfilLbMouseClicked

    private void artistasFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistasFavLbMouseClicked
        dispose();
        new ArtistaFavorito().setVisible(true);
    }//GEN-LAST:event_artistasFavLbMouseClicked

    private void menuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtnActionPerformed
        Principal principal = new Principal(); 
        principal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuBtnActionPerformed

    private void artistaLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLbMouseClicked
        dispose();
        new Artista().setVisible(true);
    }//GEN-LAST:event_artistaLbMouseClicked

    private void albumFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumFavLbMouseClicked

        dispose();
        new AlbumFavorito().setVisible(true);
    }//GEN-LAST:event_albumFavLbMouseClicked

    private void albumLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumLbMouseClicked
        dispose();
        new Principal().setVisible(true);
    }//GEN-LAST:event_albumLbMouseClicked

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Properly dispose of the window instead of System.exit(0)
            System.exit(0);
        }
    }//GEN-LAST:event_salirMouseClicked

    private void artistaLb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLb1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_artistaLb1MouseClicked

    private void artistaLb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLb2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_artistaLb2MouseClicked

    private void generoSinBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generoSinBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generoSinBanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JLabel albumFavLb;
    private javax.swing.JLabel albumLb;
    private javax.swing.JLabel artistaLb;
    private javax.swing.JLabel artistaLb1;
    private javax.swing.JLabel artistaLb2;
    private javax.swing.JLabel artistasFavLb;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> generoSinBan;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton menuBtn;
    private javax.swing.JPanel menuDesplegablePanel;
    private controlador.PanelRound panelInformacionAlbum;
    private controlador.PanelRound panelRound1;
    private controlador.PanelRound panelRound5;
    private javax.swing.JLabel perfilLb;
    private javax.swing.JButton quitarBaneoBtn;
    private javax.swing.JLabel salir;
    private javax.swing.JTable tablaBaneado;
    // End of variables declaration//GEN-END:variables
}
