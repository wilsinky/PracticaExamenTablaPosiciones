package practica.presentation.tablaPosiciones;

import practica.Application;
import practica.logic.Equipo;
import practica.logic.Partido;


import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class View implements PropertyChangeListener {
    private JComboBox<Equipo> equipoCasa;
    private JButton guardarButton;
    private JTable equipos;
    private JTable partidosDelEquipo;
    private JComboBox<Equipo> equipoVisita;
    private JComboBox<Integer> golesCasa;
    private JComboBox<Integer> golesVisita;
    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public View(){

        inicializarCombosGoles();

        guardarButton.addActionListener(e -> {
            if (validatePartido()) {
                Partido partido = takePartido();
                try {
                    controller.createPartido(partido);
                    JOptionPane.showMessageDialog(panel, "Partido creado correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                    controller.refresh();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        equipos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = equipos.getSelectedRow();
                if (selectedRow != -1) {
                    EquiposTableModel modelTabla = (EquiposTableModel) equipos.getModel();
                    Equipo seleccionado = modelTabla.getRowAt(selectedRow);

                    controller.setCurrentEquipo(seleccionado);

                }
            }
        });

//        partidosDelEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent e) {
//                if (e.getClickCount() == 1 && partidosDelEquipo.getSelectedRow() != -1) {
//                    int row = partidosDelEquipo.getSelectedRow();
//                    PartidosTableModel modelTabla = (PartidosTableModel) partidosDelEquipo.getModel();
//                    Partido partidoSeleccionado = modelTabla.getRowAt(row);
//
//                    controller.setCurrentPartido(partidoSeleccionado);
//                }
//            }
//        });
    }

    Controller controller;
    Model model;



    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void cargarCombosEquipos(java.util.List<Equipo> equiposList) {

        List<Equipo> equiposParaCombo = new ArrayList<>(equiposList);
        equiposParaCombo.sort(java.util.Comparator.comparing(Equipo::getNombre));

        DefaultComboBoxModel<Equipo> modelCasa = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Equipo> modelVisita = new DefaultComboBoxModel<>();

        for (Equipo e : equiposParaCombo) {
            modelCasa.addElement(e);
            modelVisita.addElement(e);
        }

        equipoCasa.setModel(modelCasa);
        equipoVisita.setModel(modelVisita);
    }

    private void inicializarCombosGoles() {
        DefaultComboBoxModel<Integer> modeloGC = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> modeloGV = new DefaultComboBoxModel<>();
        for (int i = 0; i <= 10; i++) {
            modeloGC.addElement(i);
            modeloGV.addElement(i);
        }
        golesCasa.setModel(modeloGC);
        golesVisita.setModel(modeloGV);
    }





    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.EQUIPOS:
                int[] colsEquipos = {EquiposTableModel.NOMBRE,EquiposTableModel.PG, EquiposTableModel.PE, EquiposTableModel.PP, EquiposTableModel.PUNTOS};
                equipos.setModel(new EquiposTableModel(colsEquipos,model.getEquipos()));
                cargarCombosEquipos(model.getEquipos());
                break;
            case Model.PARTIDOS:
                int[] colsPartidos = {PartidosTableModel.CASA,PartidosTableModel.GC, PartidosTableModel.VISITA, PartidosTableModel.GV};
                partidosDelEquipo.setModel(new PartidosTableModel(colsPartidos,model.getPartidos()));
                break;
        }
        this.panel.revalidate();
    }

    public Partido takePartido() {
        Partido p = new Partido();

        p.setEquipoCasa((Equipo) equipoCasa.getSelectedItem());
        p.setEquipoVisita((Equipo) equipoVisita.getSelectedItem());
        p.setGolesCasa((Integer) golesCasa.getSelectedItem());
        p.setGolesVisita((Integer) golesVisita.getSelectedItem());

        return p;
    }

    private boolean validatePartido() {
        boolean valid = true;

        Object casa = equipoCasa.getSelectedItem();
        Object visita = equipoVisita.getSelectedItem();

        // Validar equipo casa
        if (casa == null) {
            valid = false;
            equipoCasa.setBackground(Application.BACKGROUND_ERROR);
            equipoCasa.setToolTipText("Debe seleccionar el equipo de casa");
        } else {
            equipoCasa.setBackground(null);
            equipoCasa.setToolTipText(null);
        }

        // Validar equipo visita
        if (visita == null) {
            valid = false;
            equipoVisita.setBackground(Application.BACKGROUND_ERROR);
            equipoVisita.setToolTipText("Debe seleccionar el equipo de visita");
        } else {
            equipoVisita.setBackground(null);
            equipoVisita.setToolTipText(null);
        }

        // Validar que los equipos sean diferentes
        if (casa != null && visita != null && casa.equals(visita)) {
            valid = false;
            equipoCasa.setBackground(Application.BACKGROUND_ERROR);
            equipoVisita.setBackground(Application.BACKGROUND_ERROR);
            equipoCasa.setToolTipText("Los equipos deben ser diferentes");
            equipoVisita.setToolTipText("Los equipos deben ser diferentes");
        }

        // Validar goles casa
        Object gCasa = golesCasa.getSelectedItem();
        if (gCasa == null) {
            valid = false;
            golesCasa.setBackground(Application.BACKGROUND_ERROR);
            golesCasa.setToolTipText("Debe seleccionar goles de casa");
        } else {
            golesCasa.setBackground(null);
            golesCasa.setToolTipText(null);
        }

        // Validar goles visita
        Object gVisita = golesVisita.getSelectedItem();
        if (gVisita == null) {
            valid = false;
            golesVisita.setBackground(Application.BACKGROUND_ERROR);
            golesVisita.setToolTipText("Debe seleccionar goles de visita");
        } else {
            golesVisita.setBackground(null);
            golesVisita.setToolTipText(null);
        }

        // Mostrar mensaje de error si hay campos inválidos
        if (!valid) {
            JOptionPane.showMessageDialog(panel, "Por favor complete todos los campos correctamente", "Validación", JOptionPane.WARNING_MESSAGE);
        }

        return valid;
    }
}
