import javafx.application.Application;
import java.awt.geom.Line2D;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Group; 
import javafx.scene.shape.Circle; 
import javafx.scene.shape.Line; 
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 
import java.util.Random;
import java.io.*;

public class ArbD<T extends Comparable<T>> extends Application{

	//Aqui se crean todos los objetos del panel
	Label label;
	Circle circle;
	Group group;
	String switch2 = "off";
	Line line;
	Text elemento;
	Avl<Perro> avl= new Avl<>();
	Arbol<Perro> arbol = new Arbol<>();
	VBox vbox3 = new VBox();
	Pane pane = new Pane();
	Scene scene= new Scene(pane,1000,1000);
	int x = 90;
	Stage primaryStage;
	int[] array = new int[100];  
	VBox vbox2 = new VBox();
	VBox vbox = new VBox();
	HBox hbox = new HBox();
	HBox hbox2 = new HBox();
	TextField textField = new TextField();
	TextField textField2 = new TextField();
	Button btn = new Button();
	Button btn2 = new Button();
	int factor;

	//Se inicia el panel
	public static void main(String[] args){
		launch(args);
	}

	//Se llama el metodo recursivo con el nodo raíz 
	public void drawNodo(Nodo<Perro> root) {
		int maxLevel = maxLevel(root);

		//Se crea una lista para recorrer todos los nodos
        drawNodoInternal(Collections.singletonList(root), 1, maxLevel);
	}

