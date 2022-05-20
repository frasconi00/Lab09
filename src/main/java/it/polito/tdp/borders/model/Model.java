package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Graph<Country,DefaultEdge> grafo;
	private BordersDAO dao;
	private Set<Country> countries; //vertici del grafo
	private Map<Integer,Country> allCountriesIdMap; // tutti i countries

	public Model() {		
		this.dao = new BordersDAO();
		this.allCountriesIdMap = dao.loadAllCountries();
	}
	
	public Collection<Country> getAllCountries() {
		return this.allCountriesIdMap.values();
	}
	
	public void creaGrafo(int anno) {
		
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		this.countries = new HashSet<Country>();
		
		List<Border> archi = dao.getCountryPairs(anno); //contiene tutti gli edges
		
		for(Border b : archi) {
			this.countries.add(this.allCountriesIdMap.get(b.getState1no()));
			this.countries.add(this.allCountriesIdMap.get(b.getState2no()));
		}
		
		Graphs.addAllVertices(this.grafo, this.countries);
		for(Border b : archi) {
			this.grafo.addEdge(this.allCountriesIdMap.get(b.getState1no()), this.allCountriesIdMap.get(b.getState2no()));
		}	
		
	}
	
	public List<StatoEGrado> stampa1() {
		
		if(this.grafo==null)
			return null;
		
		List<StatoEGrado> lista = new ArrayList<StatoEGrado>();
		
		for(Country c : this.countries) {
			lista.add(new StatoEGrado(c, this.grafo.degreeOf(c)));
		}
		
		return lista;
		
	}
	
	public int stampa2() {
		
		if(this.grafo==null)
			return -1;
		
		ConnectivityInspector<Country, DefaultEdge> connectInspect = new ConnectivityInspector<>(this.grafo);
		return connectInspect.connectedSets().size();
		
	}
	
	public List<Country> statiRaggiungibili(Country partenza) {
		
		if(this.grafo==null)
			return null;
		
		if(!this.grafo.vertexSet().contains(partenza)) {
			return null;
		}
		
		List<Country> result = new ArrayList<Country>();
		
		GraphIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<Country, DefaultEdge>(this.grafo,partenza);
		
		visita.next(); //mi serve a non inserire il vertice di partenza
		
		while(visita.hasNext()) {
			result.add(visita.next());
		}
		
		return result;
	}
	

}
