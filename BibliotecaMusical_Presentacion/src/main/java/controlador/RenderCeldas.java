package controlador;

import java.awt.Component;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.MatteBorder;

public class RenderCeldas implements TableCellRenderer {
    private final TableCellRenderer oldHeaderRenderer;
    private final TableCellRenderer oldCellRenderer;
    private final Map<Integer, Integer> columnAlignments;

    public RenderCeldas(JTable table) {
        this.oldHeaderRenderer = table.getTableHeader().getDefaultRenderer();
        this.oldCellRenderer = table.getDefaultRenderer(Object.class);
        this.columnAlignments = new HashMap<>();

        // Configurar el aspecto general de la tabla
        table.setShowGrid(false);
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        
        // Aplicar render personalizado a las celdas
        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                JLabel label = (JLabel) oldCellRenderer.getTableCellRendererComponent(
                        jtable, value, isSelected, hasFocus, row, column);
                
                // Configurar la alineación
                label.setHorizontalAlignment(getAlignment(column));
                
                // Crear borde inferior blanco
                label.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
                
                // Hacer transparente el fondo si la celda no está seleccionada
                if (!isSelected) {
                    label.setBackground(new Color(0, 0, 0, 0));
                }
                
                return label;
            }
        });
        
        // Configurar el header de la tabla si es necesario
        table.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                JLabel header = (JLabel) oldHeaderRenderer.getTableCellRendererComponent(
                        jtable, value, isSelected, hasFocus, row, column);
                
                // Configurar borde inferior blanco para el header
                header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(35, 58, 68)));
                header.setHorizontalAlignment(getAlignment(column));
                
                return header;
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) oldHeaderRenderer.getTableCellRendererComponent(
                jtable, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(getAlignment(column));
        label.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
        return label;
    }

    public void setColumnAlignment(int column, int alignment) {
        columnAlignments.put(column, alignment);
    }

    protected int getAlignment(int column) {
        return columnAlignments.getOrDefault(column, SwingConstants.LEFT);
    }
}