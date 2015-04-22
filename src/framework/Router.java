package framework;

public class Router {
	public void resolve(String route){
		String controllerName = route.split("@")[0];
		String actionName = route.split("@")[1];
	}
}