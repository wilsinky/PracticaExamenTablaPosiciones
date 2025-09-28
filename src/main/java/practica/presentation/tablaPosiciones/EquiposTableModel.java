package practica.presentation.tablaPosiciones;

import practica.presentation.AbstractTableModel;
import practica.logic.Equipo;

import java.util.List;

public class EquiposTableModel extends AbstractTableModel<Equipo> implements javax.swing.table.TableModel {
    public EquiposTableModel(int[] cols, List<Equipo> rows) {
        super(cols, rows);
    }

    public static final int NOMBRE = 0;
    public static final int PG = 1;
    public static final int PE = 2;
    public static final int PP = 3;
    public static final int PUNTOS = 4;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[NOMBRE] = "Nombre";
        colNames[PG] = "PG";
        colNames[PE] = "PE";
        colNames[PP] = "PP";
        colNames[PUNTOS] = "Pts";
    }
    @Override
    protected Object getPropetyAt(Equipo e, int col) {
        switch (cols[col]) {
            case NOMBRE:
                return e.getNombre();
            case PG:
                return e.getGanados();
            case PE:
                return e.getEmpatados();
            case PP:
                return e.getPerdidos();
            case PUNTOS:
                return e.getPuntos();
            default:
                return "";
        }
    }
}
