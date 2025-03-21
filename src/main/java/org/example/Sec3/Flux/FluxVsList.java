package org.example.Sec3.Flux;

import org.example.Sec3.helper.NameGrenrator;
import org.example.commons.Util;

public class FluxVsList {

	public static void main(String[] args) {

		//var names= NameGrenrator.getNameLists(10);
		//System.out.println(names);

NameGrenrator.getNameFlux(10).subscribe(Util.subscriber());


	}

}
