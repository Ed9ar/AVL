public class Avl<T extends Comparable<T>> {
	private Nodo<T> raiz;
	private Nodo<T> ant;
	public int counter = 0;
	public int nivel = 0;
	public String swich = "off";
	public String desb = "";
	public String rot = "";
	public int dir;
	public int ins = 1;

	public Nodo<T> getRaiz() {
		return raiz;
	}
	

	public void insertarElemento(T elemento) {
		swich = "off";
		raiz= insertaRecursivo(elemento,raiz);
	}
	
	
	public Nodo<T> insertaRecursivo(T elemento,Nodo<T> raiz) {
		if(raiz==null) {
			raiz= new Nodo<T>(elemento);


		}else {
			if(elemento.compareTo(raiz.getElemento())>0) {
				raiz.setDerecha(insertaRecursivo(elemento, raiz.getDerecha()));
				if(altura(raiz.getIzquierda())-altura(raiz.getDerecha())==-2) {
					if(elemento.compareTo(raiz.getDerecha().getElemento())>0) {
						desb = raiz.getElemento().toString();
						rot = "Rotar simple a la izquierda";
						raiz= rotaSimpleALaIzquierda(raiz);
					}else {
						desb = raiz.getElemento().toString();
						rot = "Rotar doble a la izquierda";
						raiz= rotarDobleALaIzquierda(raiz);
					}
					swich = "on";
				}
			}
			if(elemento.compareTo(raiz.getElemento())<0) {
				raiz.setIzquierda(insertaRecursivo(elemento, raiz.getIzquierda()));
				if(altura(raiz.getIzquierda())-altura(raiz.getDerecha())==2) {
					if(elemento.compareTo(raiz.getIzquierda().getElemento())<0) {
						desb = raiz.getElemento().toString();
						rot = "Rotar simple a la derecha";
						raiz= rotaSimpleALaDerecha(raiz) ;
					}else {
						desb = raiz.getElemento().toString();
						rot = "Rotar doble a la derecha";
						raiz= rotarDobleALaDerecha(raiz);
					}
					swich = "on";
				}	
			}
		}
		
		int altura= max(altura (raiz.getIzquierda()),altura(raiz.getDerecha()));
		System.out.println("Altura nodo: "+raiz.getElemento()+" "+altura);
		raiz.setAltura(altura+1);
		return raiz;
	}
	
	private Nodo<T> rotaSimpleALaIzquierda(Nodo<T> raiz){
		Nodo<T> temp= raiz.getDerecha();
		raiz.setDerecha(temp.getIzquierda());
		temp.setIzquierda(raiz);
		raiz.setAltura(max(altura(raiz.getIzquierda()),altura(raiz.getDerecha()))+1);
		temp.setAltura(max(altura(temp.getDerecha()),altura(raiz))+1);
		return temp;
	}
	private Nodo<T> rotaSimpleALaDerecha(Nodo<T> raiz){
		Nodo<T> temp= raiz.getIzquierda();
		raiz.setIzquierda(temp.getDerecha());
		temp.setDerecha(raiz);
		raiz.setAltura(max(altura(raiz.getIzquierda()),altura(raiz.getDerecha()))+1);
		temp.setAltura(max(altura(temp.getIzquierda()),altura(raiz))+1);
		return temp;
	}	
	
	private Nodo<T> rotarDobleALaIzquierda(Nodo<T> raiz){
		raiz.setDerecha(rotaSimpleALaDerecha(raiz.getDerecha()));
		return rotaSimpleALaIzquierda(raiz);
	}
	private Nodo<T> rotarDobleALaDerecha(Nodo<T> raiz){
		raiz.setIzquierda(rotaSimpleALaIzquierda(raiz.getIzquierda()));
		return rotaSimpleALaDerecha(raiz);
	}
	private int max(int a, int b) {
		if(a>b) {
			return a;
		}else {
			return b;
		}
	}
	
	private int altura(Nodo<T> nodo) {
		if(nodo==null) {
			return -1;
		}else {
			
			return nodo.getAltura();
		}
	}

	public int calcAltura(){
		return alturaAr(raiz);
	}

	public int alturaAr(Nodo <T> nodo) {
		if(nodo == null)   
		{
			return 0;
		}

		else   
		{

			return 1+(Math.max(altura(nodo.getIzquierda()),altura(nodo.getDerecha())));   
	}

	
}


	public void imprimeArbol(int alturan) {
		System.out.println("Estoy Aqui");
		int counte = alturan;
		if(raiz == null){
			return;
		}
		for(int i = 0; i < alturan; i++){

			for(int j = counte; j > 0; j--){
				System.out.print(" ");
				
			}
			
			imprimeArbolRec(raiz, i+1, counter);
			System.out.println();
			for(int j = counte - 1; j > 0; j--){
				System.out.print(" ");
				
			}
			/*imprimeEspacio(raiz, i+1, counter);
			counter++;

			counte--;*/
			System.out.println();
		}
	}

	public void imprimeArbolRec(Nodo <T> nodo, int altura, int count){
		if(nodo == null){
			return;
		}
		if(altura == 1){
			return;
		}
		else{
			imprimeArbolRec(nodo.getIzquierda(), altura - 1, count);
			imprimeArbolRec(nodo.getDerecha(), altura - 1, count);
		}

	}
	public void deleteNode(T elemento) {
		raiz= deleteRec(elemento,raiz);
	}

	public Nodo <T> nodoMenor(Nodo <T> nodo){
		Nodo <T> actual = nodo;
		while (actual.getIzquierda() != null) {
			actual = actual.getIzquierda();
		}
		return actual;
	}
	public int getBalance(Nodo <T> nododo) {
		if(nododo== null) {
			return 0;
		}
		return altura(nododo.getIzquierda()) - altura(nododo.getDerecha());
	}
	
	public Nodo<T> deleteRec(T elemento, Nodo <T> raiz){
		if(raiz==null) {
			return raiz;
		}
		if(elemento.compareTo(raiz.getElemento())<0){
			raiz.setIzquierda(deleteRec(elemento, raiz.getIzquierda()));
			}
		if(elemento.compareTo(raiz.getElemento())>0) {
			raiz.setDerecha(deleteRec(elemento, raiz.getDerecha()));
		}
		else {
			if((raiz.getIzquierda()== null) || (raiz.getDerecha()== null)){
				Nodo<T> temp= null;
				if(temp == raiz.getIzquierda()) {
					temp = raiz.getDerecha();
				}else {
					temp = raiz.getIzquierda();
				}if(temp == null) {
					temp = raiz;
					raiz = null;
				}else {
					raiz = temp;
				}
			}
			else {
				Nodo<T> temp = nodoMenor(raiz.getDerecha());
				raiz.setElemento(temp.getElemento());
				raiz.setDerecha(deleteRec(temp.getElemento(), raiz.getDerecha()));
			}
		}
		if(raiz == null) {
			return raiz;
		}
	return raiz;
	}
	
	
	public void borraElemento(T elemento) {
		//counter++;
		//System.out.println("Si estoy aqqqqqqqquiiiiiiiiii");
		Nodo<T> nodo= new Nodo<>(elemento);
		if(raiz==null) {
			return;
		}else {
			borrarElementoRec(raiz,nodo);
		}
	}
	public void borrarElementoRec(Nodo<T> raiz, Nodo<T> nodoABorrar) {
		if(raiz==null) {
			//System.out.println("Si estoy aqqqqqqqquiiiiiiiiii");
			return;
		}else {
			if(nodoABorrar.getElemento().compareTo(raiz.getElemento())>0) {
				ant= raiz;
				ant.setElemento(raiz.getElemento());
	
				borrarElementoRec(raiz.getDerecha(),nodoABorrar);
				dir = 1;
				//borElementoRec(raiz.getDerecha(),nodoAInsertar));
			}else if(nodoABorrar.getElemento().compareTo(raiz.getElemento())<0){
				ant= raiz;
				ant.setElemento(raiz.getElemento());
				borrarElementoRec(raiz.getIzquierda(),nodoABorrar);

				dir = 2;
			}
			else{
				if(ant != null){
					System.out.println("Nodo anterior " + ant.getElemento());
					try{
						if(ant.getDerecha().getElemento().compareTo(raiz.getElemento())==0 && raiz.getElemento() != null){
							System.out.println("DEREEECHAAA");
							if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
								ant.setDerecha(raiz.getDerecha().getDerecha());
	
							}
							else if(raiz.getIzquierda() == null){
								System.out.println("You fui " + raiz.getDerecha());
								ant.setDerecha(raiz.getDerecha());
								
							}
							else if(raiz.getDerecha() == null){
								ant.setIzquierda(raiz.getDerecha());
							}
							else{
								Nodo<T> temp= raiz.getDerecha();
								Nodo<T> temp2= temp;
								System.out.println();
								while(temp.getIzquierda()!= null){
									temp2 = temp;
									temp = temp.getIzquierda();
									System.out.println(temp);
								}
								raiz.setElemento(temp.getElemento());
								/*if(temp2.getIzquierda().getElemento().compareTo(temp.getElemento())==0){
									temp2.setIzquierda(null);
								}*/
								//System.out.println(temp.getElemento());
								//temp.setElemento(null);
								temp = null;
							}
						}
						else{
							//System.out.println("Izquieeeerdaaaaa");
							if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
								ant.setIzquierda(null);
							}
							else {
								System.out.println("Soy yo XD");
								if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
									ant.setIzquierda(raiz.getDerecha().getDerecha());
		
								}
								else if(raiz.getIzquierda() == null){
									System.out.println("You fui " + raiz.getDerecha());
									ant.setIzquierda(raiz.getDerecha());
									
								}
								else if(raiz.getDerecha() == null){
									ant.setIzquierda(raiz.getIzquierda());
								}
								else{
									Nodo<T> temp= raiz.getDerecha();
									Nodo<T> temp2= temp;
									System.out.println();
									while(temp.getIzquierda()!= null){
										temp2 = temp;
										temp = temp.getIzquierda();
										System.out.println(temp);
									}
									raiz.setElemento(temp.getElemento());
									if(temp2.getIzquierda().getElemento().compareTo(temp.getElemento())==0){
										temp2.setIzquierda(null);
									}
									//System.out.println(temp.getElemento());
									//temp.setElemento(null);
									temp = null;
								}
								/*if(raiz.getIzquierda() == null){
									raiz.setElemento(raiz.getDerecha().getElemento());
									raiz.setDerecha(raiz.getDerecha().getDerecha());
								}
								else{
									Nodo<T> temp= raiz.getDerecha();
									while(temp.getIzquierda()!= null){
										temp = temp.getIzquierda();
										System.out.println(temp);
									}
									raiz.setElemento(temp.getElemento());
									System.out.println(temp.getElemento());
									//temp.setElemento(null);
									temp = null;
								}*/
							}
						}
					}
					catch(NullPointerException ex){
						System.out.println("Creo soy yo");
						if(ant.getIzquierda() != null){
							if(ant.getIzquierda().getElemento().compareTo(raiz.getElemento())==0 && raiz.getElemento() != null){
								if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
									ant.setIzquierda(null);
		
								}
								else {
									if(raiz.getDerecha() == null){
										System.out.println("You fui " + raiz.getDerecha());
										ant.setIzquierda(raiz.getIzquierda());
									}
									else{
										//ant.setIzquierda(ant.getIzquierda());
									}
								}
							}
							else{
								System.out.println("Izquieeeerdaaaaa");
								if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
									ant.setDerecha(null);
								}
								else {
									if(raiz.getIzquierda() == null){
										System.out.println("You fui " + raiz.getDerecha());
										ant.setIzquierda(raiz.getDerecha());
									}
									else{
										//ant.setIzquierda(ant.getIzquierda());
									}
								}
							}
						}
						else{
							if(ant.getDerecha().getElemento().compareTo(raiz.getElemento())==0 && raiz.getElemento() != null){
								if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
									ant.setDerecha(null);
		
								}
								else {
									if(raiz.getIzquierda() == null){
										System.out.println("You fui " + raiz.getDerecha());
										ant.setDerecha(raiz.getDerecha());
									}
									else{
										//ant.setIzquierda(ant.getIzquierda());
									}
								}
							}
							else{
								System.out.println("Izquieeeerdaaaaa");
								if((raiz.getIzquierda()== null) && (raiz.getDerecha()== null)){
									ant.setIzquierda(null);
								}
								else {
									if(raiz.getIzquierda() == null){
										System.out.println("You fui " + raiz.getDerecha());
										ant.setIzquierda(raiz.getDerecha());
									}
									else{
										//ant.setIzquierda(ant.getIzquierda());
									}
								}
							}
						
						}
						
						System.out.println("Aqui sta ");
					}
					
				}
				if(raiz.getDerecha() == null){
					raiz.setElemento(raiz.getIzquierda().getElemento());
					raiz.setIzquierda(raiz.getIzquierda().getIzquierda());
				}
				else if(raiz.getIzquierda() == null){
					raiz.setElemento(raiz.getDerecha().getElemento());
					raiz.setIzquierda(raiz.getDerecha().getDerecha());
				}
				else if(raiz.getIzquierda() != null && raiz.getDerecha() != null && raiz.getIzquierda().getIzquierda() == null && raiz.getDerecha().getDerecha() == null){
					System.out.println("Soy io????");
					raiz.setElemento(raiz.getDerecha().getElemento());
					raiz.setIzquierda(raiz.getDerecha().getDerecha());
					
				}
				else{
					
					Nodo<T> temp= raiz.getDerecha();
					Nodo<T> temp2= temp;
					System.out.println();
					while(temp.getIzquierda()!= null){
						temp2 = temp;
						temp = temp.getIzquierda();
						System.out.println(temp);
					}
					raiz.setElemento(temp.getElemento());
					if(temp2.getIzquierda().getElemento().compareTo(temp.getElemento())==0){
						temp2.setIzquierda(null);
					}
					//System.out.println(temp.getElemento());
					//temp.setElemento(null);
					temp = null;
				}
				
				
				
			}
			//imprimeArbol(2);
			//return raiz;
		}
	}
}