	//Metodo para dibujar recursivo, recibe lista, nivel y el maximo
	private void drawNodoInternal(List<Nodo<Perro>> Nodos, int level, int maxLevel) {
		//Si no hay nada, no dibuja nada
        if (Nodos.isEmpty() || isAllElementsNull(Nodos))
            return;

		//Calcula profundidad
		int floor = maxLevel - level;

		//Se calculan los espacios en X y Y 
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));

		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
		
		
		//System.out.println("LEVEL : " + level);
		//System.out.println("BEETWEEN SPACES : " + betweenSpaces);
		//System.out.println("EndeLines : " + endgeLines);


		List<Nodo<Perro>> newNodos = new ArrayList<Nodo<Perro>>();
		//Recorre Cada Nodo de la lista
        for (Nodo<Perro> Nodo : Nodos) {
            if (Nodo != null) {
				//System.out.print("Nodo ");
				//System.out.println(Nodo.getElemento());
				//Para cada nodo crea un circulo y un elemento de texto
				circle = new Circle();
				elemento = new Text();

				circle.setRadius(17.0f);

				//Establece la posicion de la raiz
				if(level == 1){
					Nodo.setX(700);
					Nodo.setY(200);
				}
				
				//System.out.println(Nodo.getElemento().toString());
				//System.out.println("SON IGUALES ????");
				//System.out.println(avl.desb);


				//Pintar nodo desbalanceado
				if(Nodo.getElemento().toString().compareTo(avl.desb) == 0  && avl.rot!= null){
					//System.out.println("AQUITOY");
					circle.setFill(Color.rgb(255,0,0,.99));
				}
				else{
					circle.setFill(Color.rgb(255,160,122));
				}
				

				//System.out.println( "Circle X : "+ Nodo.getX());
				//System.out.println("Circle Y : " + Nodo.getY());
				//Se asigna el centro del circulo
				circle.setCenterX(Nodo.getX());
				circle.setCenterY(Nodo.getY());
				
				//Se pone el elemento de texto adentro del circulo
				elemento.setText(Nodo.getElemento().toString());
				//elemento.setFill(Color.rgb(255,255,255,.99));
				elemento.setFill(Color.rgb(68,68,68)); 
				circle.toFront();
				elemento.toFront();
				elemento.setX(circle.getCenterX()-2);
				elemento.setY(circle.getCenterY()+5);
				
				//Se agrega el circulo y el elemento al grupo que se pondra en panel
				group = new Group(circle, elemento);

				//System.out.print(Nodo.getElemento());

				//Si el siguiente nivel tiene un nodo, se asigna los centro con los valores calculados por nivel
				if(Nodo.getIzquierda() != null){
					line = new Line();
					line.setStartX(Nodo.getX()); 
					line.setStartY(Nodo.getY()); 
					line.setEndY(circle.getCenterY() + 25 * endgeLines); 
					Nodo.getIzquierda().setY(circle.getCenterY() + 25 * endgeLines);
					Nodo.getIzquierda().setX(circle.getCenterX() - betweenSpaces * 10);
					line.setEndX(circle.getCenterX() - betweenSpaces * 10); 
					elemento.toFront();
					group.getChildren().add(line);
				}
				//Si el siguiente nivel tiene un nodo, se asigna los centro con los valores calculados por nivel
				if(Nodo.getDerecha() != null){
					line = new Line();
					line.setStartX(Nodo.getX()); 
					line.setStartY(Nodo.getY()); 

					line.setEndY(circle.getCenterY() + 25 * endgeLines);
					Nodo.getDerecha().setY(circle.getCenterY() + 25 * endgeLines);
					Nodo.getDerecha().setX(circle.getCenterX() + betweenSpaces * 10);
					line.setEndX(circle.getCenterX() + betweenSpaces * 10); 
					group.getChildren().add(line);
				}
				circle.toFront();
				elemento.toFront();
				//Se agregan los elementos al panel
				pane.getChildren().addAll(group);

                newNodos.add(Nodo.getIzquierda());
                newNodos.add(Nodo.getDerecha());
            } else {
                newNodos.add(null);
                newNodos.add(null);
                //System.out.print(" ");
			}
		}
		primaryStage.setScene(scene);

        //System.out.println("");

		//Se llama el metodo recursivo
        drawNodoInternal(newNodos, level + 1, maxLevel);
	}
	
	//Se calcula la altura
    private int maxLevel(Nodo<Perro> Nodo) {
        if (Nodo == null)
            return 0;

        return Math.max(maxLevel(Nodo.getIzquierda()), maxLevel(Nodo.getDerecha())) + 1;
    }

	//Se verifica que haya nodos en el arbol
    private boolean isAllElementsNull(List<Nodo<Perro>> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

	//Recorre el avl para rellenar el arbol normal
	public void recorreEnPreOrden() {
		recorreEnPreOrdenRec(avl.getRaiz());
	}
	//Borra todos los elementos del arbol
	private void recorreEnPreOrdenRec(Nodo<Perro> nodo) {
		if(nodo!=null) {
			//System.out.print(nodo.getElemento().toString()+", ");
			if(nodo.getElemento()!= null){
				arbol.deleteNode(nodo.getElemento());
			}

			//array[counter] = Integer.parseInt(nodo.getElemento().toString());
			recorreEnPreOrdenRec(nodo.getIzquierda());
			recorreEnPreOrdenRec(nodo.getDerecha());
			
		}
	}

	public void recorreEnPreOrdenin() {
		recorreEnPreOrdenRecin(avl.getRaiz());
	}
	//Inserta todos los elementos del avl en el arbol
	private void recorreEnPreOrdenRecin(Nodo<Perro> nodo) {
		if(nodo!=null) {
			//System.out.print(nodo.getElemento().toString()+", ");
			arbol.insertarElemento(nodo.getElemento());
			recorreEnPreOrdenRecin(nodo.getIzquierda() );
			recorreEnPreOrdenRecin(nodo.getDerecha());
			
		}
	}

	public void start(Stage primaryStage){
		//Crea el stage primario, agrega boxes, labels y botones
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Arbol");
		primaryStage.show();
	
		btn.setText("Borrar Nodo");
		btn2.setText("Insertar Nodo");
		btn.getStyleClass().add("boton");
		btn2.getStyleClass().add("boton");
		label =new Label("Nodo a insertar");

		Button balancear = new Button();
		balancear.setText("Balancear");

	
		ArbolPrinter<Perro> ap= new ArbolPrinter<>();
		ap.printNodo(arbol.getRaiz());
	


		vbox.getChildren().addAll(label, textField, btn2);
		label =new Label("Nodo a borrar");
		vbox2.getChildren().addAll(label, textField2, btn);
		hbox.getChildren().addAll(vbox,vbox2);
		vbox.setSpacing(10);
		vbox2.setSpacing(10);
		hbox.setSpacing(10);
		
		pane.getChildren().addAll(hbox);

		scene.getStylesheets().add("application.css");
		primaryStage.setScene(scene);

		btn.getStyleClass().add("btn1");
		btn2.getStyleClass().add("btn2");
		balancear.getStyleClass().add("balancear");
		textField.getStyleClass().add("text-field");
		pane.getStyleClass().add("pane");	
		scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Roboto");

		//Boton de insertar, lo mete a los dos arboles y pinta el arbol normal hasta que se desbalancea
		btn2.addEventHandler(MouseEvent.MOUSE_CLICKED ,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				try{
					//arbol.insertarElemento(Integer.parseInt(textField.getText()));
					//avl.insertarElemento(Integer.parseInt(textField.getText()));


                    Random rand = new Random();

// Obtain a number between [0 - 49].
                    int n = rand.nextInt(50);
                    Perro perro = new Perro(n, textField.getText());

                    
                    avl.insertarElemento(perro);
                    arbol.insertarElemento(perro);
                    
					pane.getChildren().clear();
					scene.getStylesheets().add("../bin/application/application.css");
					hbox.getChildren().clear();
					hbox.getChildren().addAll(vbox, vbox2);
					pane.getChildren().addAll(hbox);
					btn.getStyleClass().add("boton");
					btn2.getStyleClass().add("boton");

					//Este switch cambia en Avl.java si se desbalancea
					if(avl.swich == "on"){
						//Se agrega el boton de balancear
						vbox.getChildren().removeAll(btn2);

						Label label = new Label("El Arbol se ha desbalanceado del nodo : "+ avl.desb );
						Label label2 = new Label(avl.rot);
						vbox3.getChildren().addAll(label,label2, balancear);
						vbox3.setSpacing(20);
				
						hbox.getChildren().clear();
						hbox.getChildren().addAll(vbox, vbox2, vbox3);
						hbox.setSpacing(10);
						pane.getChildren().addAll(hbox2);
						switch2 ="on";
					}

					//System.out.println(elemento);
					//Se dibuja el arbol
					drawNodo(arbol.getRaiz());
					avl.rot = null;
					ap.printNodo(arbol.getRaiz());
					ap.printNodo(avl.getRaiz());
				
				}
				catch(NumberFormatException ex){
					System.out.println("No hay elementos para insertar");
				}
				
			}
		});

		//Este metodo imprime el arbol ya balanceado
		balancear.addEventHandler(MouseEvent.MOUSE_CLICKED ,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
					avl.swich = "off";
					vbox.getChildren().addAll(btn2);
					vbox3.getChildren().clear();
					
					pane.getChildren().clear();
					HBox hbox2 = new HBox();
					hbox2.getChildren().addAll(vbox, vbox2, vbox3);
					hbox2.setSpacing(10);
					pane.getChildren().addAll(hbox2);
					//Se llevan los elementos del avl al arbol
					recorreEnPreOrden();
					recorreEnPreOrdenin();


					ap.printNodo(arbol.getRaiz());
					//imprimir el arbol siempre
					drawNodo(arbol.getRaiz());
				}	
		});

		btn.addEventHandler(MouseEvent.MOUSE_CLICKED ,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				elemento = new Text(textField2.getText());
				//Se borran los elementos que se pongan en el textField
				//avl.deleteNode(Integer.parseInt(textField.getText()));
				//arbol.deleteNode(Integer.parseInt(textField.getText()));
				try{
					//arbol.borraElemento(Integer.parseInt(textField2.getText()));
					//avl.borraElemento(Integer.parseInt(textField2.getText()));

					//arbol.deleteNode(Integer.parseInt(textField2.getText()));
					//avl.deleteNode(Integer.parseInt(textField2.getText()));
                    /*
					if(arbol.getRaiz().getElemento().compareTo(textField.getText()) == 0 && arbol.getRaiz().getDerecha() == null && arbol.getRaiz().getIzquierda() == null){
						avl= new Avl<>();
						arbol = new Arbol<>();
					}*/

				}
				catch(NumberFormatException exx){
					System.out.println("No hay nada que borrar");
				}
				//arbol.borrarElemento(Integer.parseInt(textField.getText()));
				//Se llena el arbol con los elementos del avl

				ap.printNodo(arbol.getRaiz());
				ap.printNodo(avl.getRaiz());

				//arbol = new Arbol<>();
				recorreEnPreOrden();
				recorreEnPreOrdenin();

				//System.out.println(elemento);

				pane.getChildren().clear();
				HBox hbox2 = new HBox();
				hbox2.getChildren().addAll(vbox, vbox2, vbox3);
				hbox2.setSpacing(10);
				pane.getChildren().addAll(hbox2);

				try{
					drawNodo(arbol.getRaiz());
				}
				catch(NullPointerException ex){
					System.out.println("Vacio");
				}

				ap.printNodo(arbol.getRaiz());
				ap.printNodo(avl.getRaiz());
				//Se pinta el arbol

				
			}
		});
		
	}

}







		
