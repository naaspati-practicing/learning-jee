package jeetutorial.dukesbookstore.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import jeetutorial.dukesbookstore.Utils;
import jeetutorial.dukesbookstore.jsf.components.MapComponent;

@FacesRenderer(componentFamily="Map", rendererType="DemoMap")
public class MapRenderer extends Renderer implements Utils {
	
	private void validate(FacesContext context, UIComponent component) {
		if(context == null || component == null)
			throw new NullPointerException();
	}
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		validate(context, component);
		MapComponent map = (MapComponent) component;
		
		String key = getName(context, component);
		String value = requestParamMap().get(key);
		
		if(value != null)
			map.setCurrent(value);
	}
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		validate(context, component);
		
		MapComponent map = (MapComponent) component; 
		ResponseWriter w = context.getResponseWriter();
		
		w.startElement("map", map );
		w.writeAttribute("name", map.getId(), "id");
	}
	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		validate(context, component);
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		validate(context, component);
		
		MapComponent map = (MapComponent) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input", map);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("name", getName(context, map), "clientId");
        writer.endElement("input");
        writer.endElement("map");
	}

	private String getName(FacesContext context, UIComponent component) {
        return (component.getId() + "_current");
    }
	private String getURI(FacesContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalContext().getRequestContextPath());
        sb.append("/faces");
        sb.append(context.getViewRoot().getViewId());

        return (sb.toString());
    }
	

}
