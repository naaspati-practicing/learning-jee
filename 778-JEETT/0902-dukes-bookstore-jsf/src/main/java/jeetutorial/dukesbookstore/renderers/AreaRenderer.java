package jeetutorial.dukesbookstore.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import jeetutorial.dukesbookstore.jsf.components.AreaComponent;
import jeetutorial.dukesbookstore.jsf.components.MapComponent;
import jeetutorial.dukesbookstore.model.ImageArea;

@FacesRenderer(componentFamily="Area", rendererType="DemoArea")
public class AreaRenderer extends Renderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		validate(context, component);
	}

	private void validate(FacesContext context, UIComponent component) {
		if(context == null || component == null)
			throw new NullPointerException();
	}
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		validate(context, component);
	}
	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		validate(context, component);
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		validate(context, component);
		AreaComponent area = (AreaComponent) component; 
		String targetImgId = area.findComponent(area.getTargetImage()).getClientId();
		ImageArea iarea = (ImageArea) area.getValue();
		
		ResponseWriter w = context.getResponseWriter();
		w.startElement("area", area);
		
        w.writeAttribute("alt", iarea.getAlt(), "alt");
        w.writeAttribute("coords", iarea.getCoords(), "coords");
        w.writeAttribute("shape", iarea.getShape(), "shape");
        StringBuilder sb = new StringBuilder("document.forms[0]['").append(targetImgId)
                .append("'].src='");
        sb.append(
                getURI(context,
                        (String) area.getAttributes().get("onmouseout")));
        sb.append("'");
        w.writeAttribute("onmouseout", sb.toString(), "onmouseout");
        sb = new StringBuilder("document.forms[0]['").append(targetImgId)
                .append("'].src='");
        sb.append(
                getURI(context,
                        (String) area.getAttributes().get("onmouseover")));
        sb.append("'");
        w.writeAttribute("onmouseover", sb.toString(), "onmouseover");
        sb = new StringBuilder("document.forms[0]['");
        sb.append(getName(context, area));
        sb.append("'].value='");
        sb.append(iarea.getAlt());
        sb.append("'; document.forms[0].submit()");
        w.writeAttribute("onclick", sb.toString(), "value");
        w.endElement("area");
        
	}
	
	private String getName(FacesContext context, UIComponent component) {
        while (component != null) {
            if (component instanceof MapComponent) {
                return (component.getId() + "_current");
            }

            component = component.getParent();
        }

        throw new IllegalArgumentException();
    }
	
	private String getURI(FacesContext context, String value) {
        if (value.startsWith("/")) {
            return (context.getExternalContext().getRequestContextPath() + value);
        } else {
            return (value);
        }
    }
	

}
