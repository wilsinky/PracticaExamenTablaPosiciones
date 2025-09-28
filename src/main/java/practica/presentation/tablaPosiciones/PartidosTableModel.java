package practica.presentation.tablaPosiciones;

import practica.logic.Partido;
import practica.presentation.AbstractTableModel;

import java.util.List;

public class PartidosTableModel extends AbstractTableModel<Partido> implements javax.swing.table.TableModel {
    public PartidosTableModel(int[] cols, List<Partido> rows) {
        super(cols, rows);
    }

    public static final int CASA = 0;
    public static final int GC = 1;
    public static final int VISITA = 2;
    public static final int GV = 3;

    @Override
    protected void initColNames() {
        colNames = new String[4];
        colNames[CASA] = "Casa";
        colNames[GC] = "Goles";
        colNames[VISITA] = "Visita";
        colNames[GV] = "Goles";

    }
    @Override
    protected Object getPropetyAt(Partido e, int col) {
        switch (cols[col]) {
            case CASA:
                return e.getEquipoCasa();
            case GC:
                return e.getGolesCasa();
            case VISITA:
                return e.getEquipoVisita();
            case GV:
                return e.getGolesVisita();
            default:
                return "";
        }
    }
}