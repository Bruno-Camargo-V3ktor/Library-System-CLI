package dev.v3ktor.view;

import dev.v3ktor.util.Props;

public abstract class View {

    //ATRIBUTOS
    protected Props props;
    protected ViewManager manager;

    //CONSTRUTORES
    public View(ViewManager manager, Props props) {
        this.manager = manager;
        this.props = props;
    }

    //Métodos
    public abstract void render();
    public abstract void close();
}
