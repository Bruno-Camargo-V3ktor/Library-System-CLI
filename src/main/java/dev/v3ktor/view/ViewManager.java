package dev.v3ktor.view;

import dev.v3ktor.util.Props;

import java.util.HashMap;
import java.util.Map;

public class ViewManager {

    //ATRIBUTOS
    private Map<String, Class<?>> views = new HashMap<>();
    private View target = null;
    private boolean isActive = false;

    //CONSTUTORES
    public ViewManager() {}

    //MÉTODOS
    public <T extends View> ViewManager register(String name, Class<T> view)
    {
        views.put( name, view );
        return this;
    }

    public void to(String name, Props props) {
        if( target != null ) target.close();

        try {
            target = (View) views.get(name).getConstructor(this.getClass(), Props.class).newInstance(this, props);
        }
        catch (Exception e) { e.printStackTrace(); }

        if( !isActive ){
            isActive = true;
            renderLoop();
        }
    }

    public void renderLoop()
    {
        while( this.isActive ) { target.render(); }
    }

    public void close() { this.isActive = false; }
}
