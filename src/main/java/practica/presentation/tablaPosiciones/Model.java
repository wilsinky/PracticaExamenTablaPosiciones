package practica.presentation.tablaPosiciones;

import practica.logic.Equipo;
import practica.logic.Partido;
import practica.logic.Service;
import practica.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model extends AbstractModel {
    private Equipo equipoActual;
    private List<Equipo> equipos;

    private Partido partidoActual;
    private List<Partido> partidos;

    public static final String EQUIPO = "equipo";
    public static final String EQUIPOS = "equipos";
    public static final String PARTIDO = "partido";
    public static final String PARTIDOS = "partidos";


    public Model() {
        equipoActual = new Equipo();
        equipos = new ArrayList<Equipo>();
        partidos = new ArrayList<Partido>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(EQUIPO);
        firePropertyChange(EQUIPOS);
        firePropertyChange(PARTIDOS);
    }

    public Equipo getCurrentEquipo() {
        return equipoActual;
    }

    public void setCurrentEquipo(Equipo current) {
        this.equipoActual = current;
        firePropertyChange(EQUIPO);
        firePropertyChange(PARTIDOS);
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> list) {
        this.equipos = list;
        firePropertyChange(EQUIPOS);
    }

    public List<Partido> getPartidos() {
        if (equipoActual == null || equipoActual.getId() == null || equipoActual.getId().trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Service.instance().findPartidosByEquipo(equipoActual);
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
        firePropertyChange(PARTIDOS);
    }

    public void addPartido(Partido partido) {
        this.partidos.add(partido);
        firePropertyChange(PARTIDOS);
    }

    public void removePartido(Partido partido) {
        this.partidos.remove(partido);
        firePropertyChange(PARTIDOS);
    }

    public Partido getPartidoActual() {
        return partidoActual;
    }

    public void setPartidoActual(Partido partido) {
        this.partidoActual = partido;
        firePropertyChange(PARTIDO);
    }



}