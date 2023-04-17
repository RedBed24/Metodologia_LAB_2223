package campusvirtual;

import java.util.Comparator;

public class Ordenar {

	public static <X> void quickSortC(X[] t, Comparator<X> c) {
		if(t.length > 0) quickSortC(t, 0, t.length - 1, c);
	}

	public static <X> void quickSortC(X[] t, int li, int ls, Comparator<X> c) {
		int izq = li;
		int der = ls;
		X x = t[izq];// {pivote, puede ser cualquier elemento}
		do{
			while(izq < ls && c.compare(t[izq], x) < 0) izq++;
			while(der >= li && c.compare(t[der], x) > 0) der--;
			if(izq <= der){
				intercambiar(t, izq, der);
				izq++;
				der--;
			}
		}while(izq < der);
		//{combinar}
		if(li < der) quickSortC(t, li, der, c);
		if(izq < ls) quickSortC(t, izq, ls, c);
	}

	public static <X> void intercambiar(X[] t, int pos1, int pos2) {
		if (pos1 >= 0 && pos2 >= 0 && pos1 < t.length && pos2 < t.length){
			X aux = t[pos1];
			t[pos1] = t[pos2];
			t[pos2] = aux;
		}
	}
}

