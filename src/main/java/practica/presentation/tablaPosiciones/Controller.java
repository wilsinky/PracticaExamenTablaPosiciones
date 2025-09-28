package practica.presentation.tablaPosiciones;

import practica.logic.Equipo;
import practica.logic.Partido;
import practica.logic.Service;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setEquipos(Service.instance().findAllEquiposOrdenados());
        model.setPartidos(model.getEquipos().stream()
                .flatMap(p -> p.getPartidos().stream())
                .toList());
    }

    //Partidos


    public void createPartido(Partido partido) throws Exception {
        Equipo actual = model.getCurrentEquipo();
        if (actual == null) throw new Exception("Seleccione un equipo primero");

        Service.instance().createPartido(partido);

        actual.getPartidos().add(partido);

        model.setPartidos(actual.getPartidos());
        model.setCurrentEquipo(actual);
        model.setEquipos(Service.instance().findAllEquiposOrdenados());
    }

    public void setCurrentPartido(Partido partido){
        model.setPartidoActual(partido);
    }

    public void setCurrentEquipo(Equipo equipo){
        if (equipo != null) {
            List<Partido> partidosDelEquipo = Service.instance().findPartidosByEquipo(equipo);
            equipo.setPartidos(partidosDelEquipo);
        }

        model.setCurrentEquipo(equipo);
    }


    // refresh
    public void refresh() {
        model.setPartidos(Service.instance().findAllPartidos());
        model.setPartidoActual(new Partido());
        model.setEquipos(Service.instance().findAllEquiposOrdenados());
    }
}
