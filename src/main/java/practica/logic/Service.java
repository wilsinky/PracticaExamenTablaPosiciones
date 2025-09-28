package practica.logic;

import practica.data.XmlPersister;
import practica.data.Data;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Service() {
        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
    }

    public void stop() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Data data;

    // =============== Equipos ===============

    public List<Equipo> findAllEquipos() {
        return data.getEquipos();
    }

    public List<Equipo> findAllEquiposOrdenados() {
        return data.getEquipos().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getPuntos(), e1.getPuntos()))
                .toList();
    }

    // =============== Partidos ===============

    public List<Partido> findPartidosByEquipo(Equipo equipo) {
        if (equipo == null || equipo.getId() == null) {
            return new ArrayList<>();
        }

        return data.getPartidos().stream()
                .filter(p -> (p.getEquipoCasa() != null && p.getEquipoCasa().getId().equals(equipo.getId())) ||
                        (p.getEquipoVisita() != null && p.getEquipoVisita().getId().equals(equipo.getId())))
                .toList();
    }

    public void createPartido(Partido p) throws Exception {
        if (p.getEquipoCasa() == null || p.getEquipoVisita() == null) {
            throw new Exception("Debe seleccionar ambos equipos");
        }
        if (p.getEquipoCasa().equals(p.getEquipoVisita())) {
            throw new Exception("Los equipos no pueden ser el mismo");
        }

        // Actualizar estadísticas
        Equipo casa = p.getEquipoCasa();
        Equipo visita = p.getEquipoVisita();

        casa.setJugados(casa.getJugados() + 1);
        visita.setJugados(visita.getJugados() + 1);

        if (p.getGolesCasa() > p.getGolesVisita()) {
            casa.setGanados(casa.getGanados() + 1);
            casa.setPuntos(casa.getPuntos() + 3);
            visita.setPerdidos(visita.getPerdidos() + 1);
        } else if (p.getGolesVisita() > p.getGolesCasa()) {
            visita.setGanados(visita.getGanados() + 1);
            visita.setPuntos(visita.getPuntos() + 3);
            casa.setPerdidos(casa.getPerdidos() + 1);
        } else {
            casa.setEmpatados(casa.getEmpatados() + 1);
            visita.setEmpatados(visita.getEmpatados() + 1);
            casa.setPuntos(casa.getPuntos() + 1);
            visita.setPuntos(visita.getPuntos() + 1);
        }

        data.getPartidos().add(p);
        data.getEquipos().sort((e1, e2) -> Integer.compare(e2.getPuntos(), e1.getPuntos()));
    }

    public List<Partido> findAllPartidos() {
        return data.getPartidos();
    }




}