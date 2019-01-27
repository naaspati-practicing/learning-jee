package books.bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.LambdaExpression;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;

@Model
public class LambdaBean {
	private static final List<Integer> costBeforeVAT = list(34, 2200, 1350, 430, 57, 10000, 23, 15222, 1);
	private static final List<Integer> primes = list(11, 29, 5, 19, 2, 13, 17, 7, 23, 3);
	private static final List<String> languages = list("Java", "Scala", "C++", "Onyx", "Haskell", "Inform", "Lisp", "Accent", "Basic", "Delphi", "Fortran");
	private static final List<String> orders = list("order#23200", "order#23200", "order#23200", "order#23200", "order#23200");
	private static final List<String> names = list("Mark", "John", "Nelly", "Mark", "Kelly", "Kelly", "Mark", "Raul", "Molly", "Sully");
	private static final List<String> toothless = list("", "a", "ab", "", "", "abcde", "qwert", "", "er", "k", "klmno");
	
	@SafeVarargs
	private static <E> List<E> list(E...es) {
		return Collections.unmodifiableList(Arrays.asList(es));
	}
    
	public List<Integer> getCostBeforeVAT() {
		return costBeforeVAT;
	}
	public List<Integer> getPrimes() {
		return primes;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public List<String> getOrders() {
		return orders;
	}
	public List<String> getNames() {
		return names;
	}
	public List<String> getToothless() {
		return toothless;
	}
	public Object firstLambdaAction(LambdaExpression lambdaExpression) {

        //useful in case of a custom ELContext
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        return lambdaExpression.invoke(elContext, 8, 3);

        //or simply, default ELContext:
        //return lambdaExpression.invoke(8, 3);
    }

    public Object secondLambdaAction(LambdaExpression lambdaExpression) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();

        Map<String, Object> args = new HashMap<>();
        args.put("n", 17);
        args.put("m", 4);
        elContext.enterLambdaScope(args);
        Object result = lambdaExpression.invoke(elContext.isLambdaArgument("n") ? elContext.getLambdaArgument("n") : 0, elContext.isLambdaArgument("m") ? elContext.getLambdaArgument("m") : 0);
        elContext.exitLambdaScope();

        return result;
    }

    public Object thirdLambdaAction(LambdaExpression lambdaExpression) {
        return lambdaExpression.invoke(10);
    }

}
