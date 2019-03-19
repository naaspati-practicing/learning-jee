package sam.learns.primefaces.auto.complete;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="themeService", eager=true)
@ApplicationScoped
public class ThemeService {
	private List<Theme> themes;
	
	@PostConstruct
	public void init() {
		
		String[] names = {
				"Nova-Light",
				"Nova-Dark",
				"Nova-Colored",
				"Luna-Blue",
				"Luna-Amber",
				"Luna-Green",
				"Luna-Pink",
				"Omega"
		};
		
		Theme[] themes = new Theme[names.length];
		for (int i = 0; i < names.length; i++) 
			themes[i] = new Theme(i+1, names[i], names[i].toLowerCase());
		
		this.themes = Collections.unmodifiableList(Arrays.asList(themes));
	}
	
	public List<Theme> getThemes() {
		return themes;
	}
}
