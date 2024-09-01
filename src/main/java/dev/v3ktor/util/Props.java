package dev.v3ktor.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Props {

    //ATRIBUTOS
    private Map<String, Object> props = new HashMap<>();

    //CONTRUTORES
    public Props() {}
    public Props(Map<String, Object> props) { this.props = props; }
    public Props( String[] names, Object[] values ) {
        for (int i = 0; i < names.length; i++) {
            props.put( names[i], values[i] );
        }
    }
    public Props( List<String> names, List<?> values) {
        for (int i = 0; i < names.size(); i++) { props.put( names.get(i), values.get(i) ); }
    }

    //MÉTODOS
    public <T> T get(String key, Class<T> type)
    {
        return type.cast(props.get(key));
    }

    public void put(String key, Object value)
    {
        props.put(key, value);
    }

}
